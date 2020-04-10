package aikamapp.controller.criteria.rules;

import aikamapp.controller.criteria.Criteria;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractRule implements Rule {
    protected Criteria criteria = null;

    public boolean evaluate(Map<String, String> rawCriteria,
                             Predicate<Map<String, String>> condition,
                             Function<Map<String, String>, Criteria> mapCriteriaFunction) {

        if (condition.test(rawCriteria)) {
            this.criteria = mapCriteriaFunction.apply(rawCriteria);
            return true;
        }
        return false;
    }
}
