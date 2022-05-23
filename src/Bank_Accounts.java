public class Bank_Accounts {
    private int BankNumber;
    private String PIN;
    private int Balance;
    private Bank bank;
    private String AccountType;
    private Customer Owner;

    public Bank_Accounts(int bankNumber, String PIN, int balance, Bank bank, String accountType, Customer owner) {
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

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
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
        if(Payment>=0){
            return Payment;
        } else{ return 0;}
    }
}