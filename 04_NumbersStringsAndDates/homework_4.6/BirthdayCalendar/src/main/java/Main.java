
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 30;
        int month = 1;
        int year = 2021;

        System.out.print(collectBirthdays(year, month, day));
    }
    public static String collectBirthdays(int year, int month, int day) {

        StringBuilder text = new StringBuilder();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - E",Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1 , day, 0,0,0);

        int i = 0;

        while (calendar.getTime().getTime() < System.currentTimeMillis()){
                text.append(i++).append(" - ").append(dateFormat.format(calendar.getTime())).append(System.lineSeparator());
                calendar.add(Calendar.YEAR, 1);
        }
        //TODO реализуйте метод для построения строки в следующем виде
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue

        return text.toString();
    }
}