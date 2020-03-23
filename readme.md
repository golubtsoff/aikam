# Тестовое задание на вакансию java junior developer в ООО "Айкам"     
Автор решения - Голубцов Евгений    
Дата - 23.03.2020   

## Постановка задачи
Тестовое задание на соискателя `Java Junior Developer` находится в файле `test_Java_Junior_Developer.pdf`   

## Используемый стек
- Java 8
- Spring
- Maven
- PostgreSQL

## Сборка приложения
Сборка приложения выполняется с помощью `Maven` (Lifecycle `package`).
В результате сборки в папке `target` создаётся файл `aikam.jar`.

## Тестирование приложения
Перед запуском приложения необходимо развернуть базу данных (используется PostgreSQL).    
Дамп тестовой БД находится в папке `db` в файле `aikam.sql`.    
БД должна быть доступна со следующими параметрами:
- `url=jdbc:postgresql://localhost:5432/aikam`
- `username=user`
- `password=password`   

При использовании параметров доступа к БД, отличных от указанных, необходимо их прописать в файле `src/main/resourses/application.properties` и собрать `jar-файл` приложения заново.    

Файлы, подготовленные для демонстрации работы приложения, находятся в папке `test_files`.   
Запуск приложения осуществляется из командной строки. Примеры ввода команды запуска приложения:
- `java -jar aikam.jar search input_search.json output.json`;
- `java -jar aikam.jar stat input_stat.json output.json`.

Ключи запуска приложения:
- `aikam.jar` - файл приложения
- `search` (`stat`) - тип операции;
- `input_search.json` (`input_stat.json`) - входной файл с описанием соответствующей операции (`search`, `stat`);
- `output.json` - файл вывода результата работы приложения

