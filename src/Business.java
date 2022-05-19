

public class Business {
    private int BusinessNumber;
    private Bank_Accounts Account;

    public Business(int businessNumber, Bank_Accounts account) {
        BusinessNumber = businessNumber;
        Account = account;
    }
    public Business(Bank_Accounts account){
        Account = account;
    }

    public Bank_Accounts getAccount() {
        return Account;
    }

    public void setAccount(Bank_Accounts account) {
        Account = account;
    }

    public int getBusinessNumber() {
        return BusinessNumber;
    }

    public void setBusinessNumber(int businessNumber) {
        BusinessNumber = businessNumber;
    }

    //Withdraw: Takes "Value" from "account"'s balance
    public void Withdraw(int Value){
        getAccount().setBalance(getAccount().getBalance()-Value);
    }
    //Deposit: Puts "Value" into "account"'s balance
    public void Deposit(int Value){
        getAccount().setBalance(getAccount().getBalance()+Value);
    }
    //Transfer: Takes "Value" from "account1" and deposits it in "account2" if account 2 is not an ISA
    public void Transfer(int Value, Bank_Accounts account){
        if(getAccount().getOwner().equals(account.getOwner())) {
            getAccount().setBalance(getAccount().getBalance() - Value);
            switch (account.getAccountType()) {
                case "Business", "Current" -> account.setBalance(account.getBalance() + Value);
                //If ISA, checks the deposit limit and deposits accordingly
                case "ISA" -> {
                    if (ISA(account).getAnnualDeposit() + Value < ISA.Max_Annual_Deposit) {
                        //if ISA limit has not been reached and will not be surpassed
                        account.setBalance(account.getBalance() + Value);
                    } else {
                        getAccount().setBalance(getAccount().getBalance() + Value);
                        System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                    }
                }
            }
        }else{
            System.out.println("Error: Cannot Transfer externally, Please use the Pay function to send money to someone else");
        }
    }
    public void Pay(int Value, Bank_Accounts account){
        getAccount().setBalance(Bank_Accounts.getBalance() - Value);
        switch (Bank_Accounts.getAccountType()) {
            case "Business", "Current" -> account.setBalance(account.getBalance() + Value);
            //If ISA, checks the deposit limit and deposits accordingly
            case "ISA" -> {
                if (ISA(account).getAnnualDeposit() + Value < ISA.Max_Annual_Deposit) {
                    //if ISA limit has not been reached and will not be surpassed
                    account.setBalance(account.getBalance() + Value);
                } else {
                    getAccount().setBalance(getAccount().getBalance() + Value);
                    System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                }
            }
        }
        switch(account.getAccountType()){
            case "Current" -> Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(),account.getBankNumber(),account.getBank().getCurrentSortCode(),Value);
            case "ISA" -> Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(),account.getBankNumber(),account.getBank().getISASortCode(),Value);
            case "Business" -> Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(),account.getBankNumber(),account.getBank().getBusinessSortCode(),Value);
        }
    }
    public void Upkeep(){
        getAccount().setBalance(getAccount().getBalance()-50);
    }
}
