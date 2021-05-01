import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class Hospital_Management_System implements Serializable {
    OrderedList<Doctor> doctors;//hashmap olucak
    OrderedList<Patient> patients;//hashmap olucak
    OrderedList<Receptionist> receptionists;//hashmap olucak

    public Hospital_Management_System() throws IOException, ClassNotFoundException {
        deserialize(this);
    }

    public Patient findPatient(String ID) throws NoSuchElementException{
        for(Patient patient : patients){
            if(patient.getID().equals(ID)){
                return patient;
            }
        }
        throw new NoSuchElementException();
    }

    public Doctor findDoctor(String ID) throws NoSuchElementException{
        for(Doctor doctor : doctors){
            if(doctor.getID().equals(ID)){
                return doctor;
            }
        }
        throw new NoSuchElementException();
    }

    private void serialize() throws IOException {
        FileOutputStream fileOut = new FileOutputStream("tmp/data.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    @SuppressWarnings("unchecked")
    private void deserialize(Hospital_Management_System system) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("tmp/data.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        system = (Hospital_Management_System) in.readObject();
        in.close();
        fileIn.close();
    }

    public void runSystem(){

    }
}