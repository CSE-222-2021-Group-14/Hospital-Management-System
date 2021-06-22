import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Doctor extends AbstractPerson implements Staff, Comparable<Doctor>{
    private final LinkedList<PolyclinicAppointment> appointments;
    private final Department department;
    private ListIterator<PolyclinicAppointment> appointmentIterator;

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

        appointmentIterator = appointments.listIterator();
    }


    public Patient callPatient() {
        LocalDateTime today = LocalDate.now().atTime(23, 59);
        PolyclinicAppointment appointment = appointmentIterator.next();

        while (appointment.getTime().compareTo(today) < 0 && !appointment.isConfirmed() &&
                !appointment.getStatus().equals(StatusType.TAKEN)){
            appointment = appointmentIterator.next();
        }
        /*while ((appointment = appointments.get(patientCount)).getTime().compareTo(today) < 0
                && !appointment.isConfirmed() && !appointment.getStatus().equals(StatusType.TAKEN)){
            patientCount++;
        }
        return appointments.get(patientCount).getPatient();*/
        if(appointment.getTime().compareTo(today) > 0){
            appointmentIterator.previous();
            return null;
        }
        return appointment.getPatient();
    }

    public void viewAppointments() {
        Iterator<PolyclinicAppointment> iterator = appointments.iterator();
        for(int i = 0; i < 14; i++){
            System.out.println(iterator.next());
        }
    }

    public void viewPatientMedicalInfo(Patient patient) {
        System.out.println("Medical Information\nSurname: " + patient.getSurname() + "\nName: " + patient.getName() +
                "\nBlood Type: " + patient.getBloodType() + "\nKnown Diseases:");
        for(String disease: patient.getDisease()){
            System.out.println("-" + disease);
        }
        System.out.println("Previous prescriptions:");
        for(Prescription prescription : patient.getPrescriptions()){
            System.out.println("-" + prescription);
        }
    }

    public void viewPatientPrevAppointments(Patient patient) {
        Stack<Appointment> prevAppointments = new Stack<>();
        prevAppointments.addAll(patient.getAppointments());
        while (!prevAppointments.isEmpty()){
            System.out.println(prevAppointments.pop());
        }
    }

    public void viewInpatients(HospitalManagementSystem system) {
        for(Bed bed : system.getDorm()){
            if(bed.getBedStatus().equals(BedStatus.OCCUPIED)){
                System.out.println(bed.getPatient());
            }
        }
    }

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
