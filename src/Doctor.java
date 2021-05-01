import java.util.Queue;

public class Doctor extends Person implements DoctorInterface, Staff, Comparable<Doctor>{
    Queue<Appointment> appointments;//priority olucak
    Department department;

    public Doctor(String name, String surname, String ID, String phoneNum, Department department) {
        super(name, surname, ID, phoneNum);
        this.department = department;
    }

    @Override
    public PatientInterface callPatient() {
        return null;
    }

    @Override
    public void viewAppointments() {
        for(Appointment i : appointments){
            System.out.println(i);
        }
    }

    @Override
    public void viewPatientMedicalInfo(PatientInterface patient) {

    }

    @Override
    public void viewPatientPrevAppointments(PatientInterface patient) {

    }

    @Override
    public void setPatientStatus(PatientInterface patient) {

    }

    @Override
    public void viewInpatients() {

    }

    @Override
    public void clearSchedule() {

    }

    @Override
    public int compareTo(Doctor o) {
        return 0;
    }
}
