package aikamapp.controller;

import aikamapp.controller.criteria.*;
import aikamapp.controller.dto.CriteriasDto;
import aikamapp.controller.dto.FieldsExclusionStrategy;
import aikamapp.controller.stat.TotalStat;
import aikamapp.service.BuyerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@Component("controllerApp")
public class ControllerApp {
    private final BuyerService buyerService;
    private final String operation;
    private final String inputFileName;
    private final String outputFileName;

    private static final String SEARCH_OPERATION = "search";
    private static final String STAT_OPERATION = "stat";
    private static final String ERROR_TYPE = "error";
    private static final String LAST_NAME_KEY_CRITERIA = "lastName";
    private static final String PRODUCT_NAME_KEY_CRITERIA = "productName";
    private static final String MIN_TIMES_KEY_CRITERIA = "minTimes";
    private static final String MIN_EXPENSES_KEY_CRITERIA = "minExpenses";
    private static final String MAX_EXPENSES_KEY_CRITERIA = "maxExpenses";
    private static final String BAD_CUSTOMERS_KEY_CRITERIA = "badCustomers";

    private static class Search{
        private List<Map<String, String>> criterias;
    }

    private static class Stat{
        private String startDate;
        private String endDate;
    }

    private static class Error{
        private static final String TYPE = ERROR_TYPE;
        private final String message;

        Error(String message){
            this.message = message;
        }
    }

    public ControllerApp(ApplicationArguments applicationArguments, BuyerService buyerService) throws Exception {
        try {
            operation = applicationArguments.getSourceArgs()[0];
            inputFileName = applicationArguments.getSourceArgs()[1];
            outputFileName = applicationArguments.getSourceArgs()[2];
            this.buyerService = buyerService;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new Exception("Ошибка в указании аргументов приложения", e);
        }
    }

    public void run() throws Exception {
        try{
            if (operation.equalsIgnoreCase(SEARCH_OPERATION)){
                Search search = getOperation(Search.class);
                List<Criteria> criterias = getCriteriaList(search);
                CriteriasDto criteriasDto = new CriteriasDto(operation, criterias, buyerService);
                writeToFile(criteriasDto, "id");
            } else if (operation.equalsIgnoreCase(STAT_OPERATION)){
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
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(inputFileName), StandardCharsets.UTF_8))){
            return gson.fromJson(reader, cl);
        } catch (FileNotFoundException e){
            throw new Exception("Входной файл не найден", e);
        }
    }

    private <T> void writeToFile(T obj, String ... exclusionFields) throws Exception {
        Gson gson = getGsonWithExclusionFields(exclusionFields);
        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(outputFileName), StandardCharsets.UTF_8))){
            gson.toJson(obj, writer);
        } catch (FileNotFoundException e){
            throw new Exception("Отказано в доступе к выходному файлу", e);
        }
    }

    private Gson getGsonWithExclusionFields(String... exclusionFields){
        return new GsonBuilder()
                .setPrettyPrinting()
                .setExclusionStrategies(new FieldsExclusionStrategy(
                        exclusionFields))
                .create();
    }

    private List<Criteria> getCriteriaList(Search search) throws Exception {
        if (search.criterias == null){
            throw new Exception("Отсутствуют критерии для выборки");
        }
        List<Criteria> criteriaList = new ArrayList<>();
        for(Map<String, String> map : search.criterias){
            Criteria criteria = getCriteria(map);
            if (criteria != null){
                criteriaList.add(criteria);
            } else {
                throw new Exception("Неизвестный критерий выборки: " + map.keySet().toString());
            }
        }
        return criteriaList;
    }

    private Criteria getCriteria(Map<String, String> rawCriteria) throws Exception {
        Set<String> keySet = rawCriteria.keySet();
        if (keySet.contains(LAST_NAME_KEY_CRITERIA) && keySet.size() == 1){
            return getLastNameCriteria(rawCriteria);
        } else if (keySet.contains(PRODUCT_NAME_KEY_CRITERIA) && keySet.contains(MIN_TIMES_KEY_CRITERIA ) && keySet.size() == 2){
            return getGoodAndCountOfPurchasesCriteria(rawCriteria);
        } else if (keySet.contains(MIN_EXPENSES_KEY_CRITERIA) && keySet.contains(MAX_EXPENSES_KEY_CRITERIA) && keySet.size() == 2){
            return getCostOfPurchasesCriteria(rawCriteria);
        } else if (keySet.contains(BAD_CUSTOMERS_KEY_CRITERIA) && keySet.size() == 1){
            return getPassiveBuyersCriteria(rawCriteria);
        } else {
            return null;
        }
    }

    private Criteria getLastNameCriteria(Map<String, String> rawCriteria){
        return new LastNameCriteria(rawCriteria.get(LAST_NAME_KEY_CRITERIA));
    }

    private Criteria getGoodAndCountOfPurchasesCriteria(Map<String, String> rawCriteria) throws Exception {
        try{
            return new GoodAndCountOfPurchasesCriteria(
                    rawCriteria.get(PRODUCT_NAME_KEY_CRITERIA),
                    Integer.parseInt(rawCriteria.get(MIN_TIMES_KEY_CRITERIA))
            );
        } catch (NumberFormatException e){
            throw new Exception("Число покупок должно быть целым неотрицательным числом", e);
        }
    }

    private Criteria getCostOfPurchasesCriteria(Map<String, String> rawCriteria) throws Exception {
        try{
            return new CostOfPurchasesCriteria(
                    new BigDecimal(rawCriteria.get(MIN_EXPENSES_KEY_CRITERIA)),
                    new BigDecimal(rawCriteria.get(MAX_EXPENSES_KEY_CRITERIA))
            );
        } catch (NumberFormatException e){
            throw new Exception("Число покупок должно быть целым неотрицательным числом", e);
        }
    }

    private Criteria getPassiveBuyersCriteria(Map<String, String> rawCriteria) throws Exception {
        try{
            return new PassiveBuyersCriteria(Integer.parseInt(rawCriteria.get(BAD_CUSTOMERS_KEY_CRITERIA)));
        } catch (NumberFormatException e){
            throw new Exception("Число пассивных покупателей должно быть целым положительным числом", e);
        }
    }
}
