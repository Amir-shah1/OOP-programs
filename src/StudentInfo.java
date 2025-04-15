
public class StudentInfo {
    private int student_id;
    private String name;
    private int age;
    private double marks;
    
    public StudentInfo(){
        this.age = 0;
        this.name = null;
        this.student_id = 0;
        this.marks = 0;
    }
    
    public void set_student_id(int id){
        this.student_id = id;
    }
    public void set_name(String name){
        this.name = name;
    }
    public void set_age(int age){
        if(age > 0){
            this.age = age;
        }else{
            System.out.println("Invalid input!");
        }
    }
    public void set_marks(double marks){
        if(marks >= 0 && marks <= 100){
            this.marks = marks;
        }else{
            System.out.println("Invalid input!");
        }
    }
    
    public int get_student_id(){
        return  this.student_id;
    }
    public String get_name(){
        return  this.name;
    }
    public double get_marks(){
        return this.marks;
    }
    public int get_age(){
        return  this.age;
    }
    
    public char Calculate_grade(){
        if(this.marks > 90 && this.marks <= 100){
            return 'A';
        }else if(this.marks > 80){
            return  'B';
        }else if(this.marks > 70){
            return 'C';
        }else if (this.marks > 60){
            return 'D';
        }else if(this.marks > 50){
            return  'E';
        }else{
            return 'F';
        }
    }
    
    public void Update_marks(double up_marks){
        this.set_marks(up_marks);
    }
    
    public void Display_student_details(){
        System.out.println("Student id: " + this.student_id);
        System.out.println("Student name: " + this.name);
        System.out.println("Student age: " + this.age);
        System.out.println("Student marks: " + this.marks);
        System.out.println("Student grade: " + this.Calculate_grade());
    }
}
