
import java.util.Scanner;

public class DateTest {

    public static void run() {

        Scanner input = new Scanner(System.in);
        Date test_date = new Date();

        System.out.print("Enter Day...........");
        int day = input.nextInt();
        System.out.print("Enter Month.........");
        int month = input.nextInt();
        System.out.print("Enter Year..........");
        int year = input.nextInt();
        
        test_date.set_day(day);
        test_date.set_month(month);
        test_date.set_year(year);
        
        test_date.DisplayDate();

    }
}
