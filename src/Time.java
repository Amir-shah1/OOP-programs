
public class Time {
    private int hours;
    private  int minutes;
    
    public Time(int h, int m){
        this.set_hours(h);
        this.set_minutes(m);
    }
    
    public Time(){
        this.hours = 0;
        this.minutes = 0;
    }
    
    public void DisplayTime(){
        System.out.printf("Time: %02d:%02d\n", this.hours, this.minutes);
    }
    
    public void DisplayMinutes(){
        int total_minutes = this.minutes + (this.hours * 60);
        System.out.printf("Total minutes: %d\n", total_minutes);
    }
    
    public void set_hours(int h){
        if(h <= 23){
            this.hours = h;
        }else{
            System.out.println("Invalid input hours");
        }
    }
    
    public void set_minutes(int min){
        if (min >= 0 && min <= 59){
            this.minutes = min;
        }else{
            System.out.println("Invalid input minutes");
        }
    }
}
