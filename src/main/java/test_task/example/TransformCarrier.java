package test_task.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransformCarrier {

    public static List<DTO> getTransformTickets(List<EntityAirTicket> filteredTickets) {
        return filteredTickets.stream()
                .map(ticket -> {
                    // Рассчитываем время полета
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
                    Date departureDateTime;
                    Date arrivalDateTime;
                    try {
                        departureDateTime = dateFormat.parse(ticket.getDeparture_date() + " " + ticket.getDeparture_time());
                        arrivalDateTime = dateFormat.parse(ticket.getArrival_date() + " " + ticket.getArrival_time());
                    } catch (ParseException e) {
                        throw new RuntimeException("Ошибка при разборе даты и времени", e);
                    }
                    long flightTimeMillis = arrivalDateTime.getTime() - departureDateTime.getTime();
                    long hours = flightTimeMillis / (60 * 60 * 1000);
                    long minutes = (flightTimeMillis % (60 * 60 * 1000)) / (60 * 1000);

                    // Форматируем время полета в HH:mm
                    String flightTime = String.format("%02d:%02d", hours, minutes);

                    // Округляем стоимость до двух знаков после запятой
                    double roundedPrice = Math.round(ticket.getPrice() * 100.0) / 100.0;

                    // Создаем объект DTO
                    return new DTO(flightTime, ticket.getCarrier(), roundedPrice);
                })
                .collect(Collectors.toList());
    }

/*    public static void main(String[] args) {
        // Ваш отфильтрованный список filteredTickets
        List<EntityAirTicket> filteredTickets = // ...

                // Преобразование в TransformCarrier
                List<TransformCarrier> transformedTickets = transformTickets(filteredTickets);

        // Вывод результатов
        for (TransformCarrier transformCarrier : transformedTickets) {
            System.out.println("AirTime: " + transformCarrier.getAirTime() +
                    ", carrier: " + transformCarrier.getCarrier() +
                    ", price: " + transformCarrier.getPrice());
        }
    }*/
}

