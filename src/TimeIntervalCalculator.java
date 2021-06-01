import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeIntervalCalculator {
    public static String calculate(LocalDateTime date1, LocalDateTime date2){
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
