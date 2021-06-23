import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ReceptionistTest {

    @Test
    void newPatientRegistration() {
        // Main System
        HospitalManagementSystem system = new HospitalManagementSystem();
        // Receptionist for using its methods
        Receptionist receptionist = new Receptionist("Ahmet", "Mehmet", "13451324","445645","05343648");
        // Registered patient
        boolean added=receptionist.newPatientRegistration("Salih", "Talih", "7424684","34533655","05346415",system);
        if (!added) {
            System.out.println("Registration is not successful.");
            return;
        }
        // Method checks if id is already used or not, if it is not used then adds new patient to system otherwise prints failed.
        if (receptionist.newPatientRegistration("Ali","Veli","35435","87434","05313111", system))
            System.out.println("New Patient Registration is Successful.");
        else
            System.out.println("New Patient Registration is Failed.");
    }

    @Test
    void confirmAppointments() {
        // Main System
        HospitalManagementSystem system = new HospitalManagementSystem();
        // Receptionist for using its methods
        Receptionist receptionist = new Receptionist("Ahmet", "Mehmet", "13451324","445645","05343648");
        // Doctor for creating appointment
        Doctor doctor = new Doctor("Hasan", "Şahin", "435125", "05314645", "34135315",Department.OTOLARYNGOLOGY);
        // Registered patients
        boolean added=receptionist.newPatientRegistration("Salih", "Talih", "7424684","34533655","05346415",system);
        if (!added) {
            System.out.println("Registration is not successful.");
            return;
        }
        added=receptionist.newPatientRegistration("Ali", "Veli", "84532","54102","05341484",system);
        if (!added) {
            System.out.println("Registration is not successful.");
            return;
        }
        // Adding appointments and set confirmed one of them.
        Patient patient=system.findPatient("7424684");
        Appointment appointment = new PolyclinicAppointment(patient, doctor,LocalDateTime.now(),Department.OTOLARYNGOLOGY);
        appointment.setConfirmed(true);
        patient.addAppointment(appointment);
        patient=system.findPatient("84532");
        appointment = new PolyclinicAppointment(patient, doctor,LocalDateTime.now(),Department.OTOLARYNGOLOGY);
        patient.addAppointment(appointment);
        // Method checks if patient has not confirmed appointments , if has then confirms appointments  otherwise prints already confirmed.
        if (receptionist.confirmAppointments("84532", system))
            System.out.println("Appointments are confirmed.");
        else
            System.out.println("Appointments are already confirmed.");
    }
}