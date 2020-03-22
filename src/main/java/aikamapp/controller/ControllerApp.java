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
import java.time.format.DateTimeParseException;
import java.util.*;

@Component("controllerApp")
public class ControllerApp {
    private final BuyerService buyerService;
    private final String operation;
    private final String inputFileName;
    private final String outputFileName;

    private final String SEARCH_OPERATION = "search";
    private final String STAT_OPERATION = "stat";
    private final String ERROR_TYPE = "error";
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

    private class Error{
        public final String type = ERROR_TYPE;
        public final String message;

        Error(String message){
            this.message = message;
        }
    }

    public ControllerApp(ApplicationArguments applicationArguments, BuyerService buyerService) {
        operation = applicationArguments.getSourceArgs()[0];
        inputFileName = applicationArguments.getSourceArgs()[1];
        outputFileName = applicationArguments.getSourceArgs()[2];
        this.buyerService = buyerService;
    }

    public void run() throws IOException {
        try{
            if (operation.toLowerCase().equals(SEARCH_OPERATION)){
                Search search = getOperation(Search.class);
                List<Criteria> criterias = getCriterias(search);
                CriteriasDto criteriasDto = new CriteriasDto(operation, criterias, buyerService);
                writeToFile(criteriasDto, "id");
            } else if (operation.toLowerCase().equals(STAT_OPERATION)){
                try {
                    Stat stat = getOperation(Stat.class);
                    if (stat.startDate == null || stat.endDate == null){
                        throw new Exception("Неправильный формат файла или названий минимальной и максимальной дат");
                    }
                    TotalStat totalStat = buyerService.getTotalStat(
                            operation,
                            LocalDate.parse(stat.startDate),
                            LocalDate.parse(stat.endDate)
                    );
                    writeToFile(totalStat);
                } catch (DateTimeParseException e){
                    throw new Exception("Неправильный формат даты", e);
                }
            } else {
                throw new Exception("Операция " + operation + " не поддерживается");
            }
        } catch (Exception e){
            String message = e.getMessage() == null ? e.toString() : e.getMessage();
            writeToFile(new Error(message));
        }
    }

    private <T> T getOperation(Class<T> cl) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileReader fr = new FileReader(inputFileName)){
            return gson.fromJson(fr, cl);
        }
    }

    private <T> void writeToFile(T obj, String ... exclusionFields) throws IOException {
        Gson gson = getGsonWithExclusionFields(exclusionFields);
        try(FileWriter fw = new FileWriter(outputFileName)){
            gson.toJson(obj, fw);
        }
    }

    private Gson getGsonWithExclusionFields(String... exclusionFields){
        return new GsonBuilder()
                .setPrettyPrinting()
                .setExclusionStrategies(new FieldsExclusionStrategy(
                        exclusionFields))
                .create();
    }

    private List<Criteria> getCriterias(Search search) throws Exception {
        if (search.criterias == null){
            throw new Exception("Отсутствуют критерии для выборки");
        }
        List<Criteria> criterias = new ArrayList<>();
        for(Map<String, String> map : search.criterias){
            Set<String> keySet = map.keySet();
            Criteria criteria = null;
            if (keySet.contains(LAST_NAME_KEY_CRITERIA) && keySet.size() == 1){
                criteria = new LastNameCriteria(map.get(LAST_NAME_KEY_CRITERIA));
            } else if (keySet.contains(PRODUCT_NAME_KEY_CRITERIA) && keySet.contains(MIN_TIMES_KEY_CRITERIA ) && keySet.size() == 2){
                try{
                    criteria = new GoodAndCountOfPurchasesCriteria(
                            map.get(PRODUCT_NAME_KEY_CRITERIA),
                            Integer.parseInt(map.get(MIN_TIMES_KEY_CRITERIA))
                    );
                } catch (NumberFormatException e){
                    throw new Exception("Число покупок должно быть целым неотрицательным числом", e);
                }
            } else if (keySet.contains(MIN_EXPENSES_KEY_CRITERIA) && keySet.contains(MAX_EXPENSES_KEY_CRITERIA) && keySet.size() == 2){
                try{
                    criteria = new CostOfPurchasesCriteria(
                            new BigDecimal(map.get(MIN_EXPENSES_KEY_CRITERIA)),
                            new BigDecimal(map.get(MAX_EXPENSES_KEY_CRITERIA))
                    );
                } catch (NumberFormatException e){
                    throw new Exception("Число покупок должно быть целым неотрицательным числом", e);
                }
            } else if (keySet.contains(BAD_CUSTOMERS_KEY_CRITERIA) && keySet.size() == 1){
                try{
                    criteria = new PassiveBuyersCriteria(Integer.parseInt(map.get(BAD_CUSTOMERS_KEY_CRITERIA)));
                } catch (NumberFormatException e){
                    throw new Exception("Число пассивных покупателей должно быть целым положительным числом", e);
                }
            }
            if (criteria != null){
                criterias.add(criteria);
            } else {
                throw new Exception("Неизвестный критерий выборки: " + keySet.toString());
            }
        }
        return criterias;
    }
}
