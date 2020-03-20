package aikamapp.controller.stat;

import aikamapp.model.Buyer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuyerStat {
    private final Buyer buyer;
    private List<GoodSale> goodSales;
    private BigDecimal totalCost;

    public BuyerStat(Buyer buyer) {
        this.buyer = buyer;
        this.goodSales = new ArrayList<>();
        this.totalCost = new BigDecimal(0);
    }

    public Buyer getBuyer() {
        return buyer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerStat buyerStat = (BuyerStat) o;
        return buyer.equals(buyerStat.buyer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer);
    }
}
