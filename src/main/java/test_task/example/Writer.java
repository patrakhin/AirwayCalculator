package test_task.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Writer {
    private static final Logger logger = LogManager.getLogger(Writer.class);
    private Writer(){
        throw new IllegalStateException("Writer class");
    }
    public static void writeStatisticsToExcel(Map<String, String> statisticPrice, List<DTO> sortedCarrier) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            // Создаем лист для данных
            Sheet sheet = workbook.createSheet("Data and Statistics");

            // Заполняем лист данными
            fillDataSheet(sheet, sortedCarrier);

            // Добавляем пустую строку между таблицами
            sheet.createRow(sortedCarrier.size() + 2);

            // Заполняем лист статистикой
            fillStatisticsSheet(sheet, statisticPrice, sortedCarrier.size() + 3);

            // Сохраняем Excel-файл
            try (FileOutputStream fileOut = new FileOutputStream("AirwayData.xlsx")) {
                workbook.write(fileOut);
            }
            logger.info("Excel файл успешно создан!");
        } catch (IOException e) {
            logger.error("Ошибка при создании и записи файла AirwayData.xlsx: {}", e.getMessage());
        }
    }

    private static void fillDataSheet(Sheet sheet, List<DTO> sortedList) {
        // Создаем заголовки
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Carrier");
        headerRow.createCell(1).setCellValue("Time Flight");

        // Заполняем данными
        for (int i = 0; i < sortedList.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(sortedList.get(i).carrier());
            row.createCell(1).setCellValue(sortedList.get(i).flightTime());
        }
    }

    private static void fillStatisticsSheet(Sheet sheet, Map<String, String> statisticPrice, int rowIndex) {
        // Создаем заголовки
        Row headerRow = sheet.createRow(rowIndex);
        headerRow.createCell(0).setCellValue("Statistic");
        headerRow.createCell(1).setCellValue("Value");

        // Заполняем данными
        rowIndex++;
        for (Map.Entry<String, String> entry : statisticPrice.entrySet()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }
    }
}
