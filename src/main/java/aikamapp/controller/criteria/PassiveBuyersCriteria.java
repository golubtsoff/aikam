package aikamapp.controller.criteria;

import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;

import java.util.List;

public class PassiveBuyersCriteria implements Criteria {
    private final int limit;

    public PassiveBuyersCriteria(int limit) {
        super();
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public List<Buyer> get(BuyerService buyerService) {
        return buyerService.getPassiveBuyers(limit);
    }
}
