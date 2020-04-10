package aikamapp.controller.criteria.rules;

import aikamapp.controller.criteria.Criteria;

import java.util.Map;

public class NullRule extends AbstractRule implements Rule {
    @Override
    public boolean evaluate(Map<String, String> rawCriteria) {
        return false;
    }

    @Override
    public Criteria getResult() {
        return null;
    }
}
