package aikamapp.mapper;

import aikamapp.controller.stat.BuyerPurchaseStat;
import aikamapp.controller.stat.GoodsSale;
import aikamapp.model.Buyer;
import aikamapp.model.Good;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerPurchaseStatMapper implements RowMapper<BuyerPurchaseStat> {
    @Override
    public BuyerPurchaseStat mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long bid = rs.getLong("bid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Buyer buyer = new Buyer(bid, firstname, lastname);

        Long gid = rs.getLong("bid");
        String title = rs.getString("title");
        BigDecimal price = rs.getBigDecimal("price");
        Good good = new Good(gid, title, price);

        BigDecimal cost = rs.getBigDecimal("cost");
        GoodsSale goodsSale = new GoodsSale(good, cost);

        return new BuyerPurchaseStat(buyer, goodsSale);
    }
}
