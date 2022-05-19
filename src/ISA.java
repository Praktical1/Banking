public class ISA {
    public static double Max_Annual_Deposit = 20_000;
    public double InterestRate = 0.02;
    public double balance;

    public double amountAddedIntoAccount;
    public ISA(double interestRate) {
        InterestRate = interestRate;
    }

    public void setAmountAddedIntoAccount(int amountAddedIntoAccount){
        this.amountAddedIntoAccount = amountAddedIntoAccount;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    //The following methods need to be looked at and revised
    public void transfer(double transferAmount){
        this.balance = balance - transferAmount;
    }

    public void withdraw(double withDrawAmount){
        this.balance = balance - withDrawAmount;
    }

    public void pay(double paymentAmount){
        this.balance = balance - paymentAmount;
    }

    public void deposit(double depositAmount){
        this.balance = balance + depositAmount;
    }

    public void addInterest(double interestAmount){
        this.InterestRate += interestAmount;
    }


}
