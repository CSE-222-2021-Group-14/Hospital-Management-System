import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Patient extends AbstractPerson implements Comparable<Patient> {
    Stack<Appointment> appointments;
    Queue<Notification> notifications;
    ArrayList<Prescription> prescriptions;
    int age;
    boolean isEmergency;

    public Patient(String name, String surname, String ID, String phoneNum) {
        super(name, surname, ID, phoneNum);
        appointments = new Stack<>();
    }

    public void addAppointment(Appointment a) {

    }

    public void removeAppointment(Appointment a) {

    }

    public void viewAppointments() {

    }

    public void viewPrescriptions() {

    }

    @Override
    public int compareTo(Patient o) {
        return 0;
    }
}
