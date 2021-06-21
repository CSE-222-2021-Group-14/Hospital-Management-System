import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Driver {
    public static void main(String[] args){
        //map.forEach((k, v) -> System.out.println("key:" + k + " value:" + v));
        /*Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.DAY_OF_WEEK));*/
        /*Doctor doctor = new Doctor("a","b", "c", "d", "e", Department.DERMATOLOGY);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
        for(int i = 0; i < 70; i++){
            if(i % 14 == 0){
                System.out.println();
            }
            System.out.println(dtf.format(doctor.appointments.get(i).getTime()));
        }*/
        Doctor doctor = new Doctor("a","b", "c", "d", "e", Department.DERMATOLOGY);
        System.out.println(doctor);
    }

    /*private static HospitalManagementSystem deserialize(HospitalManagementSystem system) throws IOException, ClassNotFoundException {
        File file = new File("tmp/data.ser");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        system = (HospitalManagementSystem) in.readObject();
        in.close();
        fileIn.close();
        return system;
    }*/
}