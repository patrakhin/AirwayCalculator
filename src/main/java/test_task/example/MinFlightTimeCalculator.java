package test_task.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinFlightTimeCalculator {

    public static Map<String, String> calculateMinFlightTime(List<EntityAirTicket> tickets, String origin_name, String destination_name) {
        Map<String, String> minFlightTimes = new HashMap<>();

        for (EntityAirTicket ticket : tickets) {
            if (ticket.getOrigin_name().equals(origin_name) && ticket.getDestination_name().equals(destination_name)) {
                String carrier = ticket.getCarrier();
                double flightTime = FlightTimeCalculator.calculateFlightTime(ticket);

                if (!minFlightTimes.containsKey(carrier) || flightTime < Double.parseDouble(minFlightTimes.get(carrier))) {
                    minFlightTimes.put(carrier, String.format("%.2f", flightTime));
                }
            }
        }
        return minFlightTimes;
    }
}

