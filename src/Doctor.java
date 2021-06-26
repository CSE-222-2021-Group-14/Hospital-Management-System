import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Class tht represents a doctor.
 */
public class Doctor extends AbstractPerson implements Staff, Comparable<Doctor>{
    private final LinkedList<PolyclinicAppointment> appointments;
    private final Department department;
    private int patientCount = 0;

    /**
     * Constructs a doctor with given stats.
     * @param name name
     * @param surname surname
     * @param ID ID
     * @param phoneNum phone number
     * @param password password
     * @param department department
     */
    public Doctor(String name, String surname, String ID, String phoneNum, String password, Department department) {
        super(name, surname, ID, phoneNum, password);
        this.department = department;
        appointments = new LinkedList<>();
        LocalDateTime date = LocalDate.now().atTime(9,0);

        for(int i = 0; i < 5; i++){
            if(date.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                date = date.plusDays(2);
            }
            else if(date.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                date = date.plusDays(1);
            }

            for(int j = 0; j  < 14; j++){
                appointments.add(new PolyclinicAppointment(null, this, date, this.department));
                date = date.plusMinutes(30);
                if(date.getHour()*60 + date.getMinute() == 720){
                    date = date.plusMinutes(60);
                }
            }

            date = date.plusDays(1).toLocalDate().atTime(9, 0);
        }
    }

    /**
     * Resets patientCount to 0.
     */
    protected void reconfigurePatientQueue(){
        patientCount = 0;
    }

    /**
     * Returns the next appointment of today appointments.
     * @return the next appointment of today appointments
     */
    public PolyclinicAppointment callNextAppointment() {
        if(patientCount == 14) throw new NoSuchElementException();
        return appointments.get(patientCount++);
    }

    /**
     * Views doctor's today appointments.
     */
    public void viewAppointments() {
        Iterator<PolyclinicAppointment> iterator = appointments.iterator();
        for(int i = 0; i < 14; i++){
            PolyclinicAppointment appointment = iterator.next();
            if(appointment.getStatus().equals(StatusType.TAKEN)){
                System.out.println(appointment + "\n");
            }
        }
    }

    /**
     * Views the medical info of given patient.
     * @param patient patient
     */
    public void viewPatientMedicalInfo(Patient patient) {
        System.out.println("Medical Information\nSurname: " + patient.getSurname() + "\nName: " + patient.getName() +
                "\nBlood Type: " + patient.getBloodType() + "\nKnown Diseases:");
        for(String disease: patient.getDisease()){
            System.out.println("-" + disease);
        }
        System.out.println("Previous Prescriptions:");
        for(Prescription prescription : patient.getPrescriptions()){
            System.out.println("-" + prescription);
        }
    }

    /**
     * Views the previous appointments of the given patient.
     * @param patient patient
     */
    public void viewPatientPrevAppointments(Patient patient) {
        Stack<Appointment> prevAppointments = new Stack<>();
        patient.getAppointments().forEach(prevAppointments::push);
        while (!prevAppointments.isEmpty()){
            if(prevAppointments.peek().getStatus().equals(StatusType.FINISHED)){
                System.out.println(prevAppointments.pop());
            }
            else prevAppointments.pop();
        }
    }

    /**
     * Views the inpatients.
     * @param system HospitalManagementSystem
     */
    public void viewInpatients(HospitalManagementSystem system) {
        for(Bed bed : system.getDorm()){
            if(bed.getBedStatus().equals(BedStatus.OCCUPIED)){
                System.out.println(bed.getPatient());
            }
        }
    }

    /**
     * Cancels all the appointments today.
     */
    public void clearSchedule() {
        Iterator<PolyclinicAppointment> iterator = appointments.iterator();
        for (int i = 0; i < 14; i++){
            iterator.next().setStatus(StatusType.CANCELLED);
        }
    }

    protected boolean add(PolyclinicAppointment newAppointment){
        return appointments.add(newAppointment);
    }

    public int compareTo(Doctor o) {
        return ID.compareTo(o.ID);
    }

    public String toString() {
        return "Doctor\n" + super.toString() + "\nDepartment: " + department + "\n";
    }

    public LinkedList<PolyclinicAppointment> getAppointments() {
        return appointments;
    }

    public Department getDepartment() {
        return department;
    }
}
