package aikamapp.dao;

import aikamapp.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository("goodDao")
public class GoodDao extends JdbcDaoSupport {

    @Autowired
    public GoodDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    private final RowMapper<Good> GOOD_MAPPER = (ResultSet rs, int rowNum) -> new Good(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getBigDecimal("price")
    );

    public Good get(Long id){
        String sql = "select * from goods g\n" +
                "where g.id = ?";

        Object[] params = new Object[] { id };
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, GOOD_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Good> getAll(){
        String sql = "select * from goods g";

        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().query(sql, GOOD_MAPPER);
    }
}
