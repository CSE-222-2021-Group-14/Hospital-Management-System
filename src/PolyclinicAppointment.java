import java.time.LocalDateTime;

/** Class for Polyclinic Appointments */
public class PolyclinicAppointment extends Appointment{
    // Data Fields
    /** The Doctor with whom this appointment was made. */
    private final Doctor doctor;
    /** The Department of Polyclinic. */
    private final Department department;

    // Constructor
    public PolyclinicAppointment(Patient patient, Doctor doctor, LocalDateTime time,Department department) {
        super(patient, time);
        this.doctor = doctor;
        this.department = department;
    }

    // Getters and Setters
    public Doctor getDoctor() {
        return doctor;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Doctor: ").append(doctor.getName()).append(" ").append(doctor.getSurname()).append("\nDepartment: ");
        String d = department.toString().toLowerCase();
        d = d.substring(0, 1).toUpperCase() + d.substring(1);
        s.append(d).append("\n").append(super.toString());
        return s.toString();
    }

    @Override
    public int compareTo(Appointment o) {
        return this.getTime().compareTo(o.getTime());
    }
}
