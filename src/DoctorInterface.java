public interface DoctorInterface {
    PatientInterface callPatient();
    void viewAppointments();
    void viewPatientMedicalInfo(PatientInterface patient);
    void viewPatientPrevAppointments(PatientInterface patient);
    //void setAppointment(Patient patient, time);
    void setPatientStatus(PatientInterface patient);
    void viewInpatients();
    void clearSchedule();
}