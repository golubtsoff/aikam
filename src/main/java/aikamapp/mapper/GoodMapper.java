package aikamapp.mapper;

import aikamapp.model.Good;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodMapper implements RowMapper<Good> {
    @Override
    public Good mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String title = rs.getString("title");
        BigDecimal price = rs.getBigDecimal("price");
        return new Good(id, title, price);
    }
}
