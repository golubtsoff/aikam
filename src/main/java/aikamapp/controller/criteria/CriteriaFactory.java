package aikamapp.controller.criteria;


import aikamapp.controller.criteria.rules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CriteriaFactory {
    private static final List<Rule> RULES = new ArrayList<>();
    private static final Rule NULL_RULE = new NullRule();

    static {
        RULES.add(new LastNameRule());
        RULES.add(new GoodAndCountOfPurchasesRule());
        RULES.add(new CostOfPurchasesRule());
        RULES.add(new PassiveBuyersRule());
    }

    private CriteriaFactory(){}

    public static Criteria process(Map<String, String> rawCriteria) {
        return RULES
            .stream()
            .filter(r -> r.evaluate(rawCriteria))
            .findFirst()
            .orElse(NULL_RULE)
            .getResult();
    }
}
