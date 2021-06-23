import Graph.DijkstrasAlgorithm;
import Graph.Edge;
import Graph.ListGraph;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
        Random random = new Random();
        mapOfHospital = new ListGraph(11, false);
        vertices = new ArrayList<>();

        vertices.add("DESK");
        for(Department d : Department.values()){
            vertices.add(d.toString());
        }
        vertices.add("VACCINATION");
        vertices.add("WC");
        for(int i = 0; i < vertices.size(); i++){
            for(int j = 0; j < vertices.size(); j++){
                if(j != i) {
                    randomWeight = random.nextInt(45) + 5;
                    mapOfHospital.insert(new Edge(i, j, randomWeight));
                }
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

                    int functionality = 0;
                    while (functionality != 5){
                        System.out.println("Patient Menu\nWelcome " + patient.getName() + " " + patient.getSurname() +
                                "\n1)View Prescriptions\n2)View Appointments\n3)Get An Appointment\n4)Cancel An Appointment\n" +
                                "5)Shortest Way To A Location In The Hospital\n6)Exit");
                        functionality = getValidInput(scanner,1,6);

                        switch (functionality) {
                            case 1 -> patient.viewPrescriptions();
                            case 2 -> patient.viewAppointments();
                            case 3 -> getAnAppointment(patient);
                            case 4 -> {
                                ArrayList<Appointment> activeAppointments = activeAppointments(patient);
                                if(patient.getAppointments().isEmpty() || activeAppointments.isEmpty()) {
                                    System.out.println("You don't have any appointments");
                                }
                                else {
                                    System.out.println("Please choose the appointment to cancel");
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
                                    int counter = 1;
                                    for(Appointment a : activeAppointments){
                                        System.out.println(counter++ + ")Time: " + dtf.format(a.getTime()));
                                    }
                                    int cancelIndex = getValidInput(scanner, 1, counter - 1);
                                    activeAppointments.get(cancelIndex).setStatus(StatusType.CANCELLED);
                                }
                            }
                            case 5 -> {
                                System.out.println("Please select your current location\n1)Desk");
                                Department.printDepartments();
                                System.out.println("10)Vaccination\n11)Wc");
                                int current = getValidInput(scanner, 1, 11);
                                System.out.println("Please select your destination\n1)Desk");
                                Department.printDepartments();
                                System.out.println("10)Vaccination\n11)Wc");
                                int dest = getValidInput(scanner, 1, 11);
                                int[][] result = shortestPath(patient, current, dest);
                                if(result[0].length == 0) System.out.println("You are already there");
                                else if(result[0].length == 1){
                                    System.out.println("Directly go to the " + vertices.get(result[0][0]).toLowerCase() +
                                            " (" + result[1][0] + " meters)");
                                }
                                else {
                                    for(int i = 0; i < result[0].length; i++){
                                        System.out.println((i == 0 ? "Firstly" : "Then") + " go to the " +
                                                vertices.get(result[0][i]).toLowerCase() + " (" + result[1][i] + " meters)");
                                    }
                                }
                            }
                            case 6 -> System.out.println("Returning To The Main Menu");
                        }
                    }
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

                    if(timeDifference != 0) doctor.reconfigurePatientQueue();

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
                        System.out.println("Doctor Menu\nWelcome " + doctor.getName() + " " + doctor.getSurname() +
                                "\n1)Call the next patient\n2)View the medical information of the current patient\n" +
                                "3)View the previous appointments of the current patient\n4)View today's appointments\n" +
                                "5)View inpatients\n6)Clear today's schedule\n7)Exit");
                        functionality = getValidInput(scanner, 1, 7);
                        switch (functionality) {
                            case 1 -> {
                                PolyclinicAppointment nextAppointment = doctor.callNextAppointment();
                                if(!nextAppointment.getStatus().equals(StatusType.TAKEN)){
                                    System.out.println("There is no appointment at this time");
                                } else{
                                    current = nextAppointment.getPatient();
                                    current.getAppointments().find(nextAppointment).setStatus(StatusType.FINISHED);
                                    System.out.println(current.getName() + " " + current.getSurname() + " is called");
                                }
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
                        System.out.println("Administrator Menu\nWelcome " + admin.getName() + " " + admin.getSurname() +
                                "\n1)View Dorm Status\n2)Hire Staff\n3)Fire Staff\n4)View A Staff\n5)Set The Vaccination Age" +
                                "\n6)Exit");
                        select = getValidInput(scanner, 1, 6);
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
                            case 5 -> {
                                System.out.println("Enter the vaccination age");
                                int age = getValidInput(scanner, 0, 65);
                                admin.setVaccineAge(age, this);
                                System.out.println(GREEN + "Vaccination age is set to " + age + " successfully" + RESET);
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
                    int functionality;
                    do {
                        System.out.println("Receptionist Menu\nWelcome " + receptionist.getName() + " " + receptionist.getSurname());
                        System.out.println("1)New Patient Registration\n2)New Appointment\n" +
                                "3)Confirm an Appointment\n4)Return Main Menu");
                        functionality = getValidInput(scanner, 1, 4);
                        switch (functionality) {
                            case 1 -> {
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
                                if (receptionist.newPatientRegistration(patientName,patientSurname,patientID,patientPassword,patientPhoneNum, this))
                                    System.out.println("New Patient Registration is Successful.");
                                else
                                    System.out.println("New Patient Registration is Failed.");
                            }
                            case 2 -> {
                                String patientID;
                                int iteration = 0;
                                do{
                                    if(iteration != 0){
                                        System.out.println(RED + "There is no patient with this ID" + RESET);
                                    }
                                    patientID = getValidID(scanner);
                                    iteration++;
                                } while (patients.get(patientID) == null);
                                getAnAppointment(patients.get(patientID));
                                receptionist.confirmAppointments(patientID, this);
                            }
                            case 3 -> {
                                String patientID;
                                do{
                                    patientID = getValidID(scanner);
                                } while (patients.get(patientID) == null);
                                if (receptionist.confirmAppointments(patientID, this))
                                    System.out.println("Appointments are confirmed.");
                                else
                                    System.out.println("Some appointments are not confirmed.");
                            }
                            case 4 -> System.out.println("Returning To The Main Menu");
                        }
                    }while (functionality != 5);
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
                        System.out.println("Nurse Menu\nWelcome " + nurse.getName() + " " + nurse.getSurname());
                        System.out.println("1)Vaccinate Patient\n2)Take Care Patient\n3)Exit");
                        nurseChoice = getValidInput(scanner, 1, 3);
                        switch (nurseChoice) {
                            case 1 -> nurse.vaccinate();
                            case 2 -> nurse.takeCare();
                            case 3 -> System.out.println("Returning To The Main Menu");
                        }
                    }
                }
                default -> {
                    try {
                        serialize();
                    } catch (IOException e) {
                        System.out.println("An error occurred while serializing");
                    }
                    System.out.println("GOODBYE");
                }
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

    private int[][] shortestPath(Patient patient, int start, int dest){
        Stack<Integer> stack = new Stack<>();
        int[] parent = new int[vertices.size()];
        double[] distance = new double[vertices.size()];
        int[] shortestPath;
        int[] shortestPathWeights;
        int[][] returnVal = new int[2][];

        DijkstrasAlgorithm.dijkstrasAlgorithm(mapOfHospital, start, parent, distance);
        while (parent[dest] != -1){
            stack.push(dest);
            dest = parent[dest];
        }
        shortestPath = new int[stack.size()];
        shortestPathWeights = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()){
            shortestPath[i++] = stack.pop();
        }
        for(i = 0; i < shortestPath.length; i++){
            shortestPathWeights[i] = (int) distance[i];
        }
        returnVal[0] = shortestPath;
        returnVal[1] = shortestPathWeights;
        return returnVal;
    }

    private ArrayList<Appointment> activeAppointments(Patient patient){
        ArrayList<Appointment> activeAppointments = new ArrayList<>();
        for(Appointment appointment : patient.getAppointments()){
            if(appointment.getStatus().equals(StatusType.TAKEN)){
                activeAppointments.add(appointment);
            }
        }
        return activeAppointments;
    }

    private void getAnAppointment(Patient patient){
        Scanner scanner = new Scanner(System.in);
        ArrayList <Doctor> relevantDoctors = new ArrayList<>();
        int counter = 1;
        int index;
        System.out.println("Please choose appointment type\n1)Polyclinic Appointment\n2)Vaccine Appointment");
        int type = getValidInput(scanner, 1, 2);
        if(type == 1) {
            System.out.println("Please select department");
            Department.printDepartments();
            System.out.println("9)Exit");
            int department = getValidInput(scanner, 1, 9);

            if (department == 9) {
                System.out.println("Returning To The Main Menu");
            } else {
                System.out.println("Please choose doctor");
                for (Map.Entry<String, Doctor> e : doctors.entrySet()) {
                    Doctor temp = e.getValue();
                    if (temp.getDepartment().getNumVal() == department) {
                        relevantDoctors.add(temp);
                        System.out.printf("%d)%s %s\n", counter++, temp.getName(), temp.getSurname());
                    }
                }
                if (relevantDoctors.isEmpty()) {
                    System.out.println("There is no doctor with relevant department currently!");
                } else {
                    index = getValidInput(scanner, 1, counter - 1);
                    System.out.println("Available appointments are listing...");
                    LinkedList<PolyclinicAppointment> appointmentsOfDoctor = relevantDoctors.get(index - 1).getAppointments();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
                    counter = 1;

                    for (PolyclinicAppointment polyclinicAppointment : appointmentsOfDoctor) {
                        if (polyclinicAppointment.getStatus() == StatusType.EMPTY) {
                            System.out.println(counter++ + ")" + dtf.format(polyclinicAppointment.getTime()));
                        }
                    }

                    if (counter == 1) {
                        System.out.println("Currently there is no available appointment!");
                    } else {
                        int appointmentIndex = getValidInput(scanner, 1, counter - 1);
                        patient.addAppointment(new PolyclinicAppointment(patient, relevantDoctors.get(index),
                                appointmentsOfDoctor.get(appointmentIndex - 1).getTime(),
                                relevantDoctors.get(index).getDepartment()));
                    }
                }
            }
        }
        else {
            String date;
            counter = 0;
            System.out.println("Enter vaccination date in dd-MM-yyyy HH:mm format");
            do{
                if(counter != 0){
                    System.out.println(RED + "Date must satisfy dd-MM-yyyy HH:mm format" + RESET);
                }
                date = scanner.nextLine();
                counter++;
            }
            while (!date.matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime time = LocalDateTime.parse(date, dtf);
            Nurse nurse = leastBusyNurse();
            nurse.add(new VaccineAppointment(patient, nurse, time));
        }
    }

    private Nurse leastBusyNurse(){
        Nurse leastBusy = null;
        for(Map.Entry<String, Nurse> entry: nurses.entrySet()){
            if(leastBusy == null) leastBusy = entry.getValue();
            else if(entry.getValue().getAppointmentNumber() < leastBusy.getAppointmentNumber()) leastBusy = entry.getValue();
        }
        return leastBusy;
    }
}