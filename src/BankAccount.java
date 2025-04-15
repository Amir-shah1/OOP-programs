
public class BankAccount {

    private int account_number;
    private String acc_holder_name;
    private double balance;

    public BankAccount() {
        this.acc_holder_name = null;
        this.account_number = 0;
        this.balance = 0.0;

    }

    public void set_acc_number(int acc_num) {
        this.account_number = acc_num;
    }

    public void set_acc_holder_name(String acc_hol_name) {
        this.acc_holder_name = acc_hol_name;
    }

    public void set_balance(double acc_bal) {
        this.balance = acc_bal;
    }

    public int get_acc_number() {
        return this.account_number;
    }

    public String get_acc_holder_name() {
        return this.acc_holder_name;
    }

    public double get_balance() {
        return this.balance;
    }

    public void Deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            System.out.println("Invalid input!");
        }
    }

    public void Withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance = this.balance - amount;
        } else {
            System.out.println("Acount balance is: " + this.balance);
            System.out.println("Your amount is higher than Account Balance");
        }
    }

    public double Calculate_interest() {
        // for example intrest rate is 2.5%
        return (this.balance / 100) * 0.25;
    }

    public void Print_account_details() {
        System.out.println("Account holder: " + this.acc_holder_name);
        System.out.println("Account number: " + this.account_number);
        System.out.println("Account balance: " + this.balance);
    }
}
