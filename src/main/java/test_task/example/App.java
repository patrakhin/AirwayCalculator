package test_task.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;


public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    private DataProcessor dataProcessor;

    public static void main(String[] args) throws IOException, CustomDataProcessingException {
        App app = new App();
        app.start(args);
    }
    private void start (String[] args) throws IOException, CustomDataProcessingException {
        String filePath;

        if (args.length == 0) {
            // Если аргументы отсутствуют, ввести путь
            logger.info("Введите путь к файлу tickets.json: ");
            filePath = getUserInputPath();
        } else {
            // Иначе, использовать путь из аргументов командной строки
            filePath = args[0];
        }

        List<EntityAirTicket> tickets = IncomingFileReader.readFlightTickets(filePath);

        if (tickets.isEmpty()) {
            // Если список пуст, сообщим об ошибке и бросим новое исключение IOException
            String errorMessage = "Нет данных в файле tickets.json.";
            logger.error(errorMessage);

            // Бросаем новое исключение с сообщением
            throw new EmptyTicketListException(errorMessage);
        }
        // Добавим запись в Excel
        dataProcessor = new DataProcessor();
        dataProcessor.writingData(IncomingFileReader.readFlightTickets(filePath));
    }

    private String getUserInputPath() {
        // Если пользователь вводит путь вручную
        return new java.util.Scanner(System.in).nextLine();
    }
}
