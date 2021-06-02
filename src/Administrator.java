public class Administrator extends AbstractPerson implements Staff {

    public Administrator(String name, String surname, String ID, String phoneNum, String passwword) {
        super(name, surname, ID, phoneNum,passwword);
    }
    void addBed(int num, HospitalManagementSystem h){
        for (int i = 0; i < num; i++) {
            h.getDorm().add(new Bed());
        }
    }
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
    void viewAllStaff(HospitalManagementSystem h){
        System.out.println("Doctors:");
        //traverse map then to string
        System.out.println(h.getDoctors().toString());
        System.out.println("************************************");
        System.out.println("Nurses:");
        System.out.println(h.getNurses().toString());
        System.out.println("************************************");
        System.out.println("Receptionists:");
        System.out.println(h.getReceptionists().toString());
    }
    void viewSpecificStaff(String ID, int mod, HospitalManagementSystem h){
        //menude mod iste(1,2,3)
        if (mod == 1)
            System.out.println(h.getDoctors().get(ID).toString());

        else if (mod == 2)
            System.out.println(h.getNurses().get(ID).toString());

        else
            System.out.println(h.getReceptionists().get(ID).toString());
    }
    void viewDormStatus(HospitalManagementSystem h){
        float status = (float)(h.getOccupiedBedNum()/ h.getDorm().size()) * 100;
        System.out.println("Dorm occupancy rate is %" + status);
    }
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
    void setVaccineAge(int age, HospitalManagementSystem h){
        h.setVaccineAge(age);
    }

}
