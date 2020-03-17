package aikamapp.controller.stat;

import aikamapp.model.Good;

import java.math.BigDecimal;
import java.util.Objects;

public class GoodsSale {
    private final Good good;
    private final BigDecimal cost;

    public GoodsSale(Good good, BigDecimal cost) {
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
        GoodsSale goodsSale = (GoodsSale) o;
        return good.equals(goodsSale.good);
    }

    @Override
    public int hashCode() {
        return Objects.hash(good);
    }
}
