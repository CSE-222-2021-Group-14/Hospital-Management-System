import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    /**
     * Test : DoctorTest : callNextAppointment
     * pre : Needed objects must be constructed, appointments must be not empty.
     * post : Last appointment's status must be changed as called.
     * Constructs doctor object.
     * Calls next appointment, increases patient's count.
     */
    @org.junit.jupiter.api.Test
    void callNextAppointment() {
        System.out.println("Begin Test #"+8);
        Doctor d = null;
        d.callNextAppointment();
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Called next appointment.");
        System.out.println("End Test #"+8);
    }

    /**
     * Test : DoctorTest : viewAppointments
     * pre : Needed objects must be constructed, appointments must be not empty.
     * post : Nothing changes.
     * Constructs doctor object.
     * Lists doctor's appointments.
     */
    @org.junit.jupiter.api.Test
    void viewAppointments() {
        System.out.println("Begin Test #"+9);
        Doctor d = null;
        d.viewAppointments();
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Viewed appointments.");
        System.out.println("End Test #"+9);
    }

    /**
     * Test : DoctorTest : viewPatientMedicalInfo
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs doctor and patient objects.
     * Prints patient's medical information.
     */
    @org.junit.jupiter.api.Test
    void viewPatientMedicalInfo() {
        System.out.println("Begin Test #"+10);
        Doctor d = null;
        Patient p = null;
        d.viewPatientMedicalInfo(p);
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Viewed patient's medical information.");
        System.out.println("End Test #"+10);
    }

    /**
     * Test : DoctorTest : viewPatientPrevAppointments
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs doctor and patient objects.
     * Prints patient's previous appointments.
     */
    @org.junit.jupiter.api.Test
    void viewPatientPrevAppointments() {
        System.out.println("Begin Test #"+11);
        Doctor d = null;
        Patient p = null;
        d.viewPatientPrevAppointments(p);
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Viewed patient's previous appointments.");
        System.out.println("End Test #"+11);
    }

    /**
     * Test : DoctorTest : viewInpatients
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs doctor and hospital managements system objects.
     * Lists all inpatients.
     */
    @org.junit.jupiter.api.Test
    void viewInpatients() {
        System.out.println("Begin Test #"+12);
        Doctor d = null;
        HospitalManagementSystem hms = null;
        d.viewInpatients(hms);
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Viewed inpatients.");
        System.out.println("End Test #"+12);
    }

    /**
     * Test : DoctorTest : clearSchedule
     * pre : Needed objects must be constructed.
     * post : Doctor's schedule will be cleared.
     * Constructs doctor object.
     * Clears doctor's all schedule.
     */
    @org.junit.jupiter.api.Test
    void clearSchedule() {
        System.out.println("Begin Test #"+13);
        Doctor d = null;
        d.clearSchedule();
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Cleared schedule.");
        System.out.println("End Test #"+13);
    }

    /**
     * Test : DoctorTest : add
     * pre : Needed objects must be constructed.
     * post : Doctor adds appointment to schedule.
     * Constructs doctor and polyclinic appointment objects.
     * Adds appointment to schedule.
     */
    @org.junit.jupiter.api.Test
    void add() {
        System.out.println("Begin Test #"+14);
        Doctor d = null;
        PolyclinicAppointment pa = null;
        d.add(pa);
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Added appointment.");
        System.out.println("End Test #"+14);
    }
}