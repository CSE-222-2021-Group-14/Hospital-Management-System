import static org.junit.jupiter.api.Assertions.*;

class ReceptionistTest2 {

    /**
     * Test : ReceptionistTest2 : newPatientRegistration
     * pre : Needed objects must be constructed.
     * post : Patient added to system.
     * Constructs receptionist and hospital management objects, and patient information strings created.
     * Adds patient to system.
     */
    @org.junit.jupiter.api.Test
    void newPatientRegistration() {
        System.out.println("Begin Test #"+22);
        Receptionist r = null;
        String name = "Jane";
        String surname = "Doe";
        String id = "00111222333";
        String phoneNum = "123";
        String password = "***";
        HospitalManagementSystem hms = null;
        r.newPatientRegistration(name, surname, id, phoneNum, password, hms);
        System.out.println("Receptionist ID : "+r.getID());
        System.out.println("Test Result     : Registered new patient.");
        System.out.println("End Test #"+22);
    }

    /**
     * Test : ReceptionistTest2 : newPatientRegistration
     * pre : Needed objects must be constructed.
     * post : Confirmed given appointment to the system.
     * Constructs receptionist and hospital management objects.
     * Adds appointment to system.
     */
    @org.junit.jupiter.api.Test
    void confirmAppointments() {
        System.out.println("Begin Test #"+23);
        Receptionist r = null;
        String id = "00111222333";
        HospitalManagementSystem hms = null;
        r.confirmAppointments(id, hms);
        System.out.println("Receptionist ID : "+r.getID());
        System.out.println("Test Result     : Confirmed coming appointments.");
        System.out.println("End Test #"+23);
    }
}