package test_task.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

    private static final Logger logger = LogManager.getLogger(ExcelWriter.class);

    public static void writeDataToExcel(List<EntityAirTicket> tickets, String origin_name, String destination_name) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("AirwayData");

            writeMinFlightTimes(sheet, tickets, origin_name, destination_name);
            writePriceStats(sheet, tickets, origin_name, destination_name);

            // Сохранение в файл
            try (FileOutputStream fileOut = new FileOutputStream("AirwayData.xlsx")) {
                workbook.write(fileOut);
            }

            logger.info("Данные успешно записаны в AirwayData.xlsx.");
        } catch (IOException e) {
            logger.error("Ошибка при записи в файл: {}", e.getMessage());
            throw e; // Пробросим исключение для обработки в вызывающем коде
        }
    }

    private static void writeMinFlightTimes(Sheet sheet, List<EntityAirTicket> tickets, String origin, String destination) {
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Авиа оператор");
        headerRow.createCell(1).setCellValue("Минимальное время полета между " + origin + " и " + destination);

        Map<String, String> minFlightTimes = MinFlightTimeCalculator.calculateMinFlightTime(tickets, origin, destination);

        int rowNum = 1;
        for (Map.Entry<String, String> entry : minFlightTimes.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }
    }

    private static void writePriceStats(Sheet sheet, List<EntityAirTicket> tickets, String origin, String destination) {
        Row headerRow = sheet.createRow(sheet.getLastRowNum() + 2);
        headerRow.createCell(0).setCellValue("Разница между средней ценой и медианой для полета между " + origin + " и " + destination);
        headerRow.createCell(1).setCellValue("Средняя цена");
        headerRow.createCell(2).setCellValue("Медиана");

        Map<String, String> priceStats = PriceStatsCalculator.calculatePriceStats(tickets, origin, destination);

        Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
        dataRow.createCell(0).setCellValue(priceStats.get("difference"));
        dataRow.createCell(1).setCellValue(priceStats.get("average"));
        dataRow.createCell(2).setCellValue(priceStats.get("median"));
    }
}
