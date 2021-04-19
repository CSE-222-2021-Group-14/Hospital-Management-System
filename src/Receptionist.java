public interface Receptionist {
    Patient newPatientRegistration(String name, String surname, String ID, String phoneNum);
    boolean confirmAppointments(String ID);
}
