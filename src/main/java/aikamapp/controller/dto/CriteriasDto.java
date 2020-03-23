package aikamapp.controller.dto;

import aikamapp.controller.criteria.Criteria;
import aikamapp.model.Buyer;
import aikamapp.service.BuyerService;

import java.util.ArrayList;
import java.util.List;

public class CriteriasDto {
    private final String type;
    private final List<CriteriaDto> results;

    public static class CriteriaDto{
        public Criteria criteria;
        public List<Buyer> results;
    }

    public CriteriasDto(String type, List<Criteria> criterias, BuyerService service){
        this.type = type;
        results = new ArrayList<>();
        for(Criteria criteria : criterias){
            CriteriaDto criteriaDto = new CriteriaDto();
            criteriaDto.criteria = criteria;
            criteriaDto.results = criteria.get(service);
            results.add(criteriaDto);
        }
    }

    public String getType() {
        return type;
    }

    public List<CriteriaDto> getResults() {
        return results;
    }
}
