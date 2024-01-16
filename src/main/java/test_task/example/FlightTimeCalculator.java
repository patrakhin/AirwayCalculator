package test_task.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightTimeCalculator {
    private static final Logger logger = LogManager.getLogger(FlightTimeCalculator.class);
    // Метод для расчета времени полета в часах
    public static double calculateFlightTime(EntityAirTicket entityAirTicket) {
        String departure_date = entityAirTicket.getDeparture_date();
        String departure_time = entityAirTicket.getDeparture_time();
        String arrival_date = entityAirTicket.getArrival_date();
        String arrival_time = entityAirTicket.getArrival_time();

        String datePattern = "dd.MM.yy";
        String timePattern = "HH:mm";

        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        SimpleDateFormat timeFormat = new SimpleDateFormat(timePattern);

        try {
            Date departureDate = dateFormat.parse(departure_date);
            Date departureTime = timeFormat.parse(departure_time);

            Date arrivalDate = dateFormat.parse(arrival_date);
            Date arrivalTime = timeFormat.parse(arrival_time);

            // Комбинирование даты и времени перед расчетом времени полета
            long departureDateTimeMillis = departureDate.getTime() + departureTime.getTime();
            long arrivalDateTimeMillis = arrivalDate.getTime() + arrivalTime.getTime();

            long timeDifference = arrivalDateTimeMillis - departureDateTimeMillis;
            return (double) timeDifference / (60 * 60 * 1000); // в часах
        } catch (ParseException e) {
            logger.error("Ошибка при расчете времени полета", e);
            return 0;
        }
    }
}
