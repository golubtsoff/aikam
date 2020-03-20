package aikamapp.controller.stat;

import aikamapp.model.Good;

import java.math.BigDecimal;
import java.util.Objects;

public class GoodSale {
    private final Good good;
    private final BigDecimal cost;

    public GoodSale(Good good, BigDecimal cost) {
        this.good = good;
        this.cost = cost;
    }

    public Good getGood() {
        return good;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "GoodsSale{" +
                "good=" + good +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodSale goodSale = (GoodSale) o;
        return good.equals(goodSale.good);
    }

    @Override
    public int hashCode() {
        return Objects.hash(good);
    }
}
