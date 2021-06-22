import Graph.Edge;
import Graph.ListGraph;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

//LOGIN DE SET KULLANILACAK -BURAK

public class HospitalManagementSystem implements Serializable {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private TreeMap<String, Doctor> doctors;
    private TreeMap<String, Patient> patients;
    private TreeMap<String, Receptionist> receptionists;
    private Administrator administrator;
    private TreeMap<String, Nurse> nurses;
    private ArrayList<Bed> dorm;
    private TreeSet<String> IDs;
    private int vaccineAge;
    private int occupiedBedNum;
    private final ListGraph mapOfHospital;
    private final ArrayList<String> vertices;

    protected int getOccupiedBedNum() {
        return occupiedBedNum;
    }

    protected void setOccupiedBedNum(int occupiedBedNum) {
        this.occupiedBedNum = occupiedBedNum;
    }

    protected int getVaccineAge() {
        return vaccineAge;
    }

    protected ArrayList<Bed> getDorm() {
        return dorm;
    }

    protected void setVaccineAge(int vaccineAge) {
        this.vaccineAge = vaccineAge;
    }

    public HospitalManagementSystem(){
        int randomWeight;
        int randomDest;
        Random random = new Random();
        mapOfHospital = new ListGraph(11, false);
        vertices = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();

        vertices.add("DESK");
        for(Department d : Department.values()){
            vertices.add(d.toString());
        }
        vertices.add("VACCINATION");
        vertices.add("WC");
        for(int i = 0; i < vertices.size(); i++){
            set.add(i);
            for(int j = 0; j < 5; j++){
                do{
                    randomDest = random.nextInt(11);
                } while (!set.add(randomDest));
                randomWeight = random.nextInt(30);
                mapOfHospital.insert(new Edge(i, randomDest, randomWeight));
            }
        }
    }

    public Patient findPatient(String ID){
        return patients.get(ID);
    }

    public Doctor findDoctor(String ID){
        return doctors.get(ID);
    }

    protected void serialize() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("tmp/data.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    protected TreeMap<String, Doctor> getDoctors() {
        return doctors;
    }

    protected TreeMap<String, Patient> getPatients() {
        return patients;
    }

    protected TreeMap<String, Receptionist> getReceptionists() {
        return receptionists;
    }

    protected TreeMap<String, Nurse> getNurses() {
        return nurses;
    }

