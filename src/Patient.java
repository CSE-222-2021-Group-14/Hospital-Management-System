import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Patient extends AbstractPerson implements Comparable<Patient> {
    Stack<Appointment> appointments;
    Queue<Notification> notifications;
    ArrayList<Prescription> prescriptions;

    private int age;
    private String bloodType;
    private String sickness;

    public int getAge() {
        return age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getSickness() {
        return sickness;
    }

    public Patient(String name, String surname, String ID, String phoneNum, String password){
        super(name, surname, ID, phoneNum, password);
        appointments = new Stack<>();
    }

    public void addAppointment(Appointment a){
        appointments.push(a);
    }

    public void removeAppointment(Appointment a) {
        if (appointments.search(a) == 1) {
            appointments.remove(a);
        }
        else
            throw new UnsupportedOperationException("Cannot remove appointment\n");
    }

    public void viewAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void viewPrescriptions() throws InvalidPrescriptionException {
        for (Prescription prescription : prescriptions) {
            System.out.println(prescription);
        }
    }

    @Override
    public int compareTo(Patient o) {
        return 0;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Patient: Name = ").append(name).append(",Surname = ").append(surname).append(",ID = ").append(ID).append(",Phone Number = ").append(phoneNum);
        return s.toString();
    }
}
