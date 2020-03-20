package aikamapp.controller.criteria;

import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class CostOfPurchasesCriteria implements Criteria {
    @SerializedName("minExpenses")
    private final BigDecimal minCost;
    @SerializedName("maxExpenses")
    private final BigDecimal maxCost;

    public CostOfPurchasesCriteria(BigDecimal minCost, BigDecimal maxCost) {
        super();
        this.minCost = minCost;
        this.maxCost = maxCost;
    }

    public BigDecimal getMinCost() {
        return minCost;
    }

    public BigDecimal getMaxCost() {
        return maxCost;
    }

    @Override
    public List<Buyer> get(BuyerService buyerService) {
        return buyerService.getByCostOfPurchases(minCost, maxCost);
    }
}
