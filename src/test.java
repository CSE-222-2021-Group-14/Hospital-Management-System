import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class test {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        Calendar calendar = Calendar.getInstance();
        LocalDateTime ldt = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
        LocalDateTime minus5days = ldt.minusDays(580).plusMinutes(3);


        System.out.println(dtf.format(localDateTime));
        System.out.println(dtf.format(ldt));
        System.out.println(dtf.format(minus5days));
        System.out.println(timeInterval(ldt, minus5days));

        try {
            System.out.println(new Prescription("abs", 1, 0));
        } catch (InvalidPrescriptionException e) {
            System.out.println("invalid prescription");
        }
        System.out.println("\u001B[31m" + "cancelled" + "\u001B[0m");
        /*ArrayList<Person> array = new ArrayList<>();
        File file = new File("tmp/data.ser");
        if(file.exists()){
            System.out.println("EXISTS\n\n");
            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                array = (ArrayList<Person>) in.readObject();
                //p2 = (Person) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Employee class not found");
                c.printStackTrace();
            }
        }
        else {
            System.out.println("DOESN'T EXIST\n\n");
            array.add(new Person("person1", "ıvj", "12345", "123"));
            array.add(new Person("person2", "surname", "90876", "38r823"));
        }
        for (Person p1 : array) {
            System.out.println("name: " + p1.getName());
            System.out.println("surname: " + p1.getSurname());
            System.out.println("ID: " + p1.getID());
            System.out.println("phoneNo: " + p1.getPhoneNum() + "\n");
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("tmp/data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(array);
            //out.writeObject(p2);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in tmp/data.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }*/
    }

    public static String timeInterval(LocalDateTime date1, LocalDateTime date2){
        if(date2.compareTo(date1) < 0){
            LocalDateTime temp = date1;
            date1 = date2;
            date2 = temp;
        }

        int day1 = date1.getDayOfYear();
        int day2 = date2.getDayOfYear();
        for(int i = date1.getYear(); i < date2.getYear(); i++){
            day2 += LocalDate.parse(i + "-01-01").lengthOfYear();
        }
        int dayInterval = day2 - day1;

        int hour1 = date1.getHour();
        int hour2 = date2.getHour();
        int hourInterval = hour2 - hour1;

        int min1 = date1.getMinute();
        int min2 = date2.getMinute();
        int minInterval = min2 -min1;

        if(minInterval < 0){
            hourInterval--;
            minInterval += 60;
        }
        if(hourInterval < 0){
            dayInterval--;
            hourInterval += 24;
        }
        return dayInterval + " days " + hourInterval + " hours " + minInterval + " minutes left";
    }
}
