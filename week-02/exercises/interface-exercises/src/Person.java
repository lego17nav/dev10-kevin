public class Person {

    private final String firstName;
    private final String lastName;
    private Wallet walletInfo;

    public Person(String firstName, String lastName) {
        // Wallet constructor

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public Wallet getWalletInfo() {
        return walletInfo;
    }
    public void setWalletInfo(Wallet walletInfo) {
        this.walletInfo = walletInfo;
    }
    public String getDescription() {
        String description = walletInfo.getDescription();
        return description;
    }

    public double getBalance() {
        double balance = walletInfo.getBalance();
        return balance;
    }

}
