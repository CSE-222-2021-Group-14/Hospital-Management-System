import java.time.LocalDateTime;

/** Class for Vaccine Appointments */

public class VaccineAppointment extends Appointment{
    // Data Fields
    /** The Nurse with whom this appointment was made. */
    private final Nurse nurse;

    // Constructor
    public VaccineAppointment(Patient patient,Nurse nurse,LocalDateTime time) {
        super(patient, time);
        this.nurse = nurse;
    }

    public Nurse getNurse() {
        return nurse;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Type: Vaccine Appointment\n").append(super.toString()).append("Nurse: ").append(nurse.getName()).
                append(" ").append(nurse.getSurname()).append("\n");
        return s.toString();
    }

    @Override
    public int compareTo(Appointment o) {
        return this.getTime().compareTo(o.getTime());
    }
}
