import java.util.NoSuchElementException;

/** Class for The User which is Hospital Receptionist*/
public class Receptionist extends AbstractPerson implements Staff, Comparable<Receptionist> {

    // Constructor
    public Receptionist(String name, String surname, String ID, String password, String phoneNum) {
        super(name, surname, ID,password, phoneNum);
    }

    /** newPatientRegistration method adds new patient to system with given parameters.
     * @param name Patient name
     * @param surname Patient surname
     * @param ID Patient ID
     * @param password Patient system password
     * @param phoneNum Patient phone number
     * @param system Hospital Management System
     * @return true if patient registration is successful,
     *          otherwise false.
     */
    public boolean newPatientRegistration(String name, String surname, String ID,String phoneNum, String password, HospitalManagementSystem system) {
        Patient newPatient = new Patient(name, surname, ID, phoneNum,password);
        if (system.getPatients().get(ID) != null)
            return false;
        system.getPatients().put(ID,newPatient);
        return (system.getPatients().get(ID) != null);
    }

    /** confirmAppointments method.
        pre: The patient to add her/his appointments to her/his Doctor's or Nurse's appointment queue.
        @param ID The ID of patient whose appointments being confirmed.
        @param system Hospital Management System
        @return true if The Patient's appointments are confirmed,
                otherwise false.
     */
    public boolean confirmAppointments(String ID, HospitalManagementSystem system) {
        Patient patient;
        boolean confirmed = false;
        try{
            // Finds patient with ID from system
            patient = system.findPatient(ID);
            for(Appointment appointment : patient.getAppointments())
                if(!appointment.isConfirmed()){
                    // Appointments those need to be confirmed
                    appointment.setConfirmed(true);
                    confirmed = true;
                }
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
        return confirmed;
    }




    @Override
    public int compareTo(Receptionist o) {
        if(this.name.compareTo(o.getName()) == 0){
            if(this.surname.compareTo(o.getSurname()) == 0){
                return this.ID.compareTo(o.getID());
            }
            else return this.surname.compareTo(o.getSurname());
        }
        return this.name.compareTo(o.getName());
    }
}