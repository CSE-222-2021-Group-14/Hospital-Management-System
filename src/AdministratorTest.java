import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {
    /**
     * Test : AdministratorTest : addBed
     * pre : Needed objects must be constructed.
     * post : Added num times to dorm.
     * Constructs administrator and hospital management system objects, and defines number of beds to add.
     * Adds num times beds to dorm.
     */
    @org.junit.jupiter.api.Test
    void addBed() {
        System.out.println("Begin Test #"+1);
        Administrator a = null;
        int num = 1;
        HospitalManagementSystem hms = null;
        a.addBed(num, hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Added "+num+" beds to dorm.");
        System.out.println("End Test #"+1);
    }

    /**
     * Test : AdministratorTest : removeBed
     * pre : Needed objects must be constructed, dorm must have beds.
     * post : Removed num times bed from dorm.
     * Constructs administrator and hospital management system objects, and defines number of beds to remove.
     * Removes num times beds from dorm.
     */
    @org.junit.jupiter.api.Test
    void removeBed() {
        System.out.println("Begin Test #"+2);
        Administrator a = null;
        int num = 1;
        HospitalManagementSystem hms = null;
        a.removeBed(num, hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Removed "+num+" beds from dorm.");
        System.out.println("End Test #"+2);
    }

    /**
     * Test : AdministratorTest : viewAllStaff
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs administrator and hospital management system objects.
     * Lists all staffs.
     */
    @org.junit.jupiter.api.Test
    void viewAllStaff() {
        System.out.println("Begin Test #"+3);
        Administrator a = null;
        HospitalManagementSystem hms = null;
        a.viewAllStaff(hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Viewed all staff.");
        System.out.println("End Test #"+3);
    }

    /**
     * Test : AdministratorTest : viewSpecificStaff
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs administrator and hospital management system objects, and defines a string id and int mod.
     * Prints specific staff's information.
     */
    @org.junit.jupiter.api.Test
    void viewSpecificStaff() {
        System.out.println("Begin Test #"+4);
        Administrator a = null;
        String id = "001";
        int mod = 0;
        HospitalManagementSystem hms = null;
        a.viewSpecificStaff(id, mod, hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Viewed specific staff.");
        System.out.println("End Test #"+4);
    }

    /**
     * Test : AdministratorTest : viewDormStatus
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs administrator and hospital management system objects.
     * Prints dorm status.
     */
    @org.junit.jupiter.api.Test
    void viewDormStatus() {
        System.out.println("Begin Test #"+5);
        Administrator a = null;
        HospitalManagementSystem hms = null;
        a.viewDormStatus(hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Viewed dorm status.");
        System.out.println("End Test #"+5);
    }

    /**
     * Test : AdministratorTest : hireStaff
     * pre : Needed objects must be constructed.
     * post : Adds staff to system.
     * Constructs administrator, staff and hospital management system objects.
     * Hired staff to system.
     */
    @org.junit.jupiter.api.Test
    void hireStaff() {
        System.out.println("Begin Test #"+6);
        Administrator a = null;
        Staff s = null;
        HospitalManagementSystem hms = null;
        a.hireStaff(s, hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Hired new staff.");
        System.out.println("End Test #"+6);
    }

    /**
     * Test : AdministratorTest : fireStaff
     * pre : Needed objects must be constructed, staff must be exists in system.
     * post : Removes staff from system.
     * Constructs administrator, staff and hospital management system objects.
     * Fired staff from system.
     */
    @org.junit.jupiter.api.Test
    void fireStaff() {
        System.out.println("Begin Test #"+7);
        Administrator a = null;
        Staff s = null;
        HospitalManagementSystem hms = null;
        a.fireStaff(s, hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Fired a staff.");
        System.out.println("End Test #"+7);
    }
}