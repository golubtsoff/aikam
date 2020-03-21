package aikamapp.controller.stat;

import aikamapp.model.Good;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class GoodSale {
    @SerializedName("name")
    private final String goodName;
    @SerializedName("expenses")
    private final BigDecimal cost;

    public GoodSale(Good good, BigDecimal cost) {
        this.goodName = good.getTitle();
        this.cost = cost;
    }

    public String getGood() {
        return goodName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "GoodsSale{" +
                "goodName=" + goodName +
                ", cost=" + cost +
                '}';
    }

}
