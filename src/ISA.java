public class ISA {
    public static int MaxAnnualDeposit = 2_000_000;
    public int InterestRate = 2;
    public int balance;

    private Bank_Accounts Account;

    public int CurrentAnnualDeposit;
    public ISA(Bank_Accounts Account, int amountAddedIntoAccount) {
        this.Account = Account;
        CurrentAnnualDeposit = amountAddedIntoAccount;

    }

    public Bank_Accounts getAccount() {
        return Account;
    }

    public void setCurrentAnnualDeposit(int amountAddedIntoAccount){
        this.CurrentAnnualDeposit = amountAddedIntoAccount;
    }
    public int getCurrentAnnualDeposit() {
        return CurrentAnnualDeposit;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }

    //The following methods need to be looked at and revised
    public void transfer(int transferAmount){
        this.balance = balance - transferAmount;
    }

    public void withdraw(int withDrawAmount){
        this.balance = balance - withDrawAmount;
    }

    public void pay(int paymentAmount){
        this.balance = balance - paymentAmount;
    }

    public void deposit(int depositAmount){
        this.balance = balance + depositAmount;
    }

    public void addInterest(int interestAmount){
        this.InterestRate += interestAmount;
    }


}