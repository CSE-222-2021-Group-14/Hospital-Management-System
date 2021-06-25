import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NurseTest {

    /**
     * Test : NurseTest : vaccinate
     * pre : Needed objects must be constructed, appointments must be not empty.
     * post : Appointments does not contain last vaccine appointment, because of it must be done.
     * Constructs patient, localdatetime, appointment (vaccine appointment) and nurse objects.
     * Adds last vaccine appointment to nurse's appointments priority queue.
     */
    @org.junit.jupiter.api.Test
    void vaccinate() {
        Nurse n = new Nurse("TestNurseName", "TestNurseSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        // look here xxx
        LocalDateTime time = null;
        VaccineAppointment va = new VaccineAppointment(p, n, time);
        n.add(va);
        n.vaccinate();
        System.out.println("Test #"+15);
        System.out.println("Nurse ID    : "+n.getID());
        System.out.println("Test Result : Vaccinated.");
    }

    /**
     * Test : NurseTest : takeCare
     * pre  : Needed objects must be constructed, requests must be not empty.
     * post : Requests does not contain last request, because of it must be done.
     * Constructs patient, localdatetime and nurse objects.
     * Adds patient p who requested take care to nurse's requests queue.
     */
    @org.junit.jupiter.api.Test
    void takeCare() {
        Nurse n = new Nurse("TestNurseName", "TestNurseSurname", "00111222333", "05554443322", "12345678");
        n.takeCare();
        System.out.println("Test #"+16);
        System.out.println("Nurse ID    : "+n.getID());
        System.out.println("Test Result : Taken care.");
    }

    /**
     * Test : NurseTest : add
     * pre  : Needed objects must be constructed, appointments priority queue does not contain appointment a.
     * post : Appointments priority queue contains appointment a.
     * Constructs patient, localdatetime, appointment (type vaccine appointment) and nurse objects.
     * Adds appointment to nurse's appointments priority queue.
     */
    @org.junit.jupiter.api.Test
    void add() {
        Nurse n = new Nurse("TestNurseName", "TestNurseSurname", "00111222333", "05554443322", "12345678");
        Patient p = new Patient("TestPatientName", "TestPatientSurname", "00111222334", "05554443323", "12345678");
        // look here xxx
        LocalDateTime time = null;
        VaccineAppointment va = new VaccineAppointment(p, n, time);
        n.add(va);
        System.out.println("Test #"+17);
        System.out.println("Nurse ID    : "+n.getID());
        System.out.println("Test Result : Added vaccine appointment.");
    }
}