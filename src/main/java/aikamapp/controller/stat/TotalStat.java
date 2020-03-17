package aikamapp.controller.stat;

import java.math.BigDecimal;
import java.util.Set;

public class TotalStat {
    private final int totalDays;
    private final Set<BuyerStat> buyerStats;
    private final BigDecimal totalCost;
    private final BigDecimal avgCost;

    public TotalStat(int totalDays, Set<BuyerStat> buyerStats, BigDecimal totalCost, BigDecimal avgCost) {
        this.totalDays = totalDays;
        this.buyerStats = buyerStats;
        this.totalCost = totalCost;
        this.avgCost = avgCost;
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
