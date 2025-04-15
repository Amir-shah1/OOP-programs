
import java.util.Scanner;

public class Building {

    public int floors;
    public double area;
    public int occupants;

    public void areaperperson() {
        System.out.printf("AreaPerPerson is: %.2f\n", this.area / this.occupants);
    }

    public void set_floors(int input_floors) {
        this.floors = input_floors;
    }

    public void set_area(double input_area) {
        this.area = input_area;
    }

    public void set_occupants(int input_occupants) {
        this.occupants = input_occupants;
    }

    public void input_info() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of floor....");
        this.set_floors(input.nextInt());
        System.out.print("Enter the Area...............");
        this.set_area(input.nextDouble());
        System.out.print("Enter number of Occupants....");
        this.set_occupants(input.nextInt());

    }
}
