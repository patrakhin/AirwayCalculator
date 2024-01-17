package test_task.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProcessor {

    public void writingData(List<EntityAirTicket> tickets) throws CustomDataProcessingException {
        try {
            List<EntityAirTicket> filteredTicket = TicketFilter.getFilterTickets(tickets);
            List<DTO> transformedTicket = TransformCarrier.getTransformTickets(filteredTicket);
            List<DTO> sortedCarrier = SortedCarrier.getSortedCarrier(transformedTicket);
            Map<String, String> statisticPrice = StatisticsPrice.getStatisticPrice(filteredTicket);
            Writer.writeStatisticsToExcel(statisticPrice, sortedCarrier);
        } catch (IOException e) {
            // Обработка исключения в случае проблем с вводом-выводом
            throw new CustomDataProcessingException("Ошибка при обработке данных из json", e);
        }
    }

}
