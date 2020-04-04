package aikamapp.dao;

import aikamapp.model.Buyer;
import aikamapp.model.Good;
import aikamapp.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@Repository("purchaseDao")
public class PurchaseDao extends JdbcDaoSupport {

    @Autowired
    public PurchaseDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private final RowMapper<Purchase> PURCHASE_MAPPER = (ResultSet rs, int rowNum) -> {
        Long id = rs.getLong("pid");
        LocalDate date = (rs.getDate("date")).toLocalDate();

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
        return new Purchase(id, buyer, good, date);
    };

    public Purchase get(Long id){
        String sql = "select p.id pid, p.date,\n" +
                "       b.id bid, b.firstname, b.lastname,\n" +
                "       g.id gid, g.title, g.price\n" +
                "from buyers b, goods g, purchases p\n" +
                "where p.id = ?\n" +
                "and p.buyer_id = b.id\n" +
                "and p.good_id = g.id";

        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, PURCHASE_MAPPER, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Purchase> getAll(){
        String sql = "select p.id pid, p.date,\n" +
                "       b.id bid, b.firstname, b.lastname,\n" +
                "       g.id gid, g.title, g.price\n" +
                "from buyers b, goods g, purchases p\n" +
                "where p.buyer_id = b.id\n" +
                "and p.good_id = g.id";

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, PURCHASE_MAPPER);
    }

    public int create(long buyerId, long goodId, LocalDate date){
        String sql = "insert into purchases (buyer_id, good_id, date) " +
                "values (?, ?, ?)";

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().update(sql, buyerId, goodId, date);
    }

    public int create(long buyerId, long goodId){
        return create(buyerId, goodId, LocalDate.now());
    }
}
