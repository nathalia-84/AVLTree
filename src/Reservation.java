import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    public int code;
    private String name;
    private String flight;
    private LocalDateTime departureTime;

    public Reservation(int code, String name, String flight, String departureTime) {
        this.code = code;
        this.name = name;
        this.flight = flight;
        this.departureTime = stringToDate(departureTime);
    }

    public LocalDateTime stringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    public String getFlight() {
        return flight;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    @Override
    public String toString() {
        return "ID = " + code + ", Passageiro = " + name + ", Voo = " + flight + ", Horário = " + departureTime;
    }
}
