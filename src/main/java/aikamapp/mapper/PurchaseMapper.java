package aikamapp.mapper;

import aikamapp.model.Buyer;
import aikamapp.model.Good;
import aikamapp.model.Purchase;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PurchaseMapper implements RowMapper<Purchase> {
    @Override
    public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("pid");
        LocalDate date = (rs.getDate("date")).toLocalDate();

        Long buyerId = rs.getLong("bid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");

        Long goodId = rs.getLong("gid");
        String title = rs.getString("title");
        BigDecimal price = rs.getBigDecimal("price");

        Buyer buyer = new Buyer(buyerId, firstname, lastname);
        Good good = new Good(goodId, title, price);
        return new Purchase(id, buyer, good, date);
    }
}
