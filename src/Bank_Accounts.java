public class Bank_Accounts {
    private int BankNumber;
    private int PIN;
    private float Balance;
    private Bank bank;
    private String AccountType;

    public Bank_Accounts(int bankNumber, int PIN, float balance, Bank bank, String accountType) {
        BankNumber = bankNumber;
        this.PIN = PIN;
        this.bank = bank;
        Balance = balance;
        AccountType = accountType;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public float getBalance() {
        return Balance;
    }

    public void setBalance(float balance) {
        Balance = balance;
    }

    public int getBankNumber() {
        return BankNumber;
    }

    public void setBankNumber(int bankNumber) {
        BankNumber = bankNumber;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
