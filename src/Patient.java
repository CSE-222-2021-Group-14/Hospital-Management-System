import java.util.ArrayList;
import java.util.Stack;

public class Patient extends AbstractPerson implements Comparable<Patient> {
    private Stack<Appointment> appointments;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<String> disease;
    
    private int age;
    private String bloodType;
    
    
    protected int getAge() {
        return age;
    }

    protected String getBloodType() {
        return bloodType;
    }

    protected ArrayList<String> getDisease() {
        return disease;
    }

    protected Stack<Appointment> getAppointments(){
        return appointments;
    }

    protected ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }

    public Patient(String name, String surname, String ID, String phoneNum, String password){
        super(name, surname, ID, phoneNum, password);
        appointments = new Stack<>();
        prescriptions = new ArrayList<>();
        disease = new ArrayList<>();
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
        if (!appointments.isEmpty())
        appointments.forEach(System.out::println);
        else
            System.out.print("Currently you haven't got any appointment\n");
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
