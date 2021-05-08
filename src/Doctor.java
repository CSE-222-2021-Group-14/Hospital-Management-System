import java.util.Queue;

public class Doctor extends AbstractPerson implements Staff, Comparable<Doctor>{
    Queue<Appointment> appointments;//priority olucak
    Queue<Notification> notifications;
    Department department;

    public Doctor(String name, String surname, String ID, String phoneNum, Department department) {
        super(name, surname, ID, phoneNum);
        this.department = department;
    }


    public Patient callPatient() {
        return null;
    }

    public void viewAppointments() {
        for(Appointment i : appointments){
            System.out.println(i);
        }
    }

    public void viewPatientMedicalInfo(Patient patient) {

    }

    public void viewPatientPrevAppointments(Patient patient) {

    }

    public void setPatientStatus(Patient patient) {

    }

    public void viewInpatients() {

    }

    public void clearSchedule() {

    }

    @Override
    public int compareTo(Doctor o) {
        return 0;
    }
}
