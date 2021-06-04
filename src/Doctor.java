import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Doctor extends AbstractPerson implements Staff, Comparable<Doctor>{
    private PriorityQueue<Appointment> appointments;
    private Department department;

    public Doctor(String name, String surname, String ID, String phoneNum, String password, Department department) {
        super(name, surname, ID, phoneNum, password);
        this.department = department;
        appointments = new PriorityQueue<>();
    }


    public Patient callPatient() {
        LocalDateTime today = LocalDate.now().atTime(23, 59);
        if(!appointments.isEmpty() && appointments.peek().getTime().compareTo(today) < 0) {
            return appointments.poll().getPatient();
        }
        return null;
    }

    public void viewAppointments() {
        LocalDateTime today = LocalDate.now().atTime(23, 59);
        for(Appointment i : appointments){
            if(i.getTime().compareTo(today) < 0){
                System.out.println(i);
            }
        }
    }

    public void viewPatientMedicalInfo(Patient patient) {
        System.out.println("Surname: " + patient.surname);
        System.out.println("Name: " + patient.name);
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Known Diseases: ");
        for(String disease : patient.getDisease()){
            System.out.println(disease);
        }
    }

    public void viewPatientPrevAppointments(Patient patient) {
        Stack<Appointment> prevAppointments = new Stack<>();
        prevAppointments.addAll(patient.getAppointments());
        while (!prevAppointments.isEmpty()){
            System.out.println(prevAppointments.pop());
        }
    }

    /*public void setPatientStatus(Patient patient, int mode) {
        patient.setStatus(mode == 1);
    }*/

    public void viewInpatients(HospitalManagementSystem system) {
        for(Bed bed : system.getDorm()){
            if(bed.getBedStatus().equals(BedStatus.OCCUPIED)){
                System.out.println(bed.getPatient());
            }
        }
    }

    public void clearSchedule() {
        LocalDateTime today = LocalDate.now().atTime(23, 59);
        appointments.removeIf(appointment -> appointment.getTime().compareTo(today) < 0);
    }

    /*protected void syncSchedule(HospitalManagementSystem system){
        LocalDateTime now = LocalDateTime.now();
        if(now.getYear() < lastLoginDate.getYear() || now.getDayOfYear() < lastLoginDate.getDayOfYear()) {
            PriorityQueue<Appointment> appointments = new PriorityQueue<>(system.getAllAppointments());
            Appointment appointment;
            while (!appointments.isEmpty() && (appointment = appointments.poll()).doctor.compareTo(this) == 0 &&
                    appointment.time.getDayOfYear() == now.getDayOfYear() && !appointment.getStatus().equals("Completed")){
                appointments.add(appointment);
            }
        }
        else{
            throw new IllegalStateException();
        }
    }

    protected void setLastLoginDate(){
        lastLoginDate = LocalDateTime.now();
    }*/

    public int compareTo(Doctor o) {
        return ID.compareTo(o.ID);
    }


}
