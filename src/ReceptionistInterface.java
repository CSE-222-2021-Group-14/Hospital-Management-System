public interface ReceptionistInterface {
    Patient newPatientRegistration(String name, String surname, String ID, String phoneNum, Hospital_Management_System system);
    boolean confirmAppointments(String ID, Hospital_Management_System system);
}
