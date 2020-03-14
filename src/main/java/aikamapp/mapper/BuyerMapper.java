package aikamapp.mapper;

import aikamapp.model.Buyer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerMapper implements RowMapper<Buyer> {
    @Override
    public Buyer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        return new Buyer(id, firstname, lastname);
    }
}
