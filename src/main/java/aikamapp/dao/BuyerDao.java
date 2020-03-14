package aikamapp.dao;

import aikamapp.mapper.BuyerMapper;
import aikamapp.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

}
