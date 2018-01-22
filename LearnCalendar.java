import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/*
 * This file is simply for learning and practicing with Java date and calendar classes.
 *
 * https://www.ntu.edu.sg/home/ehchua/programming/java/DateTimeCalendar.html
 * Date is sufficient if you need only a current timestamp in your application,
 * and you do not need to operate on dates, e.g., one-week later.
 * You can further use SimpleDateFormat to control the date/time display format.
 */
public class LearnCalendar {

    public LearnCalendar() {
    }

    public void testDateAndTimers() {
        // Measuring elapsed time
        long nanoStartTime = System.nanoTime();
        long startTime = System.currentTimeMillis();

        Date now = new Date();
        System.out.println("toString(): " + now);  // dow mon dd hh:mm:ss zzz yyyy

        // SimpleDateFormat can be used to control the date/time display format:
        //   E (day of week): 3E or fewer (in text xxx), >3E (in full text)
        //   M (month): M (in number), MM (in number with leading zero)
        //              3M: (in text xxx), >3M: (in full text full)
        //   h (hour): h, hh (with leading zero)
        //   m (minute)
        //   s (second)
        //   a (AM/PM)
        //   H (hour in 0 to 23)
        //   z (time zone)
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z");
        System.out.println("Format 1:   " + dateFormatter.format(now));

        dateFormatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("Format 2:   " + dateFormatter.format(now));

        dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        System.out.println("Format 3:   " + dateFormatter.format(now));


        // The code being measured
        long estimatedNanoTime = System.nanoTime() - nanoStartTime;
        System.out.println("estimated time to run tests: " + estimatedNanoTime + " nanosec");
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("estimated time to run tests: " + estimatedTime + "ms");
    }

    public void pullApartCalendarValues() {
        Calendar cal = Calendar.getInstance();
        // You cannot use Date class to extract individual Date fields
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);      // 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        System.out.printf("\n\nNow (pulled from calendar) is %4d/%02d/%02d %02d:%02d:%02d\n\n\n",  // Pad with zero
            year, month+1, day, hour, minute, second);
    }

    public void testCalendar() {
        Calendar cal = Calendar.getInstance();

        System.out.println("Calendar's toString() is: " + cal + "\n");
        System.out.println("Time zone is: " + cal.getTimeZone() + "\n");

        // An Easier way to print the timestamp by getting a Date instance
        Date date = cal.getTime();
        System.out.println("Current date and time in Date's toString() is: " + date + "\n");

        // Print Calendar's field
        System.out.println("Year: " + cal.get(Calendar.YEAR));
        System.out.println("Month: " + cal.get(Calendar.MONTH));
        System.out.println("Day of Month: " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("Day of Week: " + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("Day of Year: " + cal.get(Calendar.DAY_OF_YEAR));
        System.out.println("Week of Year: " + cal.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Week of Month: " + cal.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Day of the Week in Month: " + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("Hour: " + cal.get(Calendar.HOUR));
        System.out.println("AM PM: " + cal.get(Calendar.AM_PM));
        System.out.println("Hour of the Day: " + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("Minute: " + cal.get(Calendar.MINUTE));
        System.out.println("Second: " + cal.get(Calendar.SECOND));
        System.out.println();

        // Manipulating Dates
        Calendar calTemp;
        calTemp = (Calendar) cal.clone();
        calTemp.add(Calendar.DAY_OF_YEAR, -365);
        System.out.println("365 days ago, it was: " + calTemp.getTime());

        calTemp = (Calendar) cal.clone();
        calTemp.add(Calendar.HOUR_OF_DAY, 11);
        System.out.println("After 11 hours, it will be: " + calTemp.getTime());

        // Roll
        calTemp = (Calendar) cal.clone();
        calTemp.roll(Calendar.HOUR_OF_DAY, 11);
        System.out.println("Roll 11 hours, it will be: " + calTemp.getTime());
        System.out.println();
    }

    public static void main(String[] args) {
        LearnCalendar lc = new LearnCalendar();
        lc.testDateAndTimers();
        lc.pullApartCalendarValues();
        lc.testCalendar();
    }
}