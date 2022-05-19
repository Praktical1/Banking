public class ISA {
    public static double Max_Annual_Deposit = 20_000;
    public int InterestRate = 2;
    public int balance;

    public static int amountAddedIntoAccount;
    public ISA(int interestRate) {
        InterestRate = interestRate;
    }
    public void setAmountAddedIntoAccount(int amountAddedIntoAccount){
        this.amountAddedIntoAccount = amountAddedIntoAccount;
    }
    public static int getAmountAddedIntoAccount() {
        return amountAddedIntoAccount;
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
