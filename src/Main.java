
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Circle Operations");
            System.out.println("2. Employee Management");
            System.out.println("3. Date Management");
            System.out.println("4. Time Management");
            System.out.println("5. Building Info");
            System.out.println("6. Student Result");
            System.out.println("7. Rectangle Info");
            System.out.println("8. Bank Account");
            System.out.println("9. Student Info System");
            System.out.println("10. Car Rental System");
            System.out.println("11. Employee Management System");
            System.out.println("12. Library Management System");
            System.out.println("13. Hotel Room Booking");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    CircleTest.run();
                    break;
                case 2:
                    EmployeeTest.run();
                    break;
                case 3:
                    DateTest.run();
                    break;
                case 4:
                    TimeTest.run();
                    break;
                case 5:
                    Building house = new Building();
                    Building office = new Building();

                    house.set_area(1400.0);
                    house.set_occupants(145);

                    office.set_area(5040);
                    office.set_occupants(421);

                    System.out.print("House: ");
                    house.areaperperson();
                    System.out.print("Office: ");
                    office.areaperperson();
                    break;
                case 6:
                    Result my_result = new Result();
                    my_result.input();
                    my_result.show();
                    System.out.println("Total marks: " + my_result.total());
                    System.out.println("Average: " + my_result.Avg());
                    break;
                case 7:
                    Rectangle test_Rectangle = new Rectangle();

                    test_Rectangle.set_length();
                    test_Rectangle.set_width();

                    System.out.println("Rectangle Perimeter: " + test_Rectangle.rectangle_Perimeter());
                    System.out.println("Rectangle Area: " + test_Rectangle.retangle_area());
                    break;
                case 8:
                    BankAccount test_account = new BankAccount();
                    String clear_buffer = input.nextLine(); // just for clearing input buffer
                    System.out.print("Enter account holder name: ");
                    String input_name = input.nextLine();
                    test_account.set_acc_holder_name(input_name);
                    System.out.print("Enter account number: ");
                    int input_acc_num = input.nextInt();
                    test_account.set_acc_number(input_acc_num);
                    System.out.print("Enter account balance: ");
                    double input_balance = input.nextDouble();
                    test_account.set_balance(input_balance);

                    System.out.println("1 Deposit into account.");
                    System.out.println("2 Withdraw from account.");
                    System.out.println("3 Calculate intrest account.");
                    System.out.println("4 Print account info.");
                    System.out.println("5 Exit account");

                    System.out.println("Enter your choice: ");
                    int user_choice = input.nextInt();
                    switch (user_choice) {
                        case 1 -> {
                            System.out.print("Enter deposit amount: ");
                            double amu = input.nextDouble();
                            test_account.Deposit(amu);
                        }
                        case 2 -> {
                            System.out.print("Enter withdraw amount: ");
                            double amu = input.nextDouble();
                            test_account.Withdraw(amu);
                        }
                        case 3 -> {
                            System.out.println("Intrest on your account balance is: " + test_account.Calculate_interest());
                        }
                        case 4 -> {
                            test_account.Print_account_details();
                        }
                        default -> {
                        }
                    }
                    break;

                case 9:
                    StudentInfo test_stuInfo = new StudentInfo();
                    System.out.print("Enter student id: ");
                    test_stuInfo.set_student_id(input.nextInt());
                    input.nextLine(); // just for clear buffer
                    System.out.print("Enter student name: ");
                    test_stuInfo.set_name(input.nextLine());
                    System.out.print("Enter student age: ");
                    test_stuInfo.set_age(input.nextInt());
                    System.out.print("Enter student marks: ");
                    test_stuInfo.set_marks(input.nextDouble());
                    System.out.println("Enter 1 for marks updation: ");
                    System.out.print("Enter 0 for exit");
                    int inputt = input.nextInt();
                    switch (inputt) {
                        case 1 -> {
                            System.out.print("Enter updated marks: ");
                            test_stuInfo.Update_marks(input.nextDouble());
                        }
                        default -> {
                        }
                    }
                    test_stuInfo.Display_student_details();
                    break;

                case 10: {
                    CarRental myCar = new CarRental(123, "Honda", 1000.0, true);
                    int choices;

                    do {
                        System.out.println("\n======= Car Rental Menu =======");
                        System.out.println("1. Rent Car");
                        System.out.println("2. Return Car");
                        System.out.println("3. Check Availability");
                        System.out.println("4. Calculate Rental Price");
                        System.out.println("5. Update Rental Price");
                        System.out.println("6. Is Car Available?");
                        System.out.println("7. Display Car Info");
                        System.out.println("8. Set Availability Manually");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choices = input.nextInt();

                        switch (choices) {
                            case 1: {
                                double rent = myCar.RentCar();
                                if (rent > 0) {
                                    System.out.println("Total Rent: " + rent);
                                }
                                break;
                            }
                            case 2: {
                                myCar.ReturnCar();
                                System.out.println("Car returned successfully.");
                                break;
                            }
                            case 3: {
                                CarRental availableCar = myCar.CheckAvailability();
                                if (availableCar != null) {
                                    System.out.println("Car is available for rent.");
                                } else {
                                    System.out.println("Car is currently rented.");
                                }
                                break;
                            }
                            case 4: {
                                System.out.print("Enter number of days to calculate rent: ");
                                int days = input.nextInt();
                                double price = myCar.CalculateRentalPrice(days);
                                if (price > 0) {
                                    System.out.println("Rental Price for " + days + " days: " + price);
                                }
                                break;
                            }
                            case 5: {
                                System.out.print("Enter new rental price per day: ");
                                double newPrice = input.nextDouble();
                                myCar.UpdateRentalPrice(newPrice);
                                System.out.println("Rental price updated.");
                                break;
                            }
                            case 6: {
                                boolean available = myCar.IsCarAvailableForRent();
                                System.out.println("Is car available? " + (available ? "Yes" : "No"));
                                break;
                            }
                            case 7: {
                                myCar.DisplayCarInfo();
                                break;
                            }
                            case 8: {
                                myCar.SetAvailability();
                                break;
                            }
                            case 0:
                                System.out.println("Exiting the program...");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                        }

                    } while (choice != 0);
                    break;
                }
                case 11: {
                    EmployeeMgmt emp = new EmployeeMgmt(123, "Salman", "CEO", 121223.0);
                    int choice1;

                    do {
                        System.out.println("\n======= Employee Management Menu =======");
                        System.out.println("1. Get Annual Salary");
                        System.out.println("2. Update Position");
                        System.out.println("3. Display Employee Details");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choice1 = input.nextInt();
                        input.nextLine();  // consume leftover newline

                        switch (choice1) {
                            case 1: {
                                double annualSalary = emp.GetAnnualSalary();
                                System.out.println("Annual Salary: " + annualSalary);
                                break;
                            }
                            case 2: {
                                System.out.print("Enter new position: ");
                                String newPosition = input.nextLine();
                                emp.UpdatePosition(newPosition);
                                System.out.println("Position updated successfully.");
                                break;
                            }
                            case 3: {
                                emp.DisplayEmployeeDetails();
                                break;
                            }
                            case 0:
                                System.out.println("Exiting Employee Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                        }

                    } while (choice != 0);
                    break;
                }
                case 12: {
                    System.out.print("Enter book ID: ");
                    int id = input.nextInt();
                    input.nextLine();  // consume newline

                    System.out.print("Enter book title: ");
                    String title = input.nextLine();

                    System.out.print("Enter author name: ");
                    String author = input.nextLine();

                    System.out.print("Enter number of copies: ");
                    int copies = input.nextInt();

                    Library book = new Library(id, title, author, copies);

                    int choice2;
                    do {
                        System.out.println("\n======= Library Menu =======");
                        System.out.println("1. Issue Book");
                        System.out.println("2. Return Book");
                        System.out.println("3. Check Book Availability");
                        System.out.println("4. Update Number of Copies");
                        System.out.println("5. Display Book Information");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choice2 = input.nextInt();

                        switch (choice2) {
                            case 1:
                                book.issueBook();
                                break;
                            case 2:
                                book.returnBook();
                                break;
                            case 3:
                                if (book.checkAvailability()) {
                                    System.out.println("Book is available.");
                                } else {
                                    System.out.println("Book is not available.");
                                }
                                break;
                            case 4:
                                System.out.print("Enter new number of copies: ");
                                int newCopies = input.nextInt();
                                book.updateCopies(newCopies);
                                break;
                            case 5:
                                System.out.println("\nBook ID: " + book.get_book_id());
                                System.out.println("Title: " + book.get_title());
                                System.out.println("Author: " + book.get_author());
                                System.out.println("Number of Copies: " + book.get_num_of_copies());
                                break;
                            case 0:
                                System.out.println("Exiting Library System...");
                                break;
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }

                    } while (choice != 0);
                    break;
                }
                case 13: {
                    System.out.print("Enter room number: ");
                    int roomNumber = input.nextInt();
                    input.nextLine(); // consume newline

                    System.out.print("Enter room type (e.g. Single, Double, Suite): ");
                    String roomType = input.nextLine();

                    System.out.print("Is the room available? (true/false): ");
                    boolean availability = input.nextBoolean();

                    System.out.print("Enter price per night: ");
                    double price = input.nextDouble();

                    HotelBooking room = new HotelBooking(roomNumber, roomType, availability, price);

                    int choice3;
                    do {
                        System.out.println("\n======= Hotel Booking System Menu =======");
                        System.out.println("1. Book Room");
                        System.out.println("2. Check Room Availability");
                        System.out.println("3. Calculate Total Price");
                        System.out.println("4. Display Room Details");
                        System.out.println("5. Update Room Price");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choice3 = input.nextInt();

                        switch (choice3) {
                            case 1:
                                room.bookRoom();
                                break;
                            case 2:
                                if (room.checkAvailability()) {
                                    System.out.println("Room is available.");
                                } else {
                                    System.out.println("Room is not available.");
                                }
                                break;
                            case 3:
                                System.out.print("Enter number of nights: ");
                                int nights = input.nextInt();
                                double total = room.calculateTotalPrice(nights);
                                System.out.println("Total price for " + nights + " night(s): " + total);
                                break;
                            case 4:
                                room.displayRoomDetails();
                                break;
                            case 5:
                                System.out.print("Enter new price per night: ");
                                double newPrice = input.nextDouble();
                                room.set_price(newPrice);
                                System.out.println("Room price updated.");
                                break;
                            case 0:
                                System.out.println("Exiting Hotel Booking System...");
                                break;
                            default:
                                System.out.println("Invalid choice! Try again.");
                        }

                    } while (choice != 0);
                    break;
                }
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);
    }
}
