import org.junit.Test;

import java.time.LocalDateTime;

class ReceptionistTest2 {

    /**
     * Test : ReceptionistTest2 : newPatientRegistration
     * pre : Needed objects must be constructed.
     * post : Patient added to system.
     * Constructs receptionist and hospital management objects.
     * Adds patient to system.
     */
    @Test
    void newPatientRegistration() {
        System.out.println("Begin Test #"+22);
        Receptionist r = new Receptionist(("TestReceptionistName", "TestReceptionistSurname", "00111222333", "05554443322", "12345678");
        HospitalManagementSystem hms = new HospitalManagementSystem();
        r.newPatientRegistration("TestPatientName", "TestPatientSurname", "00111222334", "5554443323", "12345678", hms);
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
        Receptionist r = new Receptionist(("TestReceptionistName", "TestReceptionistSurname", "00111222333", "05554443322", "12345678"););
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        Department department = Department.DERMATOLOGY;
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222335", "05554443324", "12345678", department);
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, LocalDateTime.now(), department);
        p.addAppointment(pa);
        // look here xxx
        r.confirmAppointments("00111222334", hms);
        System.out.println("Receptionist ID : "+r.getID());
        System.out.println("Test Result     : Confirmed coming appointments.");
        System.out.println("End Test #"+23);
    }
}