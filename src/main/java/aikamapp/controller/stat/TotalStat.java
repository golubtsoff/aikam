package aikamapp.controller.stat;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Set;

public class TotalStat {
    private final String type;
    private final int totalDays;
    @SerializedName("customers")
    private final Set<BuyerStat> buyerStats;
    @SerializedName("totalExpenses")
    private final BigDecimal totalCost;
    @SerializedName("avgExpenses")
    private final BigDecimal avgCost;

    public TotalStat(String type, int totalDays, Set<BuyerStat> buyerStats, BigDecimal totalCost, BigDecimal avgCost) {
        this.type = type;
        this.totalDays = totalDays;
        this.buyerStats = buyerStats;
        this.totalCost = totalCost;
        this.avgCost = avgCost;
    }

    public String getType() {
        return type;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public Set<BuyerStat> getBuyerStats() {
        return buyerStats;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public BigDecimal getAvgCost() {
        return avgCost;
    }

    @Override
    public String toString() {
        return "TotalStat{" +
                "totalDays=" + totalDays +
                ", buyerStats=" + buyerStats +
                ", totalCost=" + totalCost +
                ", avgCost=" + avgCost +
                '}';
    }
}
