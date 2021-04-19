import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    Patient patient;
    Doctor doctor;
    Department department;
    LocalDateTime time;
    String status;

    /*@Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
        s.append(patient.)
    }*/
}
