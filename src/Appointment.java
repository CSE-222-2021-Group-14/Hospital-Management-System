import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Appointment implements Serializable , Comparable<Appointment>{
    private Patient patient;
    private LocalDateTime time;
    private boolean confirmed;


    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /*@Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
        s.append(patient.)
    }*/
}
