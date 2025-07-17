/*Problem: Library Book Management
Attributes: Book ID, title, author, number of copies.
Constructor: Initializes book with ID, title, and author.
Setters/Getter: For book ID, title, author, and number of copies.
Member Functions: Issue book, Return book, Check availability, Update copies.
 */
public class Library {

    private int book_id;
    private String title;
    private String author;
    private int number_of_copies;

    public Library(int book_id, String title, String author, int copies) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.number_of_copies = copies;
    }

    // Setters/Getter: For book ID, title, author, and number of copies.
    public void set_book_id(int book_id) {
        this.book_id = book_id;
    }

    public void set_title(String title) {
        this.title = title;
    }

    public void set_author(String author) {
        this.author = author;
    }

    public void set_number_of_copies(int copies) {
        if (copies >= 0) {
            this.number_of_copies = copies;
        } else {
            System.err.println("Invalid input! of book copies");
        }
    }

    //Setters/Getter: For book ID, title, author, and number of copies.
    public int get_book_id() {
        return this.book_id;
    }

    public String get_title() {
        return this.title;
    }

    public String get_author() {
        return this.author;
    }

    public int get_num_of_copies() {
        return this.number_of_copies;
    }

    // Member Functions: Issue book, Return book, Check availability, Update copies.
    public boolean issueBook() {
        if (this.number_of_copies > 0) {
            this.number_of_copies--;
            System.out.println("Book issued successfully.");
            return true;
        } else {
            System.out.println("Book not available.");
            return false;
        }
    }

    public void returnBook() {
        this.number_of_copies++;
        System.out.println("Book returned successfully.");
    }

    public boolean checkAvailability() {
        return this.number_of_copies > 0;
    }

    public void updateCopies(int newCopies) {
        this.number_of_copies = newCopies;
        System.out.println("Number of copies updated.");
    }

}
