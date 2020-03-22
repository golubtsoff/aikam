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

    public CostOfPurchasesCriteria(BigDecimal minCost, BigDecimal maxCost) throws Exception {
        super();
        this.minCost = minCost;
        this.maxCost = maxCost;

        if (minCost.compareTo(new BigDecimal(0)) < 0 || maxCost.compareTo(new BigDecimal(0)) < 0){
            throw new Exception("Минимальная и максимальная стоимости покупок не могут быть отрицательными");
        }
        if (minCost.compareTo(maxCost) > 0){
            throw new Exception("Минимальная стоимость покупок не должна превышать максимальную");
        }
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
