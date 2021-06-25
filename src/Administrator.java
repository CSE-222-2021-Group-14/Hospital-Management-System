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
        System.out.println("Doctors:");
        for (Map.Entry<String,Doctor> entry: h.getDoctors().entrySet()) {
            System.out.println("name: " + entry.getValue().name + " surname: " + entry.getValue().surname + " ID: " + entry.getValue().ID);
        }
        System.out.println("************************************");
        System.out.println("Nurses:");
        for (Map.Entry<String,Nurse> entry: h.getNurses().entrySet()) {
            System.out.println("name: " + entry.getValue().name + " surname: " + entry.getValue().surname + " ID: " + entry.getValue().ID);
        }
        System.out.println("************************************");
        System.out.println("Receptionists:");
        for (Map.Entry<String,Receptionist> entry: h.getReceptionists().entrySet()) {
            System.out.println("name: " + entry.getValue().name + " surname: " + entry.getValue().surname + " ID: " + entry.getValue().ID);
        }
    }
    
        void SortByNameAllStaff(HospitalManagementSystem h){
        System.out.println("Doctors:");
        ArrayList <String> SortDoctors = new ArrayList<>();
        for (Map.Entry<String,Doctor> entry: h.getDoctors().entrySet()) {
            String temp = entry.getValue().getName();
            SortDoctors.add(temp);
        }
        String[]sortDoctors = (String[]) SortDoctors.toArray();
        QuickSort.quickSort(sortDoctors,0,SortDoctors.size()-1);
        for (int i = 0; i < SortDoctors.size(); ++i){
            System.out.println("name: " + sortDoctors[i]);
        }

        System.out.println("************************************");
        System.out.println("Nurses:");
        ArrayList <String> SortNurses = new ArrayList<>();
        for (Map.Entry<String,Nurse> entry: h.getNurses().entrySet()) {
            String temp = entry.getValue().getName();
            SortNurses.add(temp);
        }
        String[]sortNurses = (String[]) SortNurses.toArray();
        QuickSort.quickSort(sortNurses,0,SortDoctors.size()-1);
        for (int i = 0; i < SortNurses.size(); ++i){
            System.out.println("name: " + sortNurses[i]);
        }


        System.out.println("************************************");
        System.out.println("Receptionists:");
        RedBlackTree <String> redBlackTree = new RedBlackTree<>();
        for (Map.Entry<String,Receptionist> entry: h.getReceptionists().entrySet()) {
          String temp = entry.getValue().getName();
          redBlackTree.add(temp);
        }
        //redBlackTree.inOrderTraverse() not implemented yet
    }
    

    /**
     * Prints specific staff infos.
     * @param ID is the identification number of the staff.
     * @param mod is the number that selecting the stuff role.
     * @param h is the hospital management system.
     */
    void viewSpecificStaff(String ID, int mod, HospitalManagementSystem h){
        //menude mod iste(1,2,3)
        Staff staff = mod == 1 ? h.getDoctors().get(ID) : (mod == 2 ? h.getNurses().get(ID): h.getReceptionists().get(ID));
        System.out.println(staff == null ? "There is no staff with this ID number" : staff);
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
}
