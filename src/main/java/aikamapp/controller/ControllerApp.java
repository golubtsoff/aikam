package aikamapp.controller;

import aikamapp.controller.criteria.*;
import aikamapp.controller.dto.CriteriasDto;
import aikamapp.controller.dto.FieldsExclusionStrategy;
import aikamapp.controller.stat.TotalStat;
import aikamapp.service.BuyerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component("controllerApp")
public class ControllerApp {
    private final BuyerService buyerService;
    private final String operation;
    private final String inputFileName;
    private final String outputFileName;

    private final String SEARCH_OPERATION = "search";
    private final String STAT_OPERATION = "stat";
    private final String LAST_NAME_KEY_CRITERIA = "lastName";
    private final String PRODUCT_NAME_KEY_CRITERIA = "productName";
    private final String MIN_TIMES_KEY_CRITERIA = "minTimes";
    private final String MIN_EXPENSES_KEY_CRITERIA = "minExpenses";
    private final String MAX_EXPENSES_KEY_CRITERIA = "maxExpenses";
    private final String BAD_CUSTOMERS_KEY_CRITERIA = "badCustomers";

    static class Search{
        public List<Map<String, String>> criterias;
    }

    static class Stat{
        public String startDate;
        public String endDate;
    }

    public ControllerApp(ApplicationArguments applicationArguments, BuyerService buyerService) {
        operation = applicationArguments.getSourceArgs()[0];
        inputFileName = applicationArguments.getSourceArgs()[1];
        outputFileName = applicationArguments.getSourceArgs()[2];
        this.buyerService = buyerService;
    }

    public void run() throws IOException {
        if (operation.toLowerCase().equals(SEARCH_OPERATION)){
            Search search = getOperation(Search.class);
            List<Criteria> criterias = getCriterias(search);

            try(FileWriter fw = new FileWriter(outputFileName)){
                CriteriasDto criteriasDto = new CriteriasDto(operation, criterias, buyerService);
                Gson dOut = getGsonWithExclusionFields("id");
                dOut.toJson(criteriasDto, fw);
            }
        } else if (operation.toLowerCase().equals(STAT_OPERATION)){
            Stat stat = getOperation(Stat.class);
            TotalStat totalStat = buyerService.getTotalStat(
                    operation,
                    LocalDate.parse(stat.startDate),
                    LocalDate.parse(stat.endDate)
            );
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try(FileWriter fw = new FileWriter(outputFileName)){
                gson.toJson(totalStat, fw);
            }
        } else {
            System.out.println("Операция " + operation + " не поддерживается.");
        }
    }

    private <T> T getOperation(Class<T> cl) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileReader fr = new FileReader(inputFileName)){
            return gson.fromJson(fr, cl);
        }
    }

    private Gson getGsonWithExclusionFields(String... exclusionFields){
        return new GsonBuilder()
                .setPrettyPrinting()
                .setExclusionStrategies(new FieldsExclusionStrategy(
                        exclusionFields))
                .create();
    }

    private List<Criteria> getCriterias(Search search){
        List<Criteria> criterias = new ArrayList<>();
        for(Map<String, String> map : search.criterias){
            Set<String> keySet = map.keySet();
            Criteria criteria = null;
            if (keySet.contains(LAST_NAME_KEY_CRITERIA)){
                criteria = new LastNameCriteria(map.get(LAST_NAME_KEY_CRITERIA));
            } else if (keySet.contains(PRODUCT_NAME_KEY_CRITERIA) && keySet.contains(MIN_TIMES_KEY_CRITERIA)){
                criteria = new GoodAndCountOfPurchasesCriteria(
                        map.get(PRODUCT_NAME_KEY_CRITERIA),
                        Integer.parseInt(map.get(MIN_TIMES_KEY_CRITERIA))
                );
            } else if (keySet.contains(MIN_EXPENSES_KEY_CRITERIA) && keySet.contains(MAX_EXPENSES_KEY_CRITERIA)){
                criteria = new CostOfPurchasesCriteria(
                        new BigDecimal(map.get(MIN_EXPENSES_KEY_CRITERIA)),
                        new BigDecimal(map.get(MAX_EXPENSES_KEY_CRITERIA))
                );
            } else if (keySet.contains(BAD_CUSTOMERS_KEY_CRITERIA)){
                criteria = new PassiveBuyersCriteria(Integer.parseInt(map.get(BAD_CUSTOMERS_KEY_CRITERIA)));
            }
            if (criteria != null){
                criterias.add(criteria);
            }
        }
        return criterias;
    }

}
