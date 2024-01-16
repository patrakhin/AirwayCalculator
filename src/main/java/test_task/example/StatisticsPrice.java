package test_task.example;

import java.util.List;
import java.util.Map;

public class StatisticsPrice {
    private StatisticsPrice() {
        throw new IllegalStateException("StatisticsPrice class");
    }

    public static Map<String, String> getStatisticPrice(List<DTO> sortedCarrier) {
        // Извлекаем цены в список
        List<Double> prices = sortedCarrier.stream()
                .map(DTO::roundedPrice)
                .toList();

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
