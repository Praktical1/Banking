public class ISA extends Bank_Accounts {
    public static int MaxAnnualDeposit = 2_000_000;
    private int InterestRate = 2;
    public int CurrentAnnualDeposit;

    public ISA(int bankNumber, String PIN, int balance, int bankindex, String accountType, int index, int currentAnnualDeposit) {
        super(bankNumber, PIN, balance, bankindex, accountType, index);
        CurrentAnnualDeposit = currentAnnualDeposit;

    }

    public static int getMaxAnnualDeposit() {
        return MaxAnnualDeposit;
    }


    public static void setMaxAnnualDeposit(int maxAnnualDeposit) {
        MaxAnnualDeposit = maxAnnualDeposit;
    }

    public void setCurrentAnnualDeposit(int amountAddedIntoAccount) {
        this.CurrentAnnualDeposit = amountAddedIntoAccount;
    }

    public int getCurrentAnnualDeposit() {
        return CurrentAnnualDeposit;
    }

    //The following methods need to be looked at and revised
    public void transfer(int transferAmount, Bank_Accounts recipientAccount) {

        if (getOwner()==recipientAccount.getOwner()) {
            transferAmount = VerifyPayment(transferAmount);
            if (transferAmount != 0 & (getBalance() - (int) (1 + transferAmount / 0.75)) >= 0) {
                if (recipientAccount.getAccountType().equals("ISA")) {
                    ISA Recipient = Main_Program.FindISAAccount(recipientAccount);
                    if (Recipient.getCurrentAnnualDeposit() + transferAmount <= MaxAnnualDeposit) {
                        setBalance(getBalance() - (int) (1 + transferAmount / 0.75));
                        recipientAccount.setBalance(recipientAccount.getBalance() + transferAmount);
                        Recipient.setCurrentAnnualDeposit(Recipient.getCurrentAnnualDeposit() + transferAmount);
                    } else {
                        System.out.println("Max annual deposit reached");
                    }
                } else {
                    setBalance(getBalance() - (int) (1 + transferAmount / 0.75));
                    recipientAccount.setBalance(recipientAccount.getBalance() + transferAmount);
                }
            } else {
                System.out.println("Insufficient funds");
            }
        } else {
            System.out.println("Account owner needs to be the same!");
        }
    }

    public void withdraw(int withDrawAmount) {
        withDrawAmount = VerifyPayment(withDrawAmount);
        if (withDrawAmount != 0 & (getBalance() - (int) (1 + withDrawAmount / 0.75)) >= 0) {
            setBalance(getBalance() - (int) (1 + withDrawAmount / 0.75));
        } else {
            System.out.println("Insufficient funds");
        }

    }

    public void pay(int paymentAmount, Bank_Accounts recipientAccount) {
        if (getOwner()!=recipientAccount.getOwner()) {
            paymentAmount = VerifyPayment(paymentAmount);
            if (paymentAmount != 0 & (getBalance() - (int) (1 + paymentAmount / 0.75)) >= 0) {
                if (recipientAccount.getAccountType().equals("ISA")) {
                    ISA Recipient = Main_Program.FindISAAccount(recipientAccount);
                    if (Recipient.getCurrentAnnualDeposit() + paymentAmount <= MaxAnnualDeposit) {
                        setBalance(getBalance() - (int) (1 + paymentAmount / 0.75));
                        recipientAccount.setBalance(recipientAccount.getBalance() + paymentAmount);
                        Recipient.setCurrentAnnualDeposit(Recipient.getCurrentAnnualDeposit() + paymentAmount);
                    } else {
                        System.out.println("Max annual deposit reached");
                    }
                } else {
                    setBalance(getBalance() - (int) (1 + paymentAmount / 0.75));
                    recipientAccount.setBalance(recipientAccount.getBalance() + paymentAmount);
                }
                if (recipientAccount.getAccountType().equals("Current")) {
                    Log.Log(getBankNumber(), Main_Program.FindBank(getBank()).getISASortCode(), recipientAccount.getBankNumber(), Main_Program.FindBank(recipientAccount.getBank()).getCurrentSortCode(), paymentAmount);
                } else if (recipientAccount.getAccountType().equals("Business")) {
                    Log.Log(getBankNumber(), Main_Program.FindBank(getBank()).getISASortCode(), recipientAccount.getBankNumber(), Main_Program.FindBank(recipientAccount.getBank()).getBusinessSortCode(), paymentAmount);
                } else {
                    Log.Log(getBankNumber(), Main_Program.FindBank(getBank()).getISASortCode(), recipientAccount.getBankNumber(), Main_Program.FindBank(recipientAccount.getBank()).getISASortCode(), paymentAmount);
                }

            }

        } else {
            System.out.println("Insufficient funds");
        }
    }



    public void deposit(int depositAmount){
        if(getCurrentAnnualDeposit() + depositAmount <= MaxAnnualDeposit) {
            setCurrentAnnualDeposit(getCurrentAnnualDeposit() + depositAmount);
            setBalance(getBalance() + (int) (depositAmount * 1.25));
        }
        else{
            System.out.println("Max annual deposit reached");
        }
    }

    public void addInterest(int interestAmount){
        this.InterestRate += interestAmount;
    }

}