
public class Employee {
    private String first_name;
    private String last_name;
    private double monthly_salary;
    
    // using constructor
    public Employee(){
        this.first_name = "   ";
        this.last_name = "   ";
        this.monthly_salary = 0.0;
    }
    
    // setter for setting the value of the instance;
    
    public void set_frist_name(String f_name){
        this.first_name = f_name;
    }
    public void set_last_name(String l_name){
        this.last_name = l_name;
    }
    public void set_monthly_salary(double m_salary){
        if(m_salary < 0){ // add simple condtion if salary is negative
            this.monthly_salary = 0.0;
        }else{
            this.monthly_salary = m_salary; 
        }
    }
    // Getter for getting the values of instances;
    
    public String get_first_name(){
        return  this.first_name;
    }
    public String get_last_name(){
        return this.last_name;
    }
    public double get_monthly_salary(){
        return  this.monthly_salary;
    }
}
