package aikamapp.dao;

import aikamapp.mapper.GoodMapper;
import aikamapp.mapper.PurchaseMapper;
import aikamapp.model.Good;
import aikamapp.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
}
