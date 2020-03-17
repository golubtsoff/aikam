package aikamapp.controller.criteria;

import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;

import java.util.List;

public class LastNameCriteria implements Criteria {
    private final String lastname;

    public LastNameCriteria(String lastname) {
        super();
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public List<Buyer> get(BuyerService buyerService) {
        return buyerService.getByLastname(lastname);
    }
}
