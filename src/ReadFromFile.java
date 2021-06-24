import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
    public void readFromFile(HospitalManagementSystem system) throws IOException {
        File doctorFile = new File("default_user/doctors");
        File patientFile = new File("default_user/patients");
        File receptionistFile = new File("default_user/receptionists");
        File nurseFile = new File("default_user/nurses");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(doctorFile));
        String row;
        String[] data;
        while ((row = bufferedReader.readLine()) != null){
            if(row.length()<6)
                break;
            data = row.split("\t");
            for(int i=0;i< data.length;i++)
                system.getDoctors().put(data[2],new Doctor(data[0],data[1],data[2],data[3],data[4],getDepartment(Integer.parseInt(data[5]))));
        }
        bufferedReader = new BufferedReader(new FileReader(patientFile));
        row = null;
        data = null;
        while ((row = bufferedReader.readLine()) != null){
            if(row.length()<5)
                break;
            data = row.split("\t");
            for(int i=0;i< data.length;i++)
                system.getPatients().put(data[2],new Patient(data[0],data[1],data[2],data[3],data[4]));
        }
        bufferedReader = new BufferedReader(new FileReader(receptionistFile));
        row = null;
        data = null;
        while ((row = bufferedReader.readLine()) != null){
            if(row.length()<5)
                break;
            data = row.split("\t");
            for(int i=0;i< data.length;i++)
                system.getReceptionists().put(data[2],new Receptionist(data[0],data[1],data[2],data[3],data[4]));
        }
        bufferedReader = new BufferedReader(new FileReader(nurseFile));
        row = null;
        data = null;
        while ((row = bufferedReader.readLine()) != null){
            if(row.length()<5)
                break;
            data = row.split("\t");
            for(int i=0;i< data.length;i++)
                system.getNurses().put(data[2],new Nurse(data[0],data[1],data[2],data[3],data[4]));
        }
        bufferedReader.close();
    }
    public Department getDepartment(int numVal) {
        Department department;
        switch (numVal) {
            case 1 -> department = Department.DERMATOLOGY;
            case 2 -> department = Department.INTERNAL_MEDICINE;
            case 3 -> department = Department.NEUROLOGY;
            case 4 -> department = Department.OPHTHALMOLOGY;
            case 5 -> department = Department.OTOLARYNGOLOGY;
            case 6 -> department = Department.PATHOLOGY;
            case 7 -> department = Department.PEDIATRICS;
            case 8 -> department = Department.SURGERY;
            default -> throw new IllegalStateException("Unexpected value: " + numVal);
        }
        return department;
    }
}
