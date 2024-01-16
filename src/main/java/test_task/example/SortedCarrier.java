package test_task.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortedCarrier {
    private SortedCarrier() {
        throw new IllegalStateException("SortedCarrier class");
    }

    public static List<DTO> getSortedCarrier (List<DTO> getTransformTicket) {
        return getTransformTicket.stream()
                .collect(Collectors.groupingBy(DTO::carrier))
                .values().stream()
                .map(group -> group.stream()
                        .min(Comparator.comparing(DTO::flightTime))
                        .orElseThrow())
                .sorted(Comparator.comparing(DTO::flightTime))
                .toList();
    }
}
