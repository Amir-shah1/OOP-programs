
import java.util.Scanner;

public class CarRental {
    private int car_id;
    private String model;
    private double rental_price_per_day;
    private boolean availability;
    
    public CarRental(int car_id, String model, double rent_per_day, boolean availability){
        this.car_id = car_id;
        this.model = model;
        this.rental_price_per_day = rent_per_day;
        this.availability = availability;
    }
    
    public void set_car_id(int car_id){
        this.car_id = car_id;
    }
    public void set_model(String model){
        this.model = model;
    }
    public void set_rent_per_day(double rent_per_day){
        this.rental_price_per_day = rent_per_day;
    }
    public void set_availability(boolean availability){
        this.availability = availability;
    }
    
    public int get_car_id(){
        return this.car_id;
    }
    public String get_model(){
        return this.model;
    }
    public double get_rent_per_day(){
        return this.rental_price_per_day;
    }
    public boolean get_availability(){
        return  this.availability;
    }
    
    public double RentCar(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of days for rent: ");
        int days = input.nextInt();
        if(days > 0){
            double rent = days * this.rental_price_per_day;
            this.availability = false;
            return rent;
        }else{
            System.out.println("Invalid input!");
        }
        return 0;
    }
    // ReturnCar(): Marks the car as available again and clears the rental information.
    
    public void ReturnCar(){
        this.availability = true;
    }
    
    // CheckAvailability(): Returns if the car is available for rental.

    public CarRental CheckAvailability(){
        if(this.availability == true){
            return this;
        }else{
            return null;
        }
    }
    
    // CalculateRentalPrice(): Computes the total rental price based on the number of days rented.
    
    public double CalculateRentalPrice(int days){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter for how many day you want to rent car: ");
        int rent_days = input.nextInt();
        if(rent_days > 0){
            return this.rental_price_per_day * rent_days;
        }else{
            System.out.println("Invalid input!");
            return 0;
        } 
    }
    
    // UpdateRentalPrice(): Allows the rental price per day to be updated.
    public void UpdateRentalPrice(double new_price){
        set_rent_per_day(new_price);
    }
    
    // IsCarAvailableForRent(): Checks if the car is available and returns a boolean value.
    public boolean IsCarAvailableForRent(){
        if(this.availability == true){
            return true;
        }else{
            return false;
        }
    }
    // DisplayCarInfo(): Displays information about the car such as ID, model, and availability.
    public void DisplayCarInfo(){
        System.out.println("Car id: " + this.car_id);
        System.out.println("Car model: " + this.model);
        System.out.println("Car Availiable: " + this.availability);

    }
    
    // SetAvailability(): Manually sets the availability of the car to either true or false.
    public void SetAvailability(){
        Scanner input = new Scanner(System.in);
        System.out.println("Manual Availability");
        System.out.print("Enter 1 for available false 0 available: ");
        int choice = input.nextInt();
        if(choice == 1){
            this.availability = true;
        }else if(choice == 0){
            this.availability = false;
        }else{
            System.out.println("Invalid input!");
        }
    }
}
