import java.util.NoSuchElementException;

public class Receptionist extends AbstractPerson implements Staff, Comparable<Receptionist> {
    public Receptionist(String name, String surname, String ID, String phoneNum) {
        super(name, surname, ID, phoneNum);
    }

    public Patient newPatientRegistration(String name, String surname, String ID, String phoneNum, HospitalManagementSystem system) {
        Patient newPatient = new Patient(name, surname, ID, phoneNum);
        system.patients.add(newPatient);
        return newPatient;
    }

    public boolean confirmAppointments(String ID, HospitalManagementSystem system) {
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