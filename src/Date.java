
public class Date {
    private int day;
    private int month;
    private int year;
    
    public Date(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }
    public Date(){
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }
    
    public void set_day(int input_day){
        this.day = input_day;
    }
    public void set_month(int input_month){
        this.month = input_month;
    }
    public void set_year(int input_year){
        this.year = input_year;
    }
    
    public int get_day(){
        return this.day;
    }
    public int get_month(){
        return  this.month;
    }
    public int get_year(){
        return this.year;
    }
    
    public void DisplayDate(){
        System.out.printf("%02d/%02d/%02d\n", this.month, this.day, this.year);
    }
    
}
