import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Abstract Class for Hospital Appointments */
public abstract class Appointment implements Serializable , Comparable<Appointment>{
    // Data Fields

    /** Patient who has taken this appointment.*/
    protected Patient patient;
    /** Time of Appointment */
    protected final LocalDateTime time;
    /** Flag to indicate that the Appointment is confirmed by Receptionist or not. */
    protected boolean confirmed;
    /**Flag to store the current status of this appointment*/
    protected StatusType status;

    public Appointment(Patient patient, LocalDateTime time) {
        this.patient = patient;
        this.time = time;
        confirmed = false;
        status = StatusType.EMPTY;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public StatusType getStatus() {
        return status;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
        s.append("Time: ").append(dtf.format(time)).append("\nPatient: ");
        if (patient != null)
            s.append(patient.getName()).append(" ")
                    .append(patient.getSurname()).append("\n");
        else
            s.append("Empty");
        /*s.append("Patient: ").append(patient.getName()).append(" ").append(patient.getSurname()).append("\n");
        s.append("Time: ").append(dtf.format(time)).append("\n").append("Status: ");
        String statusLower = status.toString().toLowerCase();
        statusLower = statusLower.substring(0, 1).toUpperCase() + statusLower.substring(1);
        s.append(statusLower).append("\nConfirmed: ").append(confirmed).append("\n");*/
        return s.toString();
    }
}
