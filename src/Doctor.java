public interface Doctor {
    Patient callPatient();
    void viewAppointments();
    void viewPatientMedicalInfo(Patient patient);
    void viewPatientPrevAppointments(Patient patient);
    //void setAppointment(Patient patient, time);
    void setPatientStatus(Patient patient);
    void viewInpatients();
    void clearSchedule();
}