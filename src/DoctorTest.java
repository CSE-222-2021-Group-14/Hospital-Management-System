import org.junit.Test;

import java.time.LocalDateTime;

class DoctorTest {

    /**
     * Test : DoctorTest : callNextAppointment
     * pre : Needed objects must be constructed, appointments must be not empty.
     * post : Last appointment's status must be changed as called.
     * Constructs doctor object.
     * Calls next appointment, increases patient's count.
     */
    @Test
    void callNextAppointment() {
        System.out.println("Begin Test #"+8);
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        Department department = 0;
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, LocalDateTime.now(), department);
        d.add(pa);
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
    @Test
    void viewAppointments() {
        System.out.println("Begin Test #"+9);
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        Department department = 0;
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, LocalDateTime.now(), department);
        d.add(pa);
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
    @Test
    void viewPatientMedicalInfo() {
        System.out.println("Begin Test #"+10);
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
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
    @Test
    void viewPatientPrevAppointments() {
        System.out.println("Begin Test #"+11);
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        Department department = 0;
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, LocalDateTime.now(), department);
        d.add(pa);
        d.callNextAppointment();
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
    @Test
    void viewInpatients() {
        System.out.println("Begin Test #"+12);
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222333", "05554443322", "12345678");
        HospitalManagementSystem hms = new HospitalManagementSystem();
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
    @Test
    void clearSchedule() {
        System.out.println("Begin Test #"+13);
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        Department department = 0;
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, LocalDateTime.now(), department);
        d.add(pa);
        d.viewAppointments();
        d.clearSchedule();
        d.viewAppointments();
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
    @Test
    void add() {
        System.out.println("Begin Test #"+14);
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        Department department = 0;
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, LocalDateTime.now(), department);
        d.viewAppointments();
        d.add(pa);
        d.viewAppointments();
        System.out.println("Doctor ID   : "+d.getID());
        System.out.println("Test Result : Added appointment.");
        System.out.println("End Test #"+14);
    }
}