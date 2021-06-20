import java.util.*;

public class Administrator extends AbstractPerson implements Staff {

    public Administrator(String name, String surname, String ID, String phoneNum, String passwword) {
        super(name, surname, ID, phoneNum,passwword);
    }

    /**
     * Add new bed to the hospital dorm.
     * @param num number of new beds.
     * @param h is the hospital management system
     */
    void addBed(int num, HospitalManagementSystem h){
        for (int i = 0; i < num; i++) {
            h.getDorm().add(new Bed());
        }
    }

    /**
     * Remove beds from the hospital dorm.
     * @param num number of the beds that will be removed.
     * @param h is the hospital management system.
     */
    void removeBed(int num,HospitalManagementSystem h){
        if (h.getDorm().size() - h.getOccupiedBedNum() - num >= 0){
            int i = 0,j = 0;
            while (i < h.getDorm().size()){
                if (h.getDorm().get(i).getBedStatus() == BedStatus.EMPTY) {
                    h.getDorm().remove(i);
                    j++;
                }
                if (j == num)
                    break;

                i++;
            }
        }
        else
            System.out.println("There are no " + num +" empty bed!");
    }

    /**
     * Prints all staff in the hospital except admins.
     * @param h is the hospital managements system
     */
    void viewAllStaff(HospitalManagementSystem h){
        int i = 1;
        System.out.println("Doctors:");
        for (Map.Entry<String,Doctor> entry: h.getDoctors().entrySet()) {
            System.out.println(entry.getValue() + ": " +i);
            i++;
        }
        System.out.println("************************************");
        System.out.println("Nurses:");
        for (Map.Entry<String,Nurse> entry: h.getNurses().entrySet()) {
            System.out.println(entry.getValue()+ ": " +i);
            i++;
        }
        System.out.println("************************************");
        System.out.println("Receptionists:");
        for (Map.Entry<String,Receptionist> entry: h.getReceptionists().entrySet()) {
            System.out.println(entry.getValue()+ ": " +i);
            i++;
        }
    }

    /**
     * Prints specific staff infos.
     * @param ID is the identification number of the staff.
     * @param mod is the number that selecting the stuff role.
     * @param h is the hospital management system.
     */
    void viewSpecificStaff(String ID, int mod, HospitalManagementSystem h){
        //menude mod iste(1,2,3)
        if (mod == 1)
            System.out.println(h.getDoctors().get(ID).toString());

        else if (mod == 2)
            System.out.println(h.getNurses().get(ID).toString());

        else
            System.out.println(h.getReceptionists().get(ID).toString());
    }

    /**
     * Shows occupancy rate of the dorm.
     * @param h is the hospital management system.
     */
    void viewDormStatus(HospitalManagementSystem h){
        float status = (float)(h.getOccupiedBedNum()/ h.getDorm().size()) * 100;
        System.out.println("Dorm occupancy rate is %" + status);
    }

    /**
     * Hires the incoming staff for hospital.
     * @param staff is who will be hired.
     * @param h is the hospital management system.
     */
    void hireStaff(Staff staff, HospitalManagementSystem h){
        if (staff != null && h != null){
            if (staff instanceof Doctor)
                h.getDoctors().put(staff.getID(),(Doctor)staff);

            else if (staff instanceof Nurse)
                h.getNurses().remove(staff.getID());

            else
                h.getReceptionists().remove(staff.getID());
        }
        else
            System.out.println("Staff could not hire!");
    }

    /**
     * Fires the incoming staff from  hospital.
     * @param staff is who will be fired.
     * @param h is the hospital management system.
     */
    void fireStaff(Staff staff, HospitalManagementSystem h){
        try {
            if (staff instanceof Doctor)
                h.getDoctors().remove(staff.getID());

            else if (staff instanceof Nurse)
                h.getNurses().remove(staff.getID());

            else
                h.getReceptionists().remove(staff.getID());
        }catch (Exception e){
            System.out.println("Staff could not fire!");
        }
    }

    /**
     * Sets vaccine age limit.
     * @param age is the limit age.
     * @param h is the hospital management system.
     */
    void setVaccineAge(int age, HospitalManagementSystem h){
        h.setVaccineAge(age);
    }

