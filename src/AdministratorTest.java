import org.junit.Test;

class AdministratorTest {
    /**
     * Test : AdministratorTest : addBed
     * pre : Needed objects must be constructed.
     * post : Added num times to dorm.
     * Constructs administrator and hospital management system objects, and defines number of beds to add.
     * Adds num times beds to dorm.
     */
    @Test
    void addBed() {
        System.out.println("Begin Test #"+1);
        Administrator a = new Administrator("TestAdminName", "TestAdminSurname", "00111222333", "05554443322", "12345678");
        int numAdd = 5;
        HospitalManagementSystem hms = new HospitalManagementSystem();
        a.addBed(numAdd, hms);
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
    @Test
    void removeBed() {
        System.out.println("Begin Test #"+2);
        Administrator a = new Administrator("TestAdminName", "TestAdminSurname", "00111222333", "05554443322", "12345678");
        int numAdd = 5;
        int numRemove = 1;
        HospitalManagementSystem hms = new HospitalManagementSystem();
        a.addBed(numAdd, hms);
        a.removeBed(numRemove, hms);
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
    @Test
    void viewAllStaff() {
        System.out.println("Begin Test #"+3);
        Administrator a = new Administrator("TestAdminName", "TestAdminSurname", "00111222333", "05554443322", "12345678");
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Receptionist s1 = new Receptionist("TestStaffName1", "TestStaffSurname1", "00222333444", "05553332211", "12345678");
        Receptionist s2 = new Receptionist("TestStaffName2", "TestStaffSurname2", "00222333445", "05553332212", "12345678");
        Receptionist s3 = new Receptionist("TestStaffName3", "TestStaffSurname3", "00222333446", "05553332213", "12345678");
        a.hireStaff(s1, hms);
        a.hireStaff(s2, hms);
        a.hireStaff(s3, hms);
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
    @Test
    void viewSpecificStaff() {
        System.out.println("Begin Test #"+4);
        Administrator a = new Administrator("TestAdminName", "TestAdminSurname", "00111222333", "05554443322", "12345678");
        String id = "00222333444";
        // look here xxx
        int mod = 1;
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Receptionist s1 = new Receptionist("TestStaffName1", "TestStaffSurname1", "00222333444", "05553332211", "12345678");
        Receptionist s2 = new Receptionist("TestStaffName2", "TestStaffSurname2", "00222333445", "05553332212", "12345678");
        Receptionist s3 = new Receptionist("TestStaffName3", "TestStaffSurname3", "00222333446", "05553332213", "12345678");
        a.hireStaff(s1, hms);
        a.hireStaff(s2, hms);
        a.hireStaff(s3, hms);
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
    @Test
    void viewDormStatus() {
        System.out.println("Begin Test #"+5);
        Administrator a = new Administrator("TestAdminName", "TestAdminSurname", "00111222333", "05554443322", "12345678");
        HospitalManagementSystem hms = new HospitalManagementSystem();
        int numAdd = 50;
        a.addBed(numAdd, hms);
        int numRemove = 20;
        a.removeBed(numRemove, hms);
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
    @Test
    void hireStaff() {
        System.out.println("Begin Test #"+6);
        Administrator a = new Administrator("TestAdminName", "TestAdminSurname", "00111222333", "05554443322", "12345678");
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Receptionist s1 = new Receptionist("TestStaffName1", "TestStaffSurname1", "00222333444", "05553332211", "12345678");
        Receptionist s2 = new Receptionist("TestStaffName2", "TestStaffSurname2", "00222333445", "05553332212", "12345678");
        Receptionist s3 = new Receptionist("TestStaffName3", "TestStaffSurname3", "00222333446", "05553332213", "12345678");
        a.hireStaff(s1, hms);
        a.hireStaff(s2, hms);
        a.viewAllStaff(hms);
        a.hireStaff(s3, hms);
        a.viewAllStaff(hms);
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
    @Test
    void fireStaff() {
        System.out.println("Begin Test #"+7);
        Administrator a = new Administrator("TestAdminName", "TestAdminSurname", "00111222333", "05554443322", "12345678");
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Receptionist s1 = new Receptionist("TestStaffName1", "TestStaffSurname1", "00222333444", "05553332211", "12345678");
        Receptionist s2 = new Receptionist("TestStaffName2", "TestStaffSurname2", "00222333445", "05553332212", "12345678");
        Receptionist s3 = new Receptionist("TestStaffName3", "TestStaffSurname3", "00222333446", "05553332213", "12345678");
        a.hireStaff(s1, hms);
        a.hireStaff(s2, hms);
        a.hireStaff(s3, hms);
        a.viewAllStaff(hms);
        a.fireStaff(s3, hms);
        a.viewAllStaff(hms);
        System.out.println("Admin ID    : "+a.getID());
        System.out.println("Test Result : Fired a staff.");
        System.out.println("End Test #"+7);
    }
}