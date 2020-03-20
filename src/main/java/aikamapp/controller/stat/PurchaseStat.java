package aikamapp.controller.stat;

import aikamapp.model.Buyer;

import java.util.Objects;

public class PurchaseStat {
    private final Buyer buyer;
    private final GoodSale goodSales;

    public PurchaseStat(Buyer buyer, GoodSale goodSales) {
        this.buyer = buyer;
        this.goodSales = goodSales;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public GoodSale getGoodsSale() {
        return goodSales;
    }

    @Override
    public String toString() {
        return "PurchaseStat{" +
                "buyer=" + buyer +
                ", goodsSales=" + goodSales +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseStat that = (PurchaseStat) o;
        return buyer.equals(that.buyer) &&
                goodSales.equals(that.goodSales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer, goodSales);
    }
}
