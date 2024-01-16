package test_task.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AirTicket {
    private static final Logger logger = LogManager.getLogger(AirTicket.class);

    private final String origin;
    private final String origin_name;
    private final String destination;
    private final String destination_name;

    @JsonFormat(pattern = "dd.MM.yy")
    private String departure_date;

    @JsonFormat(pattern = "HH:mm")
    private String departure_time;

    @JsonFormat(pattern = "dd.MM.yy")
    private String arrival_date;

    @JsonFormat(pattern = "HH:mm")
    private String arrival_time;

    private String carrier;
    private int stops;
    private int price;

    @JsonCreator
    public AirTicket(
            @JsonProperty("origin") String origin,
            @JsonProperty("origin_name") String origin_name,
            @JsonProperty("destination") String destination,
            @JsonProperty("destination_name") String destination_name,
            @JsonProperty("departure_date") String departure_date,
            @JsonProperty("departure_time") String departure_time,
            @JsonProperty("arrival_date") String arrival_date,
            @JsonProperty("arrival_time") String arrival_time,
            @JsonProperty("carrier") String carrier,
            @JsonProperty("stops") int stops,
            @JsonProperty("price") int price) {
        this.origin = origin;
        this.origin_name = origin_name;
        this.destination = destination;
        this.destination_name = destination_name;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    // Геттеры для всех полей
    public String getOrigin() {
        return origin;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public int getPrice() {
        return price;
    }

    // Метод для расчета времени полета в часах
/*    public double calculateFlightTime() {
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
    }*/
}

