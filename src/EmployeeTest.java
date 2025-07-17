
import java.util.Scanner;

public class EmployeeTest {

    public static void run() {
        Scanner input = new Scanner(System.in);
        Employee new_Employee = new Employee();
        
        System.out.print("Enter salary for employee: ");
        double salary = input.nextDouble();
        
        new_Employee.set_monthly_salary(salary);
       
        System.out.printf("The yearly salry of employee is: %.2f\n", new_Employee.get_monthly_salary()*12);
        double raise10 = ((new_Employee.get_monthly_salary()*12)* 0.1) + (new_Employee.get_monthly_salary()* 12);
        System.out.printf("After 10%% raise in yearly salary is: %.2f\n", raise10);
        
        
        

    }

}
