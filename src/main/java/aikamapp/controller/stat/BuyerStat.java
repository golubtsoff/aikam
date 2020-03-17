package aikamapp.controller.stat;

import aikamapp.model.Buyer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BuyerStat {
    private final Buyer buyer;
    private final Set<GoodsSale> goodsSales;
    private final BigDecimal totalCost;

    public BuyerStat(Buyer buyer, Set<GoodsSale> goodsSales, BigDecimal totalCost) {
        this.buyer = buyer;
        this.goodsSales = goodsSales;
        this.totalCost = totalCost;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Set<GoodsSale> getGoodsSales() {
        return goodsSales;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
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