    void adminMenu(HospitalManagementSystem h){
        int select;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            do {
                System.out.println("*****************************");
                System.out.println("Administration Menu");
                System.out.println("View Dorm Status : 1");
                System.out.println("Hire Staff : 2");
                System.out.println("Fire Staff : 3");
                System.out.println("View a Staff : 4");
                System.out.println("Exit : 5");
                System.out.print("Choice: ");
                try {
                    select = sc.nextInt();
                }catch (Exception e){
                    select = 0;
                }
                sc.nextLine(); // clears the buffer

                if (select != 1 && select != 2 && select != 3 && select != 4 && select != 5) {
                    System.out.println("Invalid Selection!");
                    select = 0;
                }
            } while (select == 0);
            switch (select){
                case 1:
                    int select2;
                    viewDormStatus(h);
                    do {
                        System.out.println("*****************************");
                        System.out.println("Add bed to the Dorm : 1");
                        System.out.println("Remove bed from the Dorm : 2");
                        System.out.println("Exit : 3");
                        System.out.print("Choice: ");
                        try {
                            select2 = sc.nextInt();
                        }catch (Exception e){
                            select2 = 0;
                        }
                        sc.nextLine(); // clears the buffer

                        if (select2 != 1 && select2 != 2 && select2 != 3) {
                            System.out.println("Invalid Selection!");
                            select2 = 0;
                        }
                    } while (select2 == 0);
                    int num;
                    switch (select2){
                        case 1:
                            System.out.println("Please enter the number of bed that will be added:");
                            try {
                                num = sc.nextInt();
                                if (num<=0)
                                    throw new Exception();
                            }catch (Exception e){
                                System.out.println("Invalid number!");
                                break;
                            }
                            addBed(num,h);
                            break;
                        case 2:
                            System.out.println("Please enter the number of bed that will be removed:");
                            try {
                                num = sc.nextInt();
                            }catch (Exception e){
                                System.out.println("Invalid number!");
                                break;
                            }
                            removeBed(num,h);
                            break;
                        default:
                    }
                    break;

                case 2:
                    int select3;
                    do {
                        System.out.println("*****************************");
                        System.out.println("Hire a Doctor : 1");
                        System.out.println("Hire a Nurse : 2");
                        System.out.println("Hire a Receptionist : 3");
                        System.out.println("Exit : 4");
                        System.out.print("Choice: ");
                        try {
                            select3 = sc.nextInt();
                        }catch (Exception e){
                            select3 = 0;
                        }
                        sc.nextLine(); // clears the buffer

                        if (select3 != 1 && select3 != 2 && select3 != 3 && select3 != 4) {
                            System.out.println("Invalid Selection!");
                            select3 = 0;
                        }
                    } while (select3 == 0);
                    String name,surname,ID,phoneNum,passwd;
                    System.out.println("Please enter name of the staff:");
                    name = sc.nextLine();
                    System.out.println("Please enter surname of the staff:");
                    surname = sc.nextLine();
                    System.out.println("Please enter ID of the staff:");
                    ID = sc.nextLine();
                    System.out.println("Please enter phone number of the staff");
                    phoneNum = sc.nextLine();
                    System.out.println("Please enter password of the staff:");
                    passwd = sc.nextLine();
                    int dep,n;
                    Department d;
                    switch (select3){
                        case 1:
                            System.out.println("Please select department of the doctor:");
                            Department.printDepartments();
                            dep = sc.nextInt();
                            switch (dep){
                                case 1:
                                    d = Department.DERMATOLOGY;
                                    break;
                                case 2:
                                    d = Department.INTERNAL_MEDICINE;
                                    break;
                                case 3:
                                    d = Department.NEUROLOGY;
                                    break;
                                case 4:
                                    d = Department.OPHTHALMOLOGY;
                                    break;
                                case 5:
                                    d = Department.OTOLARYNGOLOGY;
                                    break;
                                case 6:
                                    d = Department.PATHOLOGY;
                                    break;
                                case 7:
                                    d = Department.PEDIATRICS;
                                    break;
                                default:
                                    d = Department.SURGERY;
                                    break;
                            }
                            if (dep >= 1 && dep<=8)
                                hireStaff(new Doctor(name,surname,ID,phoneNum,passwd,d),h);
                            else
                                System.out.println("Invalid selection!");
                            break;
                        case 2:
                            hireStaff(new Nurse(name,surname,ID,password,phoneNum),h);
                            break;
                        case 3:
                            hireStaff(new Receptionist(name,surname,ID,password,phoneNum),h);
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Select a staff to fire");
                    viewAllStaff(h);
                    try {
                        n = sc.nextInt();
                        if (n <= 0 || n > h.getDoctors().size()+h.getNurses().size()+h.getReceptionists().size())
                            throw new Exception();
                    }catch (Exception e){
                        System.out.println("Invalid selection!");
                        break;
                    }
                    sc.nextLine(); // clears the buffer
                    if (n <= h.getDoctors().size()){
                        int i = 1;
                        for (Map.Entry<String,Doctor> e: h.getDoctors().entrySet()) {
                           if (i == n) {
                               fireStaff(e.getValue(), h);
                               break;
                           }
                           i++;
                        }
                    }
                    else if (n > h.getDoctors().size() && n<= h.getDoctors().size() + h.getNurses().size()){
                        n = n - h.getDoctors().size();
                        int i = 1;
                        for (Map.Entry<String,Nurse> e: h.getNurses().entrySet()) {
                            if (i == n) {
                                fireStaff(e.getValue(), h);
                                break;
                            }
                            i++;
                        }
                    }
                    else {
                        n = n- h.getDoctors().size()-h.getNurses().size();
                        int i = 1;
                        for (Map.Entry<String,Receptionist> e: h.getReceptionists().entrySet()) {
                            if (i == n) {
                                fireStaff(e.getValue(), h);
                                break;
                            }
                            i++;
                        }
                    }
                    break;

                case 4:
                    int role;
                    String id;
                    System.out.println("Please select role of the stuff that will be shown");
                    System.out.println("Doctor: 1");
                    System.out.println("Nurse: 2");
                    System.out.println("Receptionist: 3");
                    try {
                        role = sc.nextInt();
                        if(role < 1 || role >3)
                            throw new Exception();
                    }catch (Exception e){
                        System.out.println("Invalid selection!");
                        break;
                    }
                    sc.nextLine();
                    System.out.println("Please enter ID of the stuff");
                    id = sc.nextLine();
                    viewSpecificStaff(id,role,h);
                    break;

                default:
                    exit = true;
                    System.out.println("Exit...");
            }
        }
    }
}
