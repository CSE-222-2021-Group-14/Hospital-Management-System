import java.time.LocalDateTime;

/** Class for Vaccine Appointments */

public class VaccineAppointment extends Appointment{
    // Data Fields
    /** The Nurse with whom this appointment was made. */
    private Nurse nurse;

    // Constructor
    public VaccineAppointment(Patient patient,Nurse nurse,LocalDateTime time) {
        setPatient(patient);
        this.nurse = nurse;
        setTime(time);
        setConfirmed(false);
    }

    public Nurse getNurse() {
        return nurse;
    }


    @Override
    public String toString() {
        return "VaccineAppointment{" +
                "confirmed=" + isConfirmed() +
                ", nurse=" + nurse +
                '}';
    }

    @Override
    public int compareTo(Appointment o) {
        return this.getTime().compareTo(o.getTime());
    }
}