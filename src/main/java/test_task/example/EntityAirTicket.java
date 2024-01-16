package test_task.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityAirTicket {

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
    public EntityAirTicket(
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
}

