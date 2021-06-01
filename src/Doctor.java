import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Doctor extends AbstractPerson implements Staff, Comparable<Doctor>{
    PriorityQueue<Appointment> appointments;//priority olucak
    Queue<Notification> notifications;
    Department department;
    LocalDateTime lastLoginDate;

    public Doctor(String name, String surname, String ID, String phoneNum, String password, Department department) {
        super(name, surname, ID, phoneNum, password);
        this.department = department;
        appointments = new PriorityQueue<>();
        notifications = new ArrayDeque<>();
    }

    public void viewNotifications(){
        try {
            while (!notifications.isEmpty()){
                System.out.println(notifications.remove());
            }
        } catch (NoSuchElementException e) {
            System.out.println("There is no notification to view");
        }
    }

    public Patient callPatient() {
        if(!appointments.isEmpty()) {
            return appointments.poll().patient;
        }
        return null;
    }

    public void viewAppointments() {
        //LocalDateTime ldt = LocalDateTime.now();
        //LocalDateTime today = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), 23, 59);
        LocalDateTime today = LocalDate.now().atTime(23, 59);
        for(Appointment i : appointments){
            if(i.time.compareTo(today) < 0){
                System.out.println(i);
            }
        }
    }

    public void viewPatientMedicalInfo(Patient patient) {
        System.out.println("Surname: " + patient.surname);
        System.out.println("Name: " + patient.name);
        System.out.println("Blood Type: " + patient.bloodType);
        System.out.println("Known Diseases: ");
        for(String disease : patient.getDiseases()){
            System.out.println(disease);
        }
    }

    public void viewPatientPrevAppointments(Patient patient) {
        Stack<Appointment> prevAppointments = new Stack<>();
        prevAppointments.addAll(patient.appointments);
        while (!prevAppointments.isEmpty()){
            System.out.println(prevAppointments.pop());
        }
    }

    public void setPatientStatus(Patient patient, int mode) {
        patient.setStatus(mode == 1);
    }

    public void viewInpatients(HospitalManagementSystem system) {
        Set<Map.Entry<String, Patient>> patients = system.getPatients().entrySet();
        for(Map.Entry<String, Patient> patient : patients){
            if(patient.getValue().getStatus()){
                System.out.println(patient.getValue());
            }
        }
    }

    public void clearSchedule() {
        //LocalDateTime ldt = LocalDateTime.now();
        //LocalDateTime today = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), 23, 59);
        LocalDateTime today = LocalDate.now().atTime(23, 59);
        appointments.removeIf(appointment -> appointment.time.compareTo(today) < 0);
    }

    protected void syncSchedule(HospitalManagementSystem system){
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
    }

    public int compareTo(Doctor o) {
        return ID.compareTo(o.ID);
    }


}
