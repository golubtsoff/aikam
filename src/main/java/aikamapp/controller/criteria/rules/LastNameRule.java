package aikamapp.controller.criteria.rules;

import aikamapp.controller.criteria.Criteria;
import aikamapp.controller.criteria.LastNameCriteria;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class LastNameRule extends AbstractRule {
    private static final String LAST_NAME_KEY_CRITERIA = "lastName";

    private static final Predicate<Map<String, String>> condition = map ->
        map.containsKey(LAST_NAME_KEY_CRITERIA) && map.size() == 1;

    private static final Function<Map<String, String>, Criteria> mapCriteriaFunction = map ->
        new LastNameCriteria(map.get(LAST_NAME_KEY_CRITERIA));

    @Override
    public boolean evaluate(Map<String, String> rawCriteria) {
        return super.evaluate(rawCriteria, condition, mapCriteriaFunction);
    }

    @Override
    public Criteria getResult() {
        return criteria;
    }
}
