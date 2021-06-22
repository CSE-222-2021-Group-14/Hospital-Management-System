import java.util.*;

public class Patient extends AbstractPerson implements Comparable<Patient> {
    private Stack<Appointment> appointments;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<String> disease;
    
    private int age;
    private String bloodType;
    
    
    protected int getAge() {
        return age;
    }

    protected String getBloodType() {
        return bloodType;
    }

    protected ArrayList<String> getDisease() {
        return disease;
    }

    protected Stack<Appointment> getAppointments(){
        return appointments;
    }

    protected ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }

    public Patient(String name, String surname, String ID, String phoneNum, String password){
        super(name, surname, ID, phoneNum, password);
        appointments = new Stack<>();
        prescriptions = new ArrayList<>();
        disease = new ArrayList<>();
    }

    public void addAppointment(Appointment a){
        appointments.push(a);
    }

    public void removeAppointment(Appointment a) {
        if (appointments.search(a) == 1) {
            appointments.remove(a);
        }
        else
            throw new UnsupportedOperationException("Cannot cancel appointment\n");
    }

    public void viewAppointments() {
        if (!appointments.isEmpty())
        appointments.forEach(System.out::println);
        else
            System.out.print("Currently you haven't got any appointment\n");
    }

    public void viewPrescriptions() {
        prescriptions.forEach(System.out::println);
    }

    @Override
    public int compareTo(Patient o) {
        return 0;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Patient: Name = ").append(name).append(",Surname = ").append(surname).append(",ID = ").append(ID).append(",Phone Number = ").append(phoneNum);
        return s.toString();
    }

    void scheduleAppointment(HospitalManagementSystem system){

        TreeMap<String, Doctor> treeMap = system.getDoctors();
        Scanner scanner = new Scanner(System.in);
        ArrayList <Doctor> doctorsStore = new ArrayList<>();
        int choice = 0;
        int counter = 1;


        int indexOfDoctor;

        int flag = 0; //it will be 0 if there isn't any doctor with relevant department.
        while (choice != 9) {
            System.out.println("Please Select Departments:\n");
            Department.printDepartments();
            System.out.println("9) EXIT");
            choice = getValidInputPatient(scanner, 1,9);

            if (choice == 9){
                System.out.println("Exiting...");
            }
            else {
                System.out.println("Please choose doctor:");
                    for(Map.Entry<String, Doctor> entry : treeMap.entrySet()){
                        Doctor temp = entry.getValue();

                        if (temp.getDepartment().getNumVal() == choice){
                            doctorsStore.add(temp);
                            System.out.printf("%d) %s %s \n",counter,temp.getName(),temp.getSurname());
                            counter++;
                            flag = 1;
                        }
                }
                    if (flag == 0){
                        System.out.println("There aren't any doctor with relevant department currently!");
                    }
                    else {
                        flag = 0;
                        choice = getValidInputPatient(scanner, 1, counter-1);
                        indexOfDoctor = choice;
                        System.out.println("Available appointments are listing...");
                        LinkedList <PolyclinicAppointment> tempAppointment = doctorsStore.get(choice+1).getAppointments();
                        counter = 1;
                        for (int i = 0; i < tempAppointment.size(); ++i){
                            if (tempAppointment.get(i).getStatus() == StatusType.EMPTY)
                            {
                                flag = 1;
                                System.out.printf("%d)",counter);
                                System.out.println(tempAppointment.get(i).getTime());
                                counter++;
                            }
                        }
                        if (flag == 0){
                            System.out.println("Currently there aren't any available appointment!");
                        }
                        else{
                            choice = getValidInputPatient(scanner,1, counter-1);
                            PolyclinicAppointment temp = new PolyclinicAppointment(this, doctorsStore.get(indexOfDoctor),tempAppointment.get(choice-1).getTime(),
                                    doctorsStore.get(indexOfDoctor).getDepartment());
                            appointments.add(temp);
                        }

                    }
            }


        }

    }

    void Menu(){

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        Iterator iterator = appointments.iterator();

        while (choice != 5){
            System.out.println("Welcome " + name + "\n1)View Prescriptions\n2)View Appointments\n" +
                    "3)Add Appointments\n4)Cancel Appointments\n5)Exit");

            choice = getValidInputPatient(scanner,1,5);
            switch (choice) {
                case 1 -> {
                    viewPrescriptions();
                }
                case 2 -> {
                    viewAppointments();
                }
                case 3 -> {
                    //scheduleAppointment(system); 
                }

                case 4 -> {
                    if (appointments.size() < 1){
                        System.out.println("You haven't got any appointments");
                    }
                    else {
                        while (iterator.hasNext()) {
                            System.out.println(iterator.next());
                        }
                    }
                }

                case 5-> {
                    System.out.println("\nExiting....");
                }

            }


        }


    }

    private int getValidInputPatient(Scanner scanner, int bottomBound, int topBound){
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
