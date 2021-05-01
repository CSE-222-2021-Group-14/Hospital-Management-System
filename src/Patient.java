import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends Person implements PatientInterface, Comparable<Patient> {
    ArrayList<Appointment> appointments;
    int age;
    boolean isEmergency;

    public Patient(String name, String surname, String ID, String phoneNum) {
        super(name, surname, ID, phoneNum);
        appointments = new ArrayList<>();
    }

    @Override
    public void addAppointment() {

    }

    @Override
    public void removeAppointment() {

    }

    @Override
    public void viewAppointments() {

    }

    @Override
    public void viewPrescriptions() {

    }

    @Override
    public int compareTo(Patient o) {
        return 0;
    }
}
