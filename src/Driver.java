import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) throws IOException {
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
        /*HospitalManagementSystem system = new HospitalManagementSystem();
        int[][] result = system.dijkstra();
        int[] parent = result[0];
        int[] distance = result[1];
        System.out.println("\nparent array\n" + Arrays.toString(parent));
        System.out.println("\ndistance array\n" + Arrays.toString(distance));*/
        HospitalManagementSystem system = new HospitalManagementSystem();
        system.runSystem();
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
    public void defaultUsers(){
        try {
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> surnames = new ArrayList<>();
            FileWriter fileWriter = new FileWriter("DefaultUsers/default_patients.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}