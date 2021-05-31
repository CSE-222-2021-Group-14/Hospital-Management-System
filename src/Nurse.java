import java.util.PriorityQueue;
import java.util.Queue;

public class Nurse extends AbstractPerson implements Staff {

    static Queue<Patient> requests;

    static PriorityQueue<Appointment> appointments; //Priority Queue olacak

    public Nurse(String name, String surname, String ID, String phoneNum) {
        super(name, surname, ID, phoneNum);
        appointments = new PriorityQueue<Appointment>();
    }

    public void vaccinate(){
        VaccineAppointment appointment = (VaccineAppointment) appointments.poll();
    } //Siradaki asi olacak kisiyi appointments dan poplar.

    public void  takeCare(){
        Patient patient = requests.poll();
    } // Requestden pop yapar.

    public void add(Appointment appointment){
        appointments.add(appointment);
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
