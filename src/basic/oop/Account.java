package basic.oop;

public class Account {
    int balance;

    public void deposit(int amount) {
        balance = balance + amount;
    }
    public void withdraw(int amount) {
        if(balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient Balance");
        }
    }
    public void printBalance() {
        System.out.println("Balance is: " + balance);
    }
}
