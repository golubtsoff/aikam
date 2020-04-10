package aikamapp.controller.criteria.rules;

import aikamapp.controller.criteria.CostOfPurchasesCriteria;
import aikamapp.controller.criteria.Criteria;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class CostOfPurchasesRule extends AbstractRule implements Rule {

    private static final String MIN_EXPENSES_KEY_CRITERIA = "minExpenses";
    private static final String MAX_EXPENSES_KEY_CRITERIA = "maxExpenses";

    private static final Predicate<Map<String, String>> condition = map ->
        map.containsKey(MIN_EXPENSES_KEY_CRITERIA)
        && map.containsKey(MAX_EXPENSES_KEY_CRITERIA)
        && map.size() == 2;

    private static final Function<Map<String, String>, Criteria> mapCriteriaFunction = map -> {
        try{
            return new CostOfPurchasesCriteria(
                new BigDecimal(map.get(MIN_EXPENSES_KEY_CRITERIA)),
                new BigDecimal(map.get(MAX_EXPENSES_KEY_CRITERIA))
            );
        } catch (NumberFormatException e){
            throw new RuntimeException("Число покупок должно быть целым неотрицательным числом", e);
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
