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

    private List<Criteria> getCriteriaList(Search search) {
        Objects.requireNonNull(search.criterias, "Отсутствуют критерии для выборки");
        List<Criteria> criteriaList = new ArrayList<>();
        for(Map<String, String> map : search.criterias){
            Criteria criteria = CriteriaFactory.process(map);
            Objects.requireNonNull(criteria, "Неизвестный критерий выборки: " + map.keySet().toString());
            criteriaList.add(criteria);
        }
        return criteriaList;
    }
}
