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
        return "Nurse: " + nurse.getName() + " " + nurse.getSurname() + "\n" + super.toString();
    }

    @Override
    public int compareTo(Appointment o) {
        return this.getTime().compareTo(o.getTime());
    }
}
