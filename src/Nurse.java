import java.util.Queue;

public class Nurse extends AbstractPerson implements Staff {

    static Queue<Patient> requests;

    static Queue<Appointment> appointments; //Priority Queue olacak

    public Nurse(String name, String surname, String ID, String phoneNum) {
        super(name, surname, ID, phoneNum);
    }

    public void vaccinate(){} //Siradaki asi olacak kisiyi appointments dan poplar.

    public void  takeCare(){} // Requestden pop yapar.


}
