public abstract class Bank_Accounts {
    private final int BankNumber;
    private String PIN;
    private int Balance;
    private int BankIndex;
    private String AccountType;
    private int OwnerIndex;

    public Bank_Accounts(int bankNumber, String PIN, int balance, int bankindex, String accountType, int customerindex) {
        BankNumber = bankNumber;
        this.PIN = PIN;
        BankIndex = bankindex;
        Balance = balance;
        AccountType = accountType;
        OwnerIndex = customerindex;
    }

    public int getOwner() {
        return OwnerIndex;
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

    public int getBank() {
        return BankIndex;
    }

    public void setBank(int bank) {
        BankIndex = bank;
    }
    public Bank_Accounts getAccount(){
        return this;
    }
    public int VerifyPayment(int Payment){
        return Math.max(Payment, 0);
    }
}