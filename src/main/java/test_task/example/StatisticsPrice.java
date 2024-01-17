package test_task.example;

import java.util.List;
import java.util.Map;

public class StatisticsPrice {
    private StatisticsPrice() {
        throw new IllegalStateException("StatisticsPrice class");
    }

    public static Map<String, String> getStatisticPrice(List<EntityAirTicket> filteredTicket) {

        // Извлекаем цены в список
        List<Double> prices = filteredTicket.stream()
                .map(EntityAirTicket::getPrice)
                .mapToDouble(Integer::doubleValue)
                .boxed()
                .toList();

        // Вычисляем общую сумму цен
        double total = prices.stream().mapToDouble(Double::doubleValue).sum();

        // Вычисляем среднюю цену
        double average = total / prices.size();

        // Вычисляем медиану
        double median;
        int size = prices.size();
        List<Double> sortedPrices = prices.stream().sorted().toList();
        if (size % 2 == 0) {
            median = (sortedPrices.get(size / 2 - 1) + sortedPrices.get(size / 2)) / 2.0;
        } else {
            median = sortedPrices.get(size / 2);
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
