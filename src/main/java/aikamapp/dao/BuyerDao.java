package aikamapp.dao;

import aikamapp.controller.stat.PurchaseStat;
import aikamapp.mapper.BuyerMapper;
import aikamapp.mapper.PurchaseStatMapper;
import aikamapp.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository("buyerDao")
public class BuyerDao  extends JdbcDaoSupport {

    @Autowired
    public BuyerDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Buyer get(Long id){
        String sql = "select * from buyers b\n" +
                "where b.id = ?";

        Object[] params = new Object[] { id };
        BuyerMapper mapper = new BuyerMapper();
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Buyer> getAll(){
        String sql = "select * from buyers b";

        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public List<Buyer> getByLastname(String lastname){
        String sql =
                "select * from buyers b\n" +
                "where upper(b.lastname) = upper(?)";

        Object[] params = new Object[] { lastname };
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
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
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
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
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
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
        BuyerMapper mapper = new BuyerMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public List<PurchaseStat> getPurchaseStats(LocalDate startDate, LocalDate endDate){
        String sql =
                "select b.id bid, b.firstname, b.lastname, g.id gid, g.title, g.price,\n" +
                "    sum(g.price) as cost\n" +
                "from buyers b, goods g, purchases p\n" +
                "where p.buyer_id = b.id\n" +
                "    and p.good_id = g.id\n" +
                "    and p.date between ? and ?\n" +
                "group by b.id, g.id\n" +
                "order by b.id, cost desc";

        Object[] params = new Object[] { startDate, endDate };
        PurchaseStatMapper mapper = new PurchaseStatMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    public Integer getNumberOfDays(LocalDate startDate, LocalDate endDate){
        String sql =
                "select coalesce(count(q.date_set), 0) from\n" +
                "(select p.date date_set\n" +
                "from purchases p\n" +
                "where p.date between ? and ?\n" +
                "group by date) q";

        Object[] params = new Object[] { startDate, endDate };
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
    }

}
