// In this Class the functionaity of calculating Area and Circumference exit;

public class Circle {
    private double radius;
    
    public void set_radius(double input_radius){
        this.radius = input_radius;
    }
    
    public double get_radius(){
        return this.radius;
    }
    
    public double getArea(){
        return  3.14 * this.radius * this.radius;
    }
    
    public double getCircumference(){
        return  2 * 3.14 * this.radius;
    }
    
    
    
    
}
