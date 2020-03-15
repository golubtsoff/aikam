package aikamapp.dao;

import aikamapp.mapper.PurchaseMapper;
import aikamapp.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository("purchaseDao")
public class PurchaseDao extends JdbcDaoSupport {

    @Autowired
    public PurchaseDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Purchase get(Long id){
        String sql = "select p.id pid, p.date,\n" +
                "       b.id bid, b.firstname, b.lastname,\n" +
                "       g.id gid, g.title, g.price\n" +
                "from buyers b, goods g, purchases p\n" +
                "where p.id = ?\n" +
                "and p.buyer_id = b.id\n" +
                "and p.good_id = g.id";

        Object[] params = new Object[] { id };
        PurchaseMapper mapper = new PurchaseMapper();
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, mapper);
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

        PurchaseMapper mapper = new PurchaseMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper);
    }

    public int create(long buyerId, long goodId, LocalDate date){
        String sql = "insert into purchases (buyer_id, good_id, date) " +
                "values (?, ?, ?)";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Object[] params = new Object[] { buyerId, goodId, date};

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().update(sql, params);
    }

    public int create(long buyerId, long goodId){
        return create(buyerId, goodId, LocalDate.now());
    }
}
