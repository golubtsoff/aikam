package aikamapp.controller.criteria.rules;

import aikamapp.controller.criteria.Criteria;
import aikamapp.controller.criteria.PassiveBuyersCriteria;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class PassiveBuyersRule extends AbstractRule implements Rule {
    private static final String BAD_CUSTOMERS_KEY_CRITERIA = "badCustomers";

    private static final Predicate<Map<String, String>> condition = map ->
        map.containsKey(BAD_CUSTOMERS_KEY_CRITERIA) && map.size() == 1;

    private static final Function<Map<String, String>, Criteria> mapCriteriaFunction = map -> {
        try {
            return new PassiveBuyersCriteria(Integer.parseInt(map.get(BAD_CUSTOMERS_KEY_CRITERIA)));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Число пассивных покупателей должно быть целым положительным числом", e);
        }
    };


    @Override
    public boolean evaluate(Map<String, String> rawCriteria) {
        return super.evaluate(rawCriteria, condition, mapCriteriaFunction);
    }

    @Override
    public Criteria getResult() {
        return criteria;
    }
}