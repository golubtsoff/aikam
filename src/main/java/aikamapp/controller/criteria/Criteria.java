package aikamapp.controller.criteria;

import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;

import java.util.List;

public interface Criteria {
    List<Buyer> get(BuyerService buyerService);
}
