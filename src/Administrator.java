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
        //num kadar bos yatak varsa kaldir
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
    void viewSpecificStaff(String ID, int mod){
        //menude mod iste(1,2,3)
        if (staff instanceof Doctor)
            System.out.println(h.getDoctors().get(staff.getID()).toString());

        else if (staff instanceof Nurse)
            System.out.println(h.getNurses().get(staff.getID()).toString());

        else
            System.out.println(h.getReceptionists().get(staff.getID()).toString());
    }
    void viewDormStatus(){
        //listeyi gez bolu dos say oranla
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
