public class Vault implements MoneyStorage {

    private double balance;
    private String description;

    public Vault(double startingBalance, String description) {
        this.balance = startingBalance;
        this.description = description;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0.0) {
            balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public double withdraw(double amount) {
        double output = 0;
        if (amount <= 0.0) {
            return 0.0;
        }

        if (amount > balance) {
            output = balance;
            balance -= balance;
        }
        else {
            output = amount;
            balance -= amount;
        }
        return output;
    }
}
