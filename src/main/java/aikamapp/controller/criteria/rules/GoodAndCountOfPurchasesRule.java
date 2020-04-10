package aikamapp.controller.criteria.rules;

import aikamapp.controller.criteria.Criteria;
import aikamapp.controller.criteria.GoodAndCountOfPurchasesCriteria;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class GoodAndCountOfPurchasesRule extends AbstractRule implements Rule {
    private static final String PRODUCT_NAME_KEY_CRITERIA = "productName";
    private static final String MIN_TIMES_KEY_CRITERIA = "minTimes";

    private static final Predicate<Map<String, String>> condition = map ->
        map.containsKey(PRODUCT_NAME_KEY_CRITERIA)
        && map.containsKey(MIN_TIMES_KEY_CRITERIA )
        && map.size() == 2;

    private static final Function<Map<String, String>, Criteria> mapCriteriaFunction = map -> {
        try{
            return new GoodAndCountOfPurchasesCriteria(
                map.get(PRODUCT_NAME_KEY_CRITERIA),
                Integer.parseInt(map.get(MIN_TIMES_KEY_CRITERIA))
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
