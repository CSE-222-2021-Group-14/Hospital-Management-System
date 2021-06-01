import java.io.*;
import java.util.*;

public class HospitalManagementSystem implements Serializable {
    private HashMap<String,Doctor> doctors;
    private HashMap<String,Patient> patients;
    private HashMap<String,Receptionist> receptionists;
    private HashMap<String,Nurse> nurses;
    private int vaccineAge;

    public int getVaccineAge() {
        return vaccineAge;
    }

    public void setVaccineAge(int vaccineAge) {
        this.vaccineAge = vaccineAge;
    }

    public HashMap<String, Doctor> getDoctors() {
        return doctors;
    }

    public HashMap<String, Patient> getPatients() {
        return patients;
    }

    public HashMap<String, Receptionist> getReceptionists() {
        return receptionists;
    }

    public HashMap<String, Nurse> getNurses() {
        return nurses;
    }

    public HospitalManagementSystem() throws IOException, ClassNotFoundException {
        //deserialize(this);
    }

    public Patient findPatient(String ID) throws NoSuchElementException{
        return patients.get(ID);
    }

    public Doctor findDoctor(String ID) throws NoSuchElementException{
        return doctors.get(ID);
    }

    private void serialize() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("tmp/data.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    @SuppressWarnings("unchecked")/*
    private void deserialize(Hospital_Management_System system) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("tmp/data.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        system = (Hospital_Management_System) in.readObject();
        in.close();
        fileIn.close();
    }
    */

    public void runSystem(){

    }
}