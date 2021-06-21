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

    public void menu(){
        Patient current = null;
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int timeDifference = LocalDateTime.now().getDayOfYear() - appointments.getFirst().getTime().getDayOfYear();

        for(int j = 0; j < timeDifference; j++){
            for (int i = 0; i < 14; i++){
                appointments.remove();
            }
            LocalDateTime date = appointments.getLast().getTime();
            if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                date = date.plusDays(2);
            }
            date = date.plusDays(1).toLocalDate().atTime(9, 0);
            for (int i = 0; i < 14; i++){
                appointments.add(new PolyclinicAppointment(null, this, date, this.department));
                date = date.plusMinutes(30);
                if(date.getHour()*60 + date.getMinute() == 720){
                    date = date.plusMinutes(60);
                }
            }
        }

        while (choice != 7) {
            System.out.println("Welcome " + name + "\n1)Call the next patient\n2)View the medical information of the current patient\n" +
                    "3)View the previous appointments of the current patient\n4)View today's appointments\n" +
                    "5)View inpatients\n6)Clear today's schedule\n7)Exit");
            choice = getValidInput(scanner, 1, 7);
            switch (choice) {
                case 1 -> {
                    if(current != null) {
                        PolyclinicAppointment appointment = (PolyclinicAppointment) current.getAppointments().peek();
                        appointment.setStatus(StatusType.FINISHED);
                    }
                    current = callPatient();
                }
                case 2 -> {
                    if (current == null) {
                        System.out.println("You have to call a patient first.");
                    } else{
                        viewPatientMedicalInfo(current);
                    }
                }
                case 3 -> {
                    if (current == null) {
                        System.out.println("You have to call a patient first.");
                    } else{
                        viewPatientPrevAppointments(current);
                    }
                }
                case 4 -> viewAppointments();
                case 5 -> viewInpatients(new HospitalManagementSystem());// daha sonra this olucak
                case 6 -> clearSchedule();
                case 7 -> System.out.println("GOODBYE");
            }
        }
    }

    private int getValidInput(Scanner scanner, int bottomBound, int topBound){
        int input = -1;
        int check;
        do {
            check = 0;
            try {
                input = Integer.parseInt(scanner.nextLine());
                if(input < bottomBound || input > topBound){
                    System.out.println("Please enter an integer between " + bottomBound + " and " + topBound);
                }
            }
            catch (NumberFormatException e){
                System.out.println("Please enter an integer between " + bottomBound + " and " + topBound);
                check = 1;
            }
        } while (input < bottomBound || input > topBound || check == 1);
        return input;
    }
}
