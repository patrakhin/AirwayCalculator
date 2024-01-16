package test_task.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PriceStatsCalculator {

    private static final Logger logger = LogManager.getLogger(PriceStatsCalculator.class);

    public static Map<String, String> calculatePriceStats(List<EntityAirTicket> tickets, String origin_name, String destination_name) {
        Map<String, String> result = new HashMap<>();

        List<Double> prices = new ArrayList<>();

        for (EntityAirTicket ticket : tickets) {
            if (ticket.getOrigin_name().equals(origin_name) && ticket.getDestination_name().equals(destination_name)) {
                prices.add((double) ticket.getPrice());
            }
        }

        if (!prices.isEmpty()) {
            double averagePrice = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            Collections.sort(prices);

            double median;
            if (prices.size() % 2 == 0) {
                median = (prices.get(prices.size() / 2 - 1) + prices.get(prices.size() / 2)) / 2;
            } else {
                median = prices.get(prices.size() / 2);
            }
            result.put("difference", String.valueOf(averagePrice - median));
            result.put("average", String.valueOf(averagePrice));
            result.put("median", String.valueOf(median));
        } else {
            logger.info("Нет данных о ценах для указанных маршрута и направления.");
        }
        return result;
    }
}
