package aikamapp.controller;

import aikamapp.controller.criteria.*;
import aikamapp.ignore.TestJson;
import aikamapp.service.BuyerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component("controllerApp")
public class ControllerApp {
    @Autowired
    private BuyerService buyerService;
    private final String operation;
    private final String inputFile;
    private final String outputFile;

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
        public LocalDate startDate;
        public LocalDate endDate;
    }

    ControllerApp(ApplicationArguments applicationArguments) {
        operation = applicationArguments.getSourceArgs()[0];
        inputFile = applicationArguments.getSourceArgs()[1];
        outputFile = applicationArguments.getSourceArgs()[2];
    }

    public void run() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        if (operation.toLowerCase().equals(SEARCH_OPERATION)){
            Search search;
            try(FileReader fr = new FileReader("input.json")){
                search = gson.fromJson(fr, Search.class);
            }
            List<Criteria> criterias = getCriterias(search);
            for (Criteria criteria : criterias){
                System.out.println(criteria.get(buyerService));
            }
        } else if (operation.toLowerCase().equals(STAT_OPERATION)){
            try(FileReader fr = new FileReader("input.json")){
                Stat stat = gson.fromJson(fr, Stat.class);
                System.out.println(stat);
            }
        } else {
            System.out.println("Операция " + operation + " не поддерживается.");
        }
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
