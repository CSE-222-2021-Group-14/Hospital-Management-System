import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/** Class for The User which is Hospital Nurses*/
public class Nurse extends AbstractPerson implements Staff, Comparable<Nurse> {

    // Data Fields
    /** Patients those need take caring.*/
    private  Queue<Patient> requests;

    /** Vaccine Appointments */
    private  PriorityQueue<VaccineAppointment> appointments;

    // Constructor
    public Nurse(String name, String surname, String ID, String phoneNum, String password) {
        super(name, surname, ID,phoneNum, password);
        appointments = new PriorityQueue<>();
    }

    /** Vaccinates head of queue and removes him/her from queue. */
    public void vaccinate(){
        VaccineAppointment appointment = appointments.poll();
        appointment.setStatus(StatusType.FINISHED);
    }

    public int getAppointmentNumber() {
        return appointments.size();
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
    public boolean add(VaccineAppointment appointment){
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
