public class ISA extends Bank_Accounts {
    public static int MaxAnnualDeposit = 2_000_000;
    private int InterestRate = 2;
    public int CurrentAnnualDeposit;

    public ISA(int bankNumber, int PIN, int balance, Bank bank, String accountType, Customer owner, int currentAnnualDeposit) {
        super(bankNumber, PIN, balance, bank, accountType, owner);
        CurrentAnnualDeposit = currentAnnualDeposit;

    }

    public static int getMaxAnnualDeposit() {
        return MaxAnnualDeposit;
    }



    public static void setMaxAnnualDeposit(int maxAnnualDeposit) {
        MaxAnnualDeposit = maxAnnualDeposit;
    }

    public void setCurrentAnnualDeposit(int amountAddedIntoAccount){
        this.CurrentAnnualDeposit = amountAddedIntoAccount;
    }
    public int getCurrentAnnualDeposit() {
        return CurrentAnnualDeposit;
    }

    //The following methods need to be looked at and revised
    public void transfer(int transferAmount){
        setBalance(getBalance() - transferAmount);
        transferAmount = VerifyPayment(transferAmount);
        if(transferAmount != 0 & (getBalance() - (int)(1+transferAmount/0.75))>=0){
            setBalance(getBalance() - (int)(1+transferAmount/0.75));
        }
        else {
            System.out.println("Insufficient funds");
        }
    }

    public void withdraw(int withDrawAmount){
        withDrawAmount = VerifyPayment(withDrawAmount);
        if(withDrawAmount != 0 & (getBalance() - (int)(1+withDrawAmount/0.75))>=0){
            setBalance(getBalance() - (int)(1+withDrawAmount/0.75));
        }
        else {
            System.out.println("Insufficient funds");
        }

    }

    public void pay(int paymentAmount){
        paymentAmount = VerifyPayment(paymentAmount);
        if(paymentAmount != 0 & (getBalance() - paymentAmount>=0)){
            setBalance(getBalance() - paymentAmount);
        }
        else{
            System.out.println("Insufficient funds");
        }
    }

    public void deposit(int depositAmount){
        setBalance(getBalance() + (int)(depositAmount*1.25));

    }

    public void addInterest(int interestAmount){
        this.InterestRate += interestAmount;
    }
}