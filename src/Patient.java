import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Patient extends AbstractPerson implements Comparable<Patient> {
    private Stack<Appointment> appointments;
    private ArrayList<Prescription> prescriptions;

    private int age;
    private String bloodType;
    private ArrayList<String> disease;

    public int getAge() {
        return age;
    }

    public String getBloodType() {
        return bloodType;
    }

    protected ArrayList<String> getDisease() {
        return disease;
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
        appointments.forEach(System.out::println);
        //bossa bos diye print et
    }

    public void viewPrescriptions() {
        prescriptions.forEach(System.out::println);
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
