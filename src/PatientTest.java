import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    /**
     * Test : PatientTest : addAppointment
     * pre : Needed objects must be constructed.
     * post : Adds appointment to its appointments.
     * Constructs patient and appointment objects.
     * Adds appointment.
     */
    @org.junit.jupiter.api.Test
    void addAppointment() {
        System.out.println("Begin Test #"+18);
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222333", "05554443322", "12345678");
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222334", "05554443323", "12345678");
        LocalDateTime time = null;
        Department department = DERMATOLOGY;
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, time, department);
        p.addAppointment(pa);
        System.out.println("Patient ID  : "+p.getID());
        System.out.println("Test Result : Added appointment.");
        System.out.println("End Test #"+18);
    }

    /**
     * Test : PatientTest : cancelAppointment
     * pre : Needed objects must be constructed.
     * post : Cancels appointment from its appointments.
     * Constructs patient and appointment objects.
     * Cancels appointment.
     */
    @org.junit.jupiter.api.Test
    void cancelAppointment() {
        System.out.println("Begin Test #"+19);
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222333", "05554443322", "12345678");
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222334", "05554443323", "12345678");
        LocalDateTime time = null;
        Department department = DERMATOLOGY;
        PolyclinicAppointment pa1 = new PolyclinicAppointment(p, d, time, department);
        p.addAppointment(pa1);
        PolyclinicAppointment pa2 = new PolyclinicAppointment(p, d, time, department);
        p.addAppointment(pa2);
        p.viewAppointments();
        p.cancelAppointment(pa2);
        p.viewAppointments();
        System.out.println("Patient ID  : "+p.getID());
        System.out.println("Test Result : Canceled appointment.");
        System.out.println("End Test #"+19);
    }

    /**
     * Test : PatientTest : viewAppointments
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs patient object.
     * Lists patient's all appointments.
     */
    @org.junit.jupiter.api.Test
    void viewAppointments() {
        System.out.println("Begin Test #"+20);
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222333", "05554443322", "12345678");
        Doctor d = new Doctor("TestDoctorName", "TestDoctorSurname", "00111222334", "05554443323", "12345678");
        LocalDateTime time = null;
        Department department = DERMATOLOGY;
        PolyclinicAppointment pa = new PolyclinicAppointment(p, d, time, department);
        p.addAppointment(pa);
        p.viewAppointments();
        System.out.println("Patient ID  : "+p.getID());
        System.out.println("Test Result : Viewed appointments.");
        System.out.println("End Test #"+20);
    }

    /**
     * Test : PatientTest : viewPrescriptions
     * pre : Needed objects must be constructed.
     * post : Nothing changes.
     * Constructs patient object.
     * Lists patient's all prescriptions.
     */
    @org.junit.jupiter.api.Test
    void viewPrescriptions() {
        System.out.println("Begin Test #"+21);
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222333", "05554443322", "12345678");
        p.viewPrescriptions();
        System.out.println("Patient ID  : "+p.getID());
        System.out.println("Test Result : Viewed prescriptions.");
        System.out.println("End Test #"+21);
    }
}