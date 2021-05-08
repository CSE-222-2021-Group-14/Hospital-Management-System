import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class HospitalManagementSystem implements Serializable {
    BinarySearchTree<Doctor> doctors;// olucak
    BinarySearchTree<Patient> patients;//hashmap olucak
    BinarySearchTree<Receptionist> receptionists;//hashmap olucak

    public HospitalManagementSystem() throws IOException, ClassNotFoundException {
        //deserialize(this);
    }

    public Patient findPatient(String ID) throws NoSuchElementException{
        return patients.find(new Patient(null,null,ID,null));
    }

    public Doctor findDoctor(String ID) throws NoSuchElementException{
        return doctors.find(new Doctor(null,null,ID,null,null));
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