import java.io.Serializable;
import java.time.LocalDateTime;

/** Abstract Class for Hospital Appointments */
public abstract class Appointment implements Serializable , Comparable<Appointment>{
    // Data Fields

    /** Patient who has taken this appointment.*/
    private Patient patient;
    /** Time of Appointment */
    private LocalDateTime time;
    /** Flag to indicate that the Appointment is confirmed by Receptionist or not. */
    private boolean confirmed;

    // Getters and Setters
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
