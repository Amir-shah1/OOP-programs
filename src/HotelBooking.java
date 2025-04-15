/*Problem: Hotel Room Booking System
Attributes: Room number, room type, availability, price.
Constructor: Initializes room with number, type, and price.
Setters/Getter: For room number, type, availability, and price.
Member Functions: Book room, Check availability, Calculate total price, Display room details.
*/
public class HotelBooking {
    private int room_number;
    private String room_type;
    private boolean availability;
    private double price;
    
    
    public HotelBooking(int room_number, String room_type, boolean availabiliry, double price){
        this.room_number = room_number;
        this.room_type = room_type;
        this.availability = availabiliry;
        this.price = price;
    }
    
    // Setters/Getter: For room number, type, availability, and price.
    public void set_room_number(int room_number){
        this.room_number = room_number;
    }
    public void set_room_type(String room_type){
        this.room_type = room_type;
    }
    public void set_availability(boolean availability){
        this.availability = availability;
    }
    public void set_price(double price){
        this.price = price;
    }
    
    public int get_room_number(){
        return this.room_number;
    }
    public String get_room_type(){
        return this.room_type;
    }
    public boolean get_availability(){
        return this.availability;
    }
    public double get_price(){
        return this.price;
    }
    
    public boolean bookRoom() {
        if (this.availability) {
            this.availability = false;
            System.out.println("Room booked successfully.");
            return true;
        } else {
            System.out.println("Room is not available.");
            return false;
        }
    }

    public boolean checkAvailability() {
        return this.availability;
    }

    public double calculateTotalPrice(int nights) {
        return this.price * nights;
    }

    public void displayRoomDetails() {
        System.out.println("Room Number: " + this.room_number);
        System.out.println("Room Type: " + this.room_type);
        System.out.println("Price per Night: $" + this.price);
        System.out.println("Availability: " + (this.availability ? "Available" : "Not Available"));
    }
    
}

