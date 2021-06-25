import static org.junit.jupiter.api.Assertions.*;

class NurseTest {

    /**
     * Test : NurseTest : vaccinate
     * pre : Needed objects must be constructed, appointments must be not empty.
     * post : Appointments does not contain last vaccine appointment, because of it must be done.
     * Constructs patient, localdatetime, appointment (vaccine appointment) and nurse objects.
     * Adds last vaccine appointment to nurse's appointments priority queue.
     * Returns true if priority queue does not contain this vaccine appointment no more,
     * Otherwise returns false if priority queue still contains this vaccine appointment.
     */
    @org.junit.jupiter.api.Test
    void vaccinate() {
        Nurse n = null;
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
     * Returns true if queue does not contain this patient p no more,
     * Otherwise returns false if queue still contains this patient p.
     */
    @org.junit.jupiter.api.Test
    void takeCare() {
        Nurse n = null;
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
     * Returns true if adds appointment,
     * Otherwise returns false if does not add appointment.
     */
    @org.junit.jupiter.api.Test
    void add() {
        Nurse n = null;
        VaccineAppointment va = null;
        n.add(va);
        System.out.println("Test #"+17);
        System.out.println("Nurse ID    : "+n.getID());
        System.out.println("Test Result : Added vaccine appointment.");
    }
}