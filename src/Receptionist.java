import java.util.NoSuchElementException;

public class Receptionist extends Person implements ReceptionistInterface, Comparable<Receptionist> {
    public Receptionist(String name, String surname, String ID, String phoneNum) {
        super(name, surname, ID, phoneNum);
    }

    @Override
    public Patient newPatientRegistration(String name, String surname, String ID, String phoneNum, Hospital_Management_System system) {
        Patient newPatient = new Patient(name, surname, ID, phoneNum);
        system.patients.add(newPatient);
        return newPatient;
    }

    @Override
    public boolean confirmAppointments(String ID, Hospital_Management_System system) {
        Patient patient;
        try{
            patient = system.findPatient(ID);
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
        return false;
    }

    @Override
    public int compareTo(Receptionist o) {
        return 0;
    }
}