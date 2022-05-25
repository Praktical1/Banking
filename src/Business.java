public class Business extends Bank_Accounts{
    private int BusinessNumber;

    public Business(int bankNumber, String PIN, int balance, Bank bank, String accountType, Customer owner, int businessNumber) {
        super(bankNumber, PIN, balance, bank, accountType, owner);
        BusinessNumber = businessNumber;
    }

    public int getBusinessNumber() {
        return BusinessNumber;
    }

    public void setBusinessNumber(int businessNumber) {
        BusinessNumber = businessNumber;
    }

    //Withdraw: Takes "Value" from "account"'s balance
    public void Withdraw(int Value){
        Value = VerifyPayment(Value);
        if(getBalance() >= Value) {
            setBalance(getBalance() - Value);
        }else{
            System.out.println("Error: Insufficient Funds");
        }
    }
    //Deposit: Puts "Value" into "account"'s balance
    public void Deposit(int Value){
        Value = VerifyPayment(Value);
        setBalance(getBalance()+Value);
    }
    //Transfer: Takes "Value" from "account1" and deposits it in "account2" if account 2 is not an ISA
    public void Transfer(int Value, Bank_Accounts account){
        Value = account.VerifyPayment(Value);
        if(getBalance() >= Value) {
            if (getOwner().equals(account.getOwner())) {
                movemoney(account, Value);
            } else {
                System.out.println("Error: Cannot Transfer externally, Please use the Pay function to send money to someone else");
            }
        }else{
            System.out.println("Error: Insufficient Funds");
        }
    }
    public void Pay(int Value, Bank_Accounts account){
        Value = account.VerifyPayment(Value);
        if(!getOwner().equals(account.getOwner())) {
            if (getBalance() >= Value) {
                movemoney(account, Value);
                //logs the transaction
                if(Value != 0) {
                    switch (account.getAccountType()) {
                        case "Current" -> Log.Log(getBankNumber(), getBank().getBusinessSortCode(), account.getBankNumber(), account.getBank().getCurrentSortCode(), Value);
                        case "ISA" -> Log.Log(getBankNumber(), getBank().getBusinessSortCode(), account.getBankNumber(), account.getBank().getISASortCode(), Value);
                        case "Business" -> Log.Log(getBankNumber(), getBank().getBusinessSortCode(), account.getBankNumber(), account.getBank().getBusinessSortCode(), Value);
                    }
                }
            } else {
                System.out.println("Error: Insufficient Funds");
            }
        }else{
            System.out.println("Error: Please use Transfer to transfer money between your accounts");
        }
    }
    private void movemoney(Bank_Accounts account, int Value){
        setBalance(getBalance() - Value);
        switch (account.getAccountType()) {
            case "Business", "Current" -> account.setBalance(account.getBalance() + Value);
            //If ISA, checks the deposit limit and deposits accordingly
            case "ISA" -> {
                ISA ISAAccount = Main_Program.FindISAAccount(account);
                if (ISAAccount != null) {
                    if (ISAAccount.getCurrentAnnualDeposit() + Value < ISA.MaxAnnualDeposit) {
                        //if ISA limit has not been reached and will not be surpassed
                        account.setBalance(account.getBalance() + Value);
                        ISAAccount.setCurrentAnnualDeposit(ISAAccount.getCurrentAnnualDeposit() + Value);
                    } else {
                        setBalance(getBalance() + Value);
                        System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                    }
                } else{
                    setBalance(getBalance() + Value);
                    System.out.println("Error: ISA account not found");
                }
            }
        }
    }
    public void Upkeep(){
        setBalance(getBalance()-50);
    }
}

