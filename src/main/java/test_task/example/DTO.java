package test_task.example;

public class DTO {
    private String flightTime;
    private String carrier;
    private double roundedPrice;

    public DTO(String flightTime, String carrier, double roundedPrice) {
        this.flightTime = flightTime;
        this.carrier = carrier;
        this.roundedPrice = roundedPrice;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public double getRoundedPrice() {
        return roundedPrice;
    }

    public void setRoundedPrice(double roundedPrice) {
        this.roundedPrice = roundedPrice;
    }
}
