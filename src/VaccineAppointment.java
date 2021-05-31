import java.time.LocalDateTime;

public class VaccineAppointment extends Appointment{
    private Nurse nurse;

    public VaccineAppointment(Patient patient,Nurse nurse,LocalDateTime time) {
        setPatient(patient);
        this.nurse = nurse;
        setTime(time);
        this.confirmed = false;
    }

    public Nurse getNurse() {
        return nurse;
    }


    @Override
    public String toString() {
        return "VaccineAppointment{" +
                "confirmed=" + confirmed +
                ", nurse=" + nurse +
                '}';
    }

    @Override
    public int compareTo(Appointment o) {
        return this.getTime().compareTo(o.getTime());
    }
}