/*Problem: Employee Management System
Attributes: Employee ID, name, position, salary.
Constructor: Initializes employee with ID, name, and position.
Setters/Getter: For employee ID, name, position, and salary.
Member Functions: Calculate annual salary, Update position, Display employee details.
*/
public class EmployeeMgmt {
    private int employee_id;
    private String name;
    private String position;
    private double salary;
    
    public EmployeeMgmt(int employee_id, String name, String position, double salary){
        this.employee_id = employee_id;
        this.name = name;
        this.position = position;
        set_salary(salary);
        
    }
    
    // Setters/Getter: For employee ID, name, position, and salary.
    
    public void  set_employee_id(int employee_id){
        this.employee_id = employee_id;
    }
    public void set_name(String name){
        this.name = name;
    }
    public void set_position(String position){
        this.position = position;
    }
    public void set_salary(double salary){
        if(salary > 0){
            this.salary = salary;
        }else{
            System.out.println("Invalid input! for salary");
        }
    }
    
    public int get_employee_id(){
        return  this.employee_id;
    }
    public String get_name(){
        return  this.name;
    }
    public String get_position(){
        return  this.position;
    }
    public double get_salary(){
        return this.salary;
    }
    
    // Member Functions: Calculate annual salary, Update position, Display employee details.
    
    public double GetAnnualSalary(){
        return this.salary * 12;
    }
    
    public void UpdatePosition(String new_position){
        this.set_position(new_position);
    }
    
    public void DisplayEmployeeDetails(){
        System.out.println("Empoyee id: " + this.employee_id);
        System.out.println("Empoyee name: " + this.name);
        System.out.println("Empoyee position: " + this.position);
        System.out.println("Empoyee salary: " + this.salary);
    }
    
}
