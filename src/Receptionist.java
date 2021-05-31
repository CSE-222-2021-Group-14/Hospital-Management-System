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
        boolean confirmed = false;
        try{
            patient = system.findPatient(ID);
            PolyclinicAppointment polyclinicAppointment;
            VaccineAppointment vaccineAppointment;
            for(Appointment appointment : patient.getAppointments())
                if(!appointment.isConfirmed()){
                    if(appointment instanceof PolyclinicAppointment) {
                        polyclinicAppointment = (PolyclinicAppointment) appointment;
                        if (polyclinicAppointment.getDoctor().getDepartment().equals(polyclinicAppointment.getDepartment())) {
                            polyclinicAppointment.setConfirmed(true);
                            polyclinicAppointment.getDoctor().add(polyclinicAppointment);
                            confirmed=true;
                        }
                    }
                    else if(appointment instanceof VaccineAppointment)   {
                        vaccineAppointment = (VaccineAppointment) appointment;
                        vaccineAppointment.setConfirmed(true);
                        vaccineAppointment.getNurse().add(vaccineAppointment);
                        confirmed=true;
                    }
                }
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
        if(confirmed)
            return true;
        return false;
    }

    @Override
    public int compareTo(Receptionist o) {
        return this.getID().compareTo(o.getID());
    }
}