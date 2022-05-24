public abstract class Bank_Accounts {
    private final int BankNumber;
    private int PIN;
    private int Balance;
    private Bank bank;
    private String AccountType;
    private Customer Owner;

    public Bank_Accounts(int bankNumber, int PIN, int balance, Bank bank, String accountType, Customer owner) {
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

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public int getBankNumber() {
        return BankNumber;
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
    public int VerifyPayment(int Payment){
        return Math.max(Payment, 0);
    }
}