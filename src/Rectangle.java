
import java.util.Scanner;

public class Rectangle {

    private double length;
    private double width;

    public Rectangle() {
        this.length = 1;
        this.width = 1;
    }

    public double rectangle_Perimeter() {
        return 2 * (this.length + this.width);
    }

    public double retangle_area() {
        return this.length * this.width;
    }

    public void set_length() {
        Scanner input = new Scanner(System.in);
        double user_input;

        do {
            System.out.print("Enter length: ");
            user_input = input.nextDouble();

            if (user_input > 0.0 && user_input < 20.0) {
                this.length = user_input;
            } else {
                System.out.println("Invaid input!");
            }
        } while (user_input < 0.0 || user_input > 20.0);
    }

    public void set_width() {
        Scanner input = new Scanner(System.in);
        double user_input;

        do {
            System.out.print("Enter width: ");
            user_input = input.nextDouble();

            if (user_input > 0.0 && user_input < 20.0) {
                this.width = user_input;
            } else {
                System.out.println("Invaid input!");
            }
        } while (user_input < 0.0 || user_input > 20.0);
    }
    
    public double get_length(){
        return this.length;
    }
    public double get_width(){
        return this.width;
    }

}
