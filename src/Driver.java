import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class Driver {
    public static void main(String[] args){
        //map.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
    }

    private static HospitalManagementSystem deserialize(HospitalManagementSystem system) throws IOException, ClassNotFoundException {
        File file = new File("tmp/data.ser");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        system = (HospitalManagementSystem) in.readObject();
        in.close();
        fileIn.close();
        return system;
    }
}