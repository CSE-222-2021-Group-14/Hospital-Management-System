import java.io.*;
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
                    if(entry == 1) patient = (Patient) login(1);
                    else patient = signIn();
                }
                case 2 -> {
                    Doctor doctor = (Doctor) login(2);
                }
                case 3 -> {
                    Administrator admin = (Administrator) login(3);
                }
                case 4 -> {
                    Receptionist receptionist = (Receptionist) login(4);
                }
                case 5 -> {
                    Nurse nurse = (Nurse) login(5);
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
                case 1 -> person = ((tmp = patients.get(ID)) == null || tmp.password.equals(password)) ? null : tmp;
                case 2 -> person = ((tmp = doctors.get(ID)) == null || tmp.password.equals(password)) ? null : tmp;
                case 3 -> person = (!administrator.ID.equals(ID) || !administrator.password.equals(password)) ? null : administrator;
                case 4 -> person = ((tmp = receptionists.get(ID)) == null || tmp.password.equals(password)) ? null : tmp;
                case 5 -> person = ((tmp = nurses.get(ID)) == null || tmp.password.equals(password)) ? null : tmp;
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
            ID = getValidID(scanner);
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