    public void runSystem(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 6){
            System.out.println("Please choose user type\n1)Patient\n2)Doctor\n3)Administrator\n4)Receptionist\n5)Nurse\n6)Exit");
            choice = getValidInput(scanner, 1, 6);

            switch (choice){
                case 1 -> {
                    Patient patient;
                    System.out.println("Please choose an entry type\n1)Login\n2)SignIn");
                    int entry = getValidInput(scanner, 1, 2);
                    if(entry == 1){
                        int counter = 0;
                        Person tmp;
                        do{
                            if(counter != 0) System.out.println(RED + "ID or password does not match" + RESET);
                            tmp = login(1);
                            counter++;
                        } while (tmp == null);
                        patient = (Patient) tmp;
                        System.out.println(GREEN + "Login successful" + RESET);
                    }
                    else patient = signIn();
                }
                case 2 -> {
                    int counter = 0;
                    Person tmp;
                    do{
                        if(counter != 0) System.out.println(RED + "ID or password does not match" + RESET);
                        tmp = login(2);
                        counter++;
                    } while (tmp == null);
                    Doctor doctor = (Doctor) tmp;
                    System.out.println(GREEN + "Login successful" + RESET);
                    Patient current = null;
                    int functionality = 0;
                    int timeDifference = LocalDateTime.now().getDayOfYear() - doctor.getAppointments().getFirst().getTime().getDayOfYear();

                    for(int j = 0; j < timeDifference; j++){
                        for (int i = 0; i < 14; i++){
                            doctor.getAppointments().remove();
                        }
                        LocalDateTime date = doctor.getAppointments().getLast().getTime();
                        if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                            date = date.plusDays(2);
                        }
                        date = date.plusDays(1).toLocalDate().atTime(9, 0);
                        for (int i = 0; i < 14; i++){
                            doctor.getAppointments().add(new PolyclinicAppointment(null, doctor, date, doctor.getDepartment()));
                            date = date.plusMinutes(30);
                            if(date.getHour()*60 + date.getMinute() == 720){
                                date = date.plusMinutes(60);
                            }
                        }
                    }

                    while (functionality != 7) {
                        System.out.println("Doctor Menu\nWelcome " + doctor.getName() + "\n1)Call the next patient\n" +
                                "2)View the medical information of the current patient\n" +
                                "3)View the previous appointments of the current patient\n4)View today's appointments\n" +
                                "5)View inpatients\n6)Clear today's schedule\n7)Exit");
                        functionality = getValidInput(scanner, 1, 7);
                        switch (functionality) {
                            case 1 -> {
                                if(current != null) {
                                    PolyclinicAppointment appointment = (PolyclinicAppointment) current.getAppointments().peek();
                                    appointment.setStatus(StatusType.FINISHED);
                                }
                                current = doctor.callPatient();
                            }
                            case 2 -> {
                                if (current == null) {
                                    System.out.println("You have to call a patient first.");
                                } else{
                                    doctor.viewPatientMedicalInfo(current);
                                }
                            }
                            case 3 -> {
                                if (current == null) {
                                    System.out.println("You have to call a patient first.");
                                } else{
                                    doctor.viewPatientPrevAppointments(current);
                                }
                            }
                            case 4 -> doctor.viewAppointments();
                            case 5 -> doctor.viewInpatients(this);// daha sonra this olucak
                            case 6 -> doctor.clearSchedule();
                            case 7 -> System.out.println("GOODBYE");
                        }
                    }
                }
                case 3 -> {
                    int counter = 0;
                    Person tmp;
                    do{
                        if(counter != 0) System.out.println(RED + "ID or password does not match" + RESET);
                        tmp = login(3);
                        counter++;
                    } while (tmp == null);
                    Administrator admin = (Administrator) tmp;
                    System.out.println(GREEN + "Login successful" + RESET);
                    int select = 0;
                    while (select != 5) {
                        System.out.println("Administrator Menu\nWelcome " + admin.getName() + "\n1)View Dorm Status\n" +
                                "2)Hire Staff\n3)Fire Staff\n4)View A Staff\n5)Exit");
                        select = getValidInput(scanner, 1, 5);
                        switch (select) {
                            case 1 -> {
                                int select2;
                                admin.viewDormStatus(this);
                                System.out.println("1)Add Bed To The Dorm\n2)Remove Bed From The Dorm\n3)Exit");
                                select2 = getValidInput(scanner, 1, 3);
                                int num;
                                switch (select2) {
                                    case 1 -> {
                                        System.out.println("Please enter the number of bed that will be added");
                                        num = getValidInput(scanner, 1, Integer.MAX_VALUE);
                                        admin.addBed(num, this);
                                    }
                                    case 2 -> {
                                        System.out.println("Please enter the number of bed that will be removed");
                                        num = getValidInput(scanner, 1, dorm.size());
                                        admin.removeBed(num, this);
                                    }
                                    default -> System.out.println("Returning To Administrator Menu");
                                }
                            }
                            case 2 -> {
                                int select3;
                                String name;
                                String surname;
                                String ID;
                                String phoneNum;
                                String password;
                                System.out.println("1)Hire A Doctor\n2)Hire A Nurse\n3)Hire A Receptionist\n4)Exit");
                                select3 = getValidInput(scanner, 1 , 4);
                                System.out.println("Please enter name of the staff");
                                name = scanner.nextLine();
                                System.out.println("Please enter surname of the staff");
                                surname = scanner.nextLine();
                                System.out.println("Please enter ID of the staff");
                                ID = getValidID(scanner);
                                System.out.println("Please enter phone number of the staff");
                                phoneNum = getValidPhoneNum(scanner);
                                System.out.println("Please enter password of the staff");
                                password = scanner.nextLine();
                                int dep;
                                Department d;
                                switch (select3) {
                                    case 1 -> {
                                        System.out.println("Please select department of the doctor");
                                        Department.printDepartments();
                                        dep = getValidInput(scanner, 1, 8);
                                        d = switch (dep) {
                                            case 1: yield Department.DERMATOLOGY;
                                            case 2: yield Department.INTERNAL_MEDICINE;
                                            case 3: yield Department.NEUROLOGY;
                                            case 4: yield Department.OPHTHALMOLOGY;
                                            case 5: yield Department.OTOLARYNGOLOGY;
                                            case 6: yield Department.PATHOLOGY;
                                            case 7: yield Department.PEDIATRICS;
                                            default: yield Department.SURGERY;
                                        };
                                        admin.hireStaff(new Doctor(name, surname, ID, phoneNum,password, d), this);
                                    }
                                    case 2 -> admin.hireStaff(new Nurse(name, surname, ID, password, phoneNum), this);
                                    case 3 -> admin.hireStaff(new Receptionist(name, surname, ID, password, phoneNum), this);
                                }
                            }
                            case 3 -> {
                                admin.viewAllStaff(this);
                                System.out.println("\nPlease enter the ID of the staff to fire");
                                String ID = getValidID(scanner);
                                Staff staffToFire = doctors.get(ID) == null ? (nurses.get(ID) == null ? receptionists.get(ID) : nurses.get(ID)) : doctors.get(ID);
                                admin.fireStaff(staffToFire, this);
                            }
                            case 4 -> {
                                int role;
                                String id;
                                System.out.println("Please Select The Role Of The Staff To Display\n" +
                                        "1)Doctor\n2)Nurse\n3)Receptionist");
                                role = getValidInput(scanner, 1, 3);
                                System.out.println("Please enter the ID of the staff");
                                id = getValidID(scanner);
                                admin.viewSpecificStaff(id, role, this);
                            }
                            default -> System.out.println("Returning To The Main Menu");
                        }
                    }
                }
                case 4 -> {
                    int counter = 0;
                    Person tmp;
                    do{
                        if(counter != 0) System.out.println(RED + "ID or password does not match" + RESET);
                        tmp = login(4);
                        counter++;
                    } while (tmp == null);
                    Receptionist receptionist = (Receptionist) tmp;
                    System.out.println(GREEN + "Login successful" + RESET);
                    int scannerChoice;
                    // Loops until receptionist selects return main menu
                    do {
                        System.out.println("Receptionist Menu\nWelcome " + receptionist.getName());
                        System.out.println("1)New Patient Registration\n2)New Appointment\n3)Cancel Appointment\n" +
                                "4)Confirm an Appointment\n5)Return Main Menu");
                        scannerChoice = getValidInput(scanner, 1, 5);
                        switch (scannerChoice) {
                            case 1 -> {    // New Patient Registration
                                // Patient Information
                                String patientName;
                                String patientSurname ;
                                String patientID;
                                String patientPassword;
                                String patientPhoneNum;
                                System.out.println("Patient name");
                                patientName = scanner.nextLine();
                                System.out.println("Patient surname");
                                patientSurname = scanner.nextLine();
                                System.out.println("Patient ID");
                                patientID = getValidID(scanner);
                                System.out.println("Patient Password");
                                patientPassword = scanner.nextLine();
                                patientPhoneNum = getValidPhoneNum(scanner);
                                // Calls the method to create new patient registration.
                                if (receptionist.newPatientRegistration(patientName,patientSurname,patientID,patientPassword,patientPhoneNum, this))
                                    System.out.println("New Patient Registration is Successful.");
                                else
                                    System.out.println("New Patient Registration is Failed.");
                            }
                            case 2 -> { // New Appointment for Patient
                                String patientID;
                                do{
                                    patientID = getValidID(scanner);
                                } while (patients.get(patientID) == null);
                                //patients.get(patientID).addAppointmentMenu();
                                // Confirm appointment which just created.
                                receptionist.confirmAppointments(patientID, this);
                            }
                            case 3 -> { // Cancel Appointment for Patient
                                String patientID;
                                do{
                                    patientID = getValidID(scanner);
                                } while (patients.get(patientID) == null);
                                //patients.get(patientID).removeAppointmentMenu();
                            }
                            case 4 -> { // Confirm Patient's Appointments
                                String patientID;
                                do{
                                    patientID = getValidID(scanner);
                                } while (patients.get(patientID) == null);
                                if (receptionist.confirmAppointments(patientID, this))
                                    System.out.println("Appointments are confirmed.");
                                else
                                    System.out.println("Some appointments are not confirmed.");
                            }
                            case 5 -> System.out.println("Returning To The Main Menu");
                        }
                    }while (scannerChoice != 5);
                }
                case 5 -> {
                    int counter = 0;
                    Person tmp;
                    do{
                        if(counter != 0) System.out.println(RED + "ID or password does not match" + RESET);
                        tmp = login(5);
                        counter++;
                    } while (tmp == null);
                    Nurse nurse = (Nurse) tmp;
                    System.out.println(GREEN + "Login successful" + RESET);
                    int nurseChoice = 0;
                    while (nurseChoice != 3) {
                        System.out.println("Nurse Menu\nWelcome " + nurse.getName());
                        System.out.println("1)Vaccinate Patient");
                        System.out.println("2)Take Care Patient");
                        System.out.println("3)Exit");
                        nurseChoice = getValidInput(scanner, 1, 3);
                        switch (nurseChoice) {
                            case 1 -> nurse.vaccinate();
                            case 2 -> nurse.takeCare();
                            case 3 -> System.out.println("Returning To The Main Menu");
                        }
                    }
                }
                default -> System.out.println("GOODBYE");
            }
        }
    }

    private Person login(int mode){
        Scanner scanner = new Scanner(System.in);
        String ID;
        String password;
        Person person = null;
        AbstractPerson tmp;

        while (person == null) {
            System.out.println("Please enter your ID");
            ID = getValidID(scanner);
            System.out.println("Please enter your password");
            password = scanner.nextLine();

            switch (mode){
                case 1 -> person = ((tmp = patients.get(ID)) == null || !tmp.password.equals(password)) ? null : tmp;
                case 2 -> person = ((tmp = doctors.get(ID)) == null || !tmp.password.equals(password)) ? null : tmp;
                case 3 -> person = (!administrator.ID.equals(ID) || !administrator.password.equals(password)) ? null : administrator;
                case 4 -> person = ((tmp = receptionists.get(ID)) == null || !tmp.password.equals(password)) ? null : tmp;
                case 5 -> person = ((tmp = nurses.get(ID)) == null || !tmp.password.equals(password)) ? null : tmp;
            }
        }

        return person;
    }

    private Patient signIn(){
        String ID;
        String name;
        String surname;
        String password;
        String phoneNum;
        Scanner scanner = new Scanner(System.in);
        int counter = 0;

        System.out.println("Please enter your ID");
        do{
            if(counter != 0){
                System.out.println(RED + "Your ID has to be unique" + RESET);
            }
            ID = getValidID(scanner);
            counter++;
        } while (!IDs.add(ID));

        System.out.println("Please enter your name");
        name = scanner.nextLine();
        System.out.println("Please enter your surname");
        surname = scanner.nextLine();
        System.out.println("Please enter your password");
        password = scanner.nextLine();
        System.out.println("Please enter your phone number");
        phoneNum = getValidPhoneNum(scanner);
        patients.put(ID, new Patient(name, surname, ID, password, phoneNum));
        return patients.get(ID);
    }

    private String getValidID(Scanner scanner){
        String ID;
        int counter = 0;

        do{
            if(counter != 0){
                System.out.println(RED + "Your ID must be eleven-digit long" + RESET);
            }
            ID = scanner.nextLine();
            counter++;
        } while (ID.length() != 11 || !ID.matches("\\d+"));

        return ID;
    }

    private String getValidPhoneNum(Scanner scanner){
        String phoneNum;
        String replaced;
        boolean isValid;
        int count;
        int iteration = 0;

        do{
            if(iteration != 0) {
                System.out.println(RED + "Your phone number must only include digits and be eleven-digit long" + RESET);
            }
            iteration++;
            phoneNum = scanner.nextLine();
            replaced = phoneNum.replaceAll("\\s", "");
            isValid = replaced.matches("\\d+");
            count = replaced.length();
        } while (!isValid || count != 11);

        return phoneNum;
    }

    /**
     * make sures that user entered an integer between bottomBound and topBound
     * @param scanner scanner which will be used to get user input
     * @param bottomBound min value that user can enter
     * @param topBound max value that user can enter
     * @return integer that meets the given requirements
     */
    private int getValidInput(Scanner scanner, int bottomBound, int topBound){
        int input = -1;
        int check;
        do {
            check = 0;
            try {
                input = Integer.parseInt(scanner.nextLine());
                if(input < bottomBound || input > topBound){
                    System.out.println(RED + "Please enter an integer between " + bottomBound + " and " + topBound + RESET);
                }
            }
            catch (NumberFormatException e){
                System.out.println(RED + "Please enter an integer between " + bottomBound + " and " + topBound + RESET);
                check = 1;
            }
        } while (input < bottomBound || input > topBound || check == 1);
        return input;
    }

    private int[] shortestPath(Patient patient){
        Appointment closestAppointment;
        for(Appointment appointment : patient.getAppointments()){
            if(appointment.getStatus().equals(StatusType.TAKEN)){
                closestAppointment = appointment;
                break;
            }
        }
    }
}