import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    Patient patient;
    LocalDateTime time;
    /*@Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
        s.append(patient.)
    }*/
}
