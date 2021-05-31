import java.time.LocalDateTime;

public class PolyclinicAppointment extends Appointment{
    private Doctor doctor;
    private Department department;
    private String status;

    public PolyclinicAppointment(Patient patient, Doctor doctor, LocalDateTime time,Department department) {
        setPatient(patient);
        this.doctor = doctor;
        setTime(time);
        this.confirmed = false;
        this.department = department;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "PolyclinicAppointment{" +
                "patient=" + getPatient() +
                ", time=" + getTime() +
                ", confirmed=" + confirmed +
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
