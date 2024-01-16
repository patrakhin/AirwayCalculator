package test_task.example;

import java.util.List;

public class TicketFilter {
    private TicketFilter(){
        throw new IllegalStateException("TicketFilter class");
    }
    public static List<EntityAirTicket> getFilterTickets(List<EntityAirTicket> tickets) {
        return tickets.stream()
                .filter(ticket -> "Владивосток".equals(ticket.getOrigin_name()) &&
                        "Тель-Авив".equals(ticket.getDestination_name()))
                .toList();
    }
}

