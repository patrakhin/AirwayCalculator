package test_task.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsPrice {
    public static Map<String, String> getStatisticPrice(List<DTO> sortedCarrier) {
        // Извлекаем цены в список
        List<Double> prices = sortedCarrier.stream()
                .map(DTO::getRoundedPrice)
                .collect(Collectors.toList());

        // Вычисляем среднюю цену
        double average = prices.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        // Вычисляем медиану
        double median;
        int size = prices.size();
        if (size % 2 == 0) {
            median = (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            median = prices.get(size / 2);
        }

        // Вычисляем разницу между средней ценой и медианой
        double difference = Math.abs(average - median);

        // Создаем Map
        return Map.of(
                "Average", String.format("%.2f", average),
                "Median", String.format("%.2f", median),
                "Difference", String.format("%.2f", difference)
        );
    }
}
