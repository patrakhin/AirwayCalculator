package test_task.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortedCarrier {
    public static List<DTO> getSortedCarrier (List<DTO> getTransformTicket) {
        return getTransformTicket.stream()
                .collect(Collectors.groupingBy(DTO::getCarrier))
                .values().stream()
                .map(group -> group.stream()
                        .min(Comparator.comparing(DTO::getFlightTime))
                        .orElseThrow())
                .sorted(Comparator.comparing(DTO::getFlightTime))
                .collect(Collectors.toList());
    }
}
