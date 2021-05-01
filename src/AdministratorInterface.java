public interface AdministratorInterface {
   // void addDoctor(Doctor doctor);
    //void removeDoctor(Doctor doctor);
    void addBeds(int num);
    void removeBeds(int num);
    void viewAllStaff();
    void viewSpecificStaff(Staff staff);
    void viewDormStatus();
    void hireStaff(Staff staff);
    void fireStaff(Staff staff);
    void setVaccineAge(int age, Hospital_Management_System system);
    //void addReceptionist(Receptionist rec);
    //void removeReceptionist(Receptionist rec);
}
