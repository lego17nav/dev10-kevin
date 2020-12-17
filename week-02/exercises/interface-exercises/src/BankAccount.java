public class BankAccount implements MoneyStorage {
    private double balance;
    private String accountNumber;

    public BankAccount(double balance, String description) {
        this.balance = balance;
        this.accountNumber = description;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return accountNumber;
    }

    @Override
    public boolean deposit(double amount) {
        if(amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }


    @Override
    public double withdraw(double amount) {
        double output = 0.00;
        if(balance - amount > -25 && amount > 0) {
            balance -= amount;
            output = amount;
        }
        else {
            balance = balance - amount;
            output = balance;
        }
        return  output;
    }
}
