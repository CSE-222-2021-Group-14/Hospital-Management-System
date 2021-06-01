import java.util.PriorityQueue;
import java.util.Queue;

/** Class for The User which is Hospital Nurses*/
public class Nurse extends AbstractPerson implements Staff, Comparable<Nurse> {

    // Data Fields
    /** Patients those need take caring.*/
    private  Queue<Patient> requests;

    /** Vaccine Appointments */
    private  PriorityQueue<Appointment> appointments;

    // Constructor
    public Nurse(String name, String surname, String ID, String password, String phoneNum) {
        super(name, surname, ID,phoneNum, password);
        appointments = new PriorityQueue<Appointment>();
    }

    /** Vaccinates head of queue and removes him/her from queue. */
    public void vaccinate(){
        VaccineAppointment appointment = (VaccineAppointment) appointments.poll();
    }

    /** Take cares head of queue and removes him/her from queue. */
    public void  takeCare(){
        Patient patient = requests.poll();
    }

    /** add method.
        @param appointment The appointment being inserted.
        @return true if appointment inserting in queue is successful,
                otherwise false.
     */
    public boolean add(Appointment appointment){
        return (appointments.add(appointment));
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", requests=" + requests +
                ", appointments=" + appointments +
                '}';
    }

    @Override
    public int compareTo(Nurse o) {
        return getID().compareTo(o.getID());
    }
}
