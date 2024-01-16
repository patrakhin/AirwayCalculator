package test_task.example;

import java.util.List;
import java.util.stream.Collectors;

public class TicketFilter {

    public static List<EntityAirTicket> getFilterTickets(List<EntityAirTicket> tickets) {
        return tickets.stream()
                .filter(ticket -> "Владивосток".equals(ticket.getOrigin_name()) &&
                        "Тель-Авив".equals(ticket.getDestination_name()))
                .collect(Collectors.toList());
    }
}

