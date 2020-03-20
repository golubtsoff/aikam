package aikamapp.controller.criteria;

import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;

import java.util.List;

public class LastNameCriteria implements Criteria {
    private final String lastName;

    public LastNameCriteria(String lastName) {
        super();
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public List<Buyer> get(BuyerService buyerService) {
        return buyerService.getByLastname(lastName);
    }
}
