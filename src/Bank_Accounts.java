public class Bank_Accounts {
    private int BankNumber;
    private int PIN;
    private float Balance;
    private Bank bank;
    private String AccountType;
    private Customer Owner;

    public Bank_Accounts(int bankNumber, int PIN, float balance, Bank bank, String accountType, Customer owner) {
        BankNumber = bankNumber;
        this.PIN = PIN;
        this.bank = bank;
        Balance = balance;
        AccountType = accountType;
        Owner = owner;
    }

    public Customer getOwner() {
        return Owner;
    }

    public void setOwner(Customer owner) {
        Owner = owner;
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
    public Bank_Accounts getAccount(){
        return this;
    }
}
