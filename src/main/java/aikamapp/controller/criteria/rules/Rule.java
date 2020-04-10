package aikamapp.controller.criteria.rules;

import aikamapp.controller.criteria.Criteria;

import java.util.Map;

public interface Rule {
    boolean evaluate(Map<String, String> rawCriteria);
    Criteria getResult();
}
