import java.time.LocalDateTime;

/** Class for Polyclinic Appointments */
public class PolyclinicAppointment extends Appointment{
    // Data Fields
    /** The Doctor with whom this appointment was made. */
    private Doctor doctor;
    /** The Department of Polyclinic. */
    private Department department;
    /** Status of Appointment */
    private StatusType status; //if true than appointment must be finished.

    // Constructor
    public PolyclinicAppointment(Patient patient, Doctor doctor, LocalDateTime time,Department department) {
        setPatient(patient);
        this.doctor = doctor;
        setTime(time);
        setConfirmed(false);
        this.department = department;
        status = StatusType.EMPTY;
    }

    // Getters and Setters
    public Doctor getDoctor() {
        return doctor;
    }

    public Department getDepartment() {
        return department;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PolyclinicAppointment{" +
                "patient=" + getPatient() +
                ", time=" + getTime() +
                ", confirmed=" + isConfirmed() +
                ", doctor=" + doctor +
                ", department=" + department +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int compareTo(Appointment o) {
        return this.getTime().compareTo(o.getTime());
    }
}

enum StatusType{
    EMPTY, TAKEN, CANCELLED, FINISHED;
}