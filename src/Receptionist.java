import java.util.NoSuchElementException;
/** Class for The User which is Hospital Receptionist*/
public class Receptionist extends AbstractPerson implements Staff, Comparable<Receptionist> {

    // Constructor
    public Receptionist(String name, String surname, String ID, String password, String phoneNum) {
        super(name, surname, ID,password, phoneNum);
    }

    /** newPatientRegistration method adds new patient to system with given parameters.
     */
    public Patient newPatientRegistration(String name, String surname, String ID,String password, String phoneNum, HospitalManagementSystem system) {
        Patient newPatient = new Patient(name, surname, ID,password, phoneNum);
        system.patients.add(newPatient);
        return newPatient;
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

            PolyclinicAppointment polyclinicAppointment;
            VaccineAppointment vaccineAppointment;
            for(Appointment appointment : patient.getAppointments())
                if(!appointment.isConfirmed()){
                    // Appointments those need to be confirmed
                    if(appointment instanceof PolyclinicAppointment) {  // Polyclinic Appointments
                        polyclinicAppointment = (PolyclinicAppointment) appointment;
                        // Adding appointment to Doctor's appointment queue
                        confirmed=polyclinicAppointment.getDoctor().add(polyclinicAppointment);
                        polyclinicAppointment.setConfirmed(confirmed);
                    }
                    else if(appointment instanceof VaccineAppointment)   {  // Vaccine Appointments
                        vaccineAppointment = (VaccineAppointment) appointment;
                        // Adding appointment to Nurse's appointment queue
                        confirmed=vaccineAppointment.getNurse().add(vaccineAppointment);
                        vaccineAppointment.setConfirmed(confirmed);
                    }
                }
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
        return confirmed;
    }

    @Override
    public int compareTo(Receptionist o) {
        return this.getID().compareTo(o.getID());
    }
}