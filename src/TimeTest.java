
import java.util.Scanner;

public class TimeTest {

    public static void run() {

        Scanner input = new Scanner(System.in);
        Time test_time = new Time();
        System.out.print("Enter hours.........");
        int hours = input.nextInt();
        System.out.print("Enter Minutes.......");
        int minutes = input.nextInt();
        
        test_time.set_hours(hours);
        test_time.set_minutes(minutes);
        
        test_time.DisplayTime();
        test_time.DisplayMinutes();

    }
}
