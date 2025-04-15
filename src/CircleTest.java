
import java.util.Scanner;

public class CircleTest {

    public static void run() { // static will help run directly without making object
        Scanner input = new Scanner(System.in); // for taking input
        Circle test_circle = new Circle();

        while (true) {
            System.out.println("1. Finding Area");
            System.out.println("2. Finding Circumfirance");
            System.out.println("0. Exit the program");
            System.out.print("Enter your Choice: ");
            int user_input = input.nextInt();

            if (user_input == 1) {
                System.out.print("Enter radius: ");
                double input_radius = input.nextDouble();
                test_circle.set_radius(input_radius);
                System.out.printf("Area = %.2f\n", test_circle.getArea());

            } else if (user_input == 2) {
                double input_radius =  input.nextDouble();
                test_circle.set_radius(input_radius);
                System.out.printf("Circumfirance = %.2f\n", test_circle.getCircumference());

            } else if (user_input == 0) {
                break;
                
            } else {
                System.out.println("Invalid input");
            }
        }
    }

}
