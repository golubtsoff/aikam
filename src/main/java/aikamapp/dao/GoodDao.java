package aikamapp.dao;

import aikamapp.mapper.GoodMapper;
import aikamapp.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("goodDao")
public class GoodDao extends JdbcDaoSupport {

    @Autowired
    public GoodDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Good get(Long id){
        String sql = "select * from goods g\n" +
                "where g.id = ?";

        Object[] params = new Object[] { id };
        GoodMapper mapper = new GoodMapper();
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Good> getAll(){
        String sql = "select * from goods g";

        GoodMapper mapper = new GoodMapper();
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, mapper);
    }
}
