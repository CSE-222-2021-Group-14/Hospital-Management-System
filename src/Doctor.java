import java.time.LocalDateTime;
import java.util.*;

public class Doctor extends AbstractPerson implements Staff, Comparable<Doctor>{
    PriorityQueue<Appointment> appointments;//priority olucak
    Queue<Notification> notifications;
    Department department;
    Patient currentPatient;

    public Doctor(String name, String surname, String ID, String phoneNum, Department department) {
        super(name, surname, ID, phoneNum);
        this.department = department;
        appointments = new PriorityQueue<>();
        notifications = new ArrayDeque<>();
    }


    public Patient callPatient() {
        currentPatient = appointments.poll().patient;
        return currentPatient;
    }

    public void viewAppointments() {
        Calendar calendar = Calendar.getInstance();
        LocalDateTime today = LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59);
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
        Stack<Appointment> prevAppointments = patient.appointments;
        while (!prevAppointments.isEmpty()){
            System.out.println(prevAppointments.pop());
        }
    }

    public void setPatientStatus(Patient patient, int mode) {
        patient.setStatus(mode == 1);
    }

    public void viewInpatients(HospitalManagementSystem system) {
        Set<Map.Entry<String, Patient>> patients = system.patients.entrySet();
        for(Map.Entry<String, Patient> patient : patients){
            if(patient.getValue().getStatus()){
                System.out.println(patient.getValue());
            }
        }
    }

    public void clearSchedule() {
        Calendar calendar = Calendar.getInstance();
        LocalDateTime today = LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59);
        appointments.removeIf(appointment -> appointment.time.compareTo(today) < 0);
    }

    @Override
    public int compareTo(Doctor o) {
        return 0;
    }
}
