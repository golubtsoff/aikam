package aikamapp.controller.stat;

import aikamapp.model.Buyer;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BuyerStat {
    @SerializedName("name")
    private final String buyerName;
    @SerializedName("purchases")
    private final List<GoodSale> goodSales;
    @SerializedName("totalExpenses")
    private BigDecimal totalCost;

    public BuyerStat(Buyer buyer) {
        this.buyerName = buyer.getLastName().concat(" ").concat(buyer.getFirstName());
        this.goodSales = new ArrayList<>();
        this.totalCost = BigDecimal.ZERO;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public List<GoodSale> getGoodSales() {
        return goodSales;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void addSale(GoodSale sale) {
        goodSales.add(sale);
        totalCost = totalCost.add(sale.getCost());
    }

}
