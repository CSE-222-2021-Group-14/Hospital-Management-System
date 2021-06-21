import java.util.NoSuchElementException;
import java.util.Scanner;

/** Class for The User which is Hospital Receptionist*/
public class Receptionist extends AbstractPerson implements Staff, Comparable<Receptionist> {

    // Constructor
    public Receptionist(String name, String surname, String ID, String password, String phoneNum) {
        super(name, surname, ID,password, phoneNum);
    }

    /** newPatientRegistration method adds new patient to system with given parameters.
     * @param name Patient name
     * @param surname Patient surname
     * @param ID Patient ID
     * @param password Patient system password
     * @param phoneNum Patient phone number
     * @param system Hospital Management System
     * @return true if patient registration is successful,
     *          otherwise false.
     */
    public boolean newPatientRegistration(String name, String surname, String ID,String password, String phoneNum, HospitalManagementSystem system) {
        Patient newPatient = new Patient(name, surname, ID, phoneNum,password);
        system.getPatients().put(ID,newPatient);
        return (system.getPatients().get(ID) != null);
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
            for(Appointment appointment : patient.getAppointments())
                if(!appointment.isConfirmed()){
                    // Appointments those need to be confirmed
                    appointment.setConfirmed(true);
                    confirmed = true;
                }
        }
        catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
        return confirmed;
    }

    /** Receptionist menu method shows menu to receptionist to access receptionist's jobs.
     * @param system Hospital Management System
     */
    public void menu(HospitalManagementSystem system) {
        Scanner scanner = new Scanner(System.in);
        int scannerChoice;
        // Loops until receptionist selects return main menu
        do {
            scannerChoice = 0;
            System.out.println("Welcome to Receptionist Menu");
            System.out.println("[1] New Patient Registration");
            System.out.println("[2] New Appointment");
            System.out.println("[3] Cancel Appointment");
            System.out.println("[4] Confirm an Appointment");
            System.out.println("[5] Return Main Menu");
            while (scannerChoice<1 || scannerChoice >5) {
                System.out.println("Select an option:");
                if (scanner.hasNextInt())
                    scannerChoice = scanner.nextInt();
                if (scannerChoice<1 || scannerChoice >5)
                    System.out.println("Invalid Choice");
            }
            switch (scannerChoice) {
                case 1 -> {    // New Patient Registration
                    // Patient Information
                    String patientName = null, patientSurname = null, patientID = null,
                            patientPassword = null, patientPhoneNum = null;
                    while (patientName == null) {
                        System.out.println("Patient name:");
                        if (scanner.hasNext())
                            patientName = scanner.next();
                    }
                    while (patientSurname == null) {
                        System.out.println("Patient surname:");
                        if (scanner.hasNext())
                            patientSurname = scanner.next();
                    }
                    while (patientID == null) {
                        System.out.println("Patient ID:");
                        if (scanner.hasNext())
                            patientID = scanner.next();
                    }
                    while (patientPassword == null) {
                        System.out.println("Patient Password:");
                        if (scanner.hasNext())
                            patientPassword = scanner.next();
                    }
                    while (patientPhoneNum == null) {
                        System.out.println("Patient Phone Number:");
                        if (scanner.hasNext())
                            patientPhoneNum = scanner.next();
                    }
                    // Calls the method to create new patient registration.
                    if (newPatientRegistration(patientName,patientSurname,patientID,patientPassword,patientPhoneNum,system))
                        System.out.println("New Patient Registration is Successful.");
                    else
                        System.out.println("New Patient Registration is not Successful.");
                }
                case 2 -> { // New Appointment for Patient
                    String patientID = null;
                     do {
                        System.out.println("Patient ID:");
                        if (scanner.hasNext())
                            patientID = scanner.next();
                        if (system.getPatients().get(patientID) == null)
                            System.out.println("Invalid ID");
                    }while (patientID == null || system.getPatients().get(patientID) == null);
                    system.getPatients().get(patientID).addAppointmentMenu();
                    // Confirm appointment which just created.
                    confirmAppointments(patientID, system);
                }
                case 3 -> { // Cancel Appointment for Patient
                    String patientID = null;
                    do {
                        System.out.println("Patient ID:");
                        if (scanner.hasNext())
                            patientID = scanner.next();
                        if (system.getPatients().get(patientID) == null)
                            System.out.println("Invalid ID");
                    }while (patientID == null || system.getPatients().get(patientID) == null);
                    system.getPatients().get(patientID).removeAppointmentMenu();
                }
                case 4 -> { // Confirm Patient's Appointments
                    String patientID = null;
                    do {
                        System.out.println("Patient ID:");
                        if (scanner.hasNext())
                            patientID = scanner.next();
                    }while (patientID == null || system.getPatients().get(patientID) == null);
                    if (confirmAppointments(patientID, system))
                        System.out.println("Appointments are confirmed.");
                    else
                        System.out.println("Some appointments are not confirmed.");
                }
                case 5 -> {
                    System.out.println("Returning Main Menu...");
                }
            }
        }while (scannerChoice != 5);
    }

    @Override
    public int compareTo(Receptionist o) {
        return this.getID().compareTo(o.getID());
    }
}