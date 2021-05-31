import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Driver {
    public static void main(String[] args) throws Exception {
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy   HH:mm");
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
        System.out.println("\u001B[31m" + "cancelled" + "\u001B[0m");*/
        TreeMap<Integer, String> map = new TreeMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            int rand = random.nextInt(5000);
            map.put(rand, "a");
            hashMap.put(rand, "a");
        }
        System.out.println(map);
        System.out.println(map.descendingMap());
        System.out.println(hashMap);
    }

    public static ArrayList<Object> deserialize() throws IOException, ClassNotFoundException {
        //File file = new File();
        FileInputStream fileIn = new FileInputStream("tmp/data.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<Object> items = new ArrayList<>();
        try {
            while (true){
                items.add(in.readObject());
            }
        }
        catch (OptionalDataException e){
            if(!e.eof){
                throw e;
            }
        }
        catch (EOFException e){
            in.close();
            fileIn.close();
        }
        System.out.println("okunan dosya: " + items);
        return items;
    }

    /*@SuppressWarnings("unchecked")
    public static <E extends Serializable> Collection<E> deserialize(Collection<E> collection) throws IOException, ClassNotFoundException {
        File file = new File("tmp/data.ser");
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        collection = (Collection<E>) in.readObject();
        in.close();
        fileIn.close();
        System.out.println("okunan dosya: " + collection);
        return collection;
    }*/

    public static <E extends Serializable> void serialize(E item) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("tmp/data.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(item);
        out.close();
        fileOut.close();
    }

    /*public static <E extends Serializable> void serialize(Collection<E> collection) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("tmp/data.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(collection);
        out.close();
        fileOut.close();
    }*/

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