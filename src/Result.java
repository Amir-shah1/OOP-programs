
import java.util.Scanner;

public class Result {

    private int roll_no;
    private String name;
    double[] marks = new double[3];

    public void input() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        this.name = input.nextLine();
        System.out.print("Enter your roll no: ");
        this.roll_no = input.nextInt();

        System.out.print("Enter marks of subject1: ");
        this.marks[0] = input.nextDouble();
        System.out.print("Enter marks of subject2: ");
        this.marks[1] = input.nextDouble();
        System.out.print("Enter marks of subject3: ");
        this.marks[2] = input.nextDouble();
    }

    public void show() {
        System.out.printf("Name: %s\nRoll No: %d\n", this.name, this.roll_no);
        System.out.println("Marks of subject1: " + this.marks[0]);
        System.out.println("Marks of subject2: " + this.marks[1]);
        System.out.println("Marks of subject3: " + this.marks[2]);
    }

    public double total() {
        return this.marks[0] + this.marks[1] + this.marks[2];
    }

    public double Avg() {
        return (this.total() / 3);
    }

}
