package aikamapp.dao;

import aikamapp.controller.stat.GoodSale;
import aikamapp.controller.stat.PurchaseStat;
import aikamapp.model.Buyer;
import aikamapp.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository("buyerDao")
public class BuyerDao  extends JdbcDaoSupport {

    @Autowired
    public BuyerDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private final RowMapper<Buyer> BUYER_MAPPER = (ResultSet rs, int rowNum) -> new Buyer(
            rs.getLong("id"),
            rs.getString("firstname"),
            rs.getString("lastname")
    );

    private final RowMapper<PurchaseStat> PURCHASE_STAT_MAPPER = (ResultSet rs, int rowNum) -> {
        Buyer buyer = new Buyer(
                rs.getLong("bid"),
                rs.getString("firstname"),
                rs.getString("lastname")
        );

        Good good = new Good(
                rs.getLong("gid"),
                rs.getString("title"),
                rs.getBigDecimal("price")
        );

        GoodSale goodSale = new GoodSale(
                good,
                rs.getBigDecimal("cost")
        );

        return new PurchaseStat(buyer, goodSale);
    };

    public PurchaseStat mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long bid = rs.getLong("bid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Buyer buyer = new Buyer(bid, firstname, lastname);

        Long gid = rs.getLong("gid");
        String title = rs.getString("title");
        BigDecimal price = rs.getBigDecimal("price");
        Good good = new Good(gid, title, price);

        BigDecimal cost = rs.getBigDecimal("cost");
        GoodSale goodSale = new GoodSale(good, cost);

        return new PurchaseStat(buyer, goodSale);
    }

    public Buyer get(Long id){
        String sql = "select * from buyers b\n" +
                "where b.id = ?";

        Object[] params = new Object[] { id };
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, BUYER_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Buyer> getAll(){
        String sql = "select * from buyers b";

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, BUYER_MAPPER);
    }

    public List<Buyer> getByLastname(String lastname){
        String sql =
                "select * from buyers b\n" +
                "where upper(b.lastname) = upper(?)";

        Object[] params = new Object[] { lastname };
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, BUYER_MAPPER);
    }

    public List<Buyer> getByGoodAndCountOfPurchases(String goodsTitle, int count){
        String sql =
                "select q.id, q.firstname, q.lastname\n" +
                "from (select b.*,\n" +
                "             coalesce(count(p.good_id), 0) number_of_purchases\n" +
                "      from buyers b\n" +
                "      left join purchases p on b.id = p.buyer_id\n" +
                "          and p.good_id = (select g.id from goods g\n" +
                "                           where upper(g.title) = upper(?))\n" +
                "      group by b.id) q\n" +
                "where q.number_of_purchases >= ?";

        Object[] params = new Object[] { goodsTitle, count };
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, BUYER_MAPPER);
    }

    public List<Buyer> getByCostOfPurchases(BigDecimal minCost, BigDecimal maxCost){
        String sql =
                "select q.id, q.firstname, q.lastname\n" +
                "from (select b.*,\n" +
                "       coalesce(sum(g.price), 0) cost_of_purchases\n" +
                "       from buyers b\n" +
                "       left join purchases p on b.id = p.buyer_id\n" +
                "       left join goods g on p.good_id = g.id\n" +
                "       group by b.id) q\n" +
                "where q.cost_of_purchases >=?\n" +
                "  and q.cost_of_purchases <= ?";

        Object[] params = new Object[] { minCost, maxCost };
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, BUYER_MAPPER);
    }

    public List<Buyer> getPassiveBuyers(int limit){
        String sql =
                "select b.id, b.firstname, b.lastname,\n" +
                "       coalesce(count(p.good_id), 0) number_of_purchases\n" +
                "from buyers b\n" +
                "    left join purchases p on b.id = p.buyer_id\n" +
                "group by b.id\n" +
                "order by number_of_purchases\n" +
                "limit ?";

        Object[] params = new Object[] { limit };
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, BUYER_MAPPER);
    }

    public List<PurchaseStat> getPurchaseStats(LocalDate startDate, LocalDate endDate){
        String sql =
                "select b.id bid, b.firstname, b.lastname, g.id gid, g.title, g.price,\n" +
                "    sum(g.price) as cost\n" +
                "from buyers b, goods g, purchases p\n" +
                "where p.buyer_id = b.id\n" +
                "    and p.good_id = g.id\n" +
                "    and p.date between ? and ?\n" +
                "    and TO_CHAR(p.date, 'dy') NOT IN ('sat', 'sun')\n" +
                "group by b.id, g.id\n" +
                "order by b.id, cost desc";

        Object[] params = new Object[] { startDate, endDate };
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, PURCHASE_STAT_MAPPER);
    }

    public Integer getNumberOfDays(LocalDate startDate, LocalDate endDate){
        String sql =
                "select coalesce(count(q.date), 0) num_days from\n" +
                "(select gs.date date\n" +
                "from generate_series(?, ?, interval '1 day') as gs\n" +
                "where TO_CHAR(gs.date, 'dy') NOT IN ('sat', 'sun')) q";

        Object[] params = new Object[] { startDate, endDate };
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
    }

}
