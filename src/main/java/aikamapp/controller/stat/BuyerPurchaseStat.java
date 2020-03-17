package aikamapp.controller.stat;

import aikamapp.model.Buyer;

import java.util.Objects;

public class BuyerPurchaseStat {
    private final Buyer buyer;
    private final GoodsSale goodsSales;

    public BuyerPurchaseStat(Buyer buyer, GoodsSale goodsSales) {
        this.buyer = buyer;
        this.goodsSales = goodsSales;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public GoodsSale getGoodsSales() {
        return goodsSales;
    }

    @Override
    public String toString() {
        return "BuyerPurchaseStat{" +
                "buyer=" + buyer +
                ", goodsSales=" + goodsSales +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerPurchaseStat that = (BuyerPurchaseStat) o;
        return buyer.equals(that.buyer) &&
                goodsSales.equals(that.goodsSales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyer, goodsSales);
    }
}
