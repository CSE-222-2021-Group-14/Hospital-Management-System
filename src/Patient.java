import java.time.format.DateTimeFormatter;
import java.util.*;

public class Patient extends AbstractPerson implements Comparable<Patient> {
    private SkipList<Appointment> appointments;
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

    protected SkipList<Appointment> getAppointments(){
        return appointments;
    }

    protected ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }

    public Patient(String name, String surname, String ID, String phoneNum, String password){
        super(name, surname, ID, phoneNum, password);
        appointments = new SkipList<>();
        prescriptions = new ArrayList<>();
        disease = new ArrayList<>();
    }

    public void addAppointment(Appointment a){
        appointments.add(a);
    }

    public void cancelAppointment(Appointment a) {
        if (appointments.find(a) != null) {
            appointments.remove(a);
        }
        else
            throw new NoSuchElementException("Cannot cancel appointment\n");
    }

    public void viewAppointments() {
        if (!appointments.isEmpty()){
            Stack<Appointment> temp = new Stack<>();
            appointments.forEach(temp::push);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
            while (!temp.isEmpty()){
                Appointment tmp = temp.pop();
                if(tmp instanceof PolyclinicAppointment){
                    PolyclinicAppointment pA = (PolyclinicAppointment) tmp;
                    System.out.println("Type: Polyclinic, Time: " + dtf.format(pA.getTime()) + ", Doctor: " + pA.getDoctor().getName() +
                            " " + pA.getDoctor().getSurname() + ", Department: " + pA.getDepartment() + ", Status: " +
                            pA.getStatus());
                }
                else{
                    System.out.println("Type: Vaccination, Time: " + dtf.format(tmp.getTime()) + ", Status: " + tmp.getStatus());
                }
            }
        }
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
