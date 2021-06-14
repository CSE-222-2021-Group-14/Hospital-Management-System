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
    private TreeMap<String, Administrator> administrators;
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

    public HospitalManagementSystem() throws IOException, ClassNotFoundException {

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

    }

    private Person signIn(){
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
                System.out.println(RED + "Your ID must be eleven-digit long" + RESET);
            }
            ID = scanner.nextLine();
            counter++;
        } while (ID.length() != 11 || !ID.matches("\\d+") || !IDs.add(ID));

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