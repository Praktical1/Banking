public class Business {
    private int BusinessNumber;

    public Business(int businessNumber) {
        BusinessNumber = businessNumber;
    }

    public int getBusinessNumber() {
        return BusinessNumber;
    }

    public void setBusinessNumber(int businessNumber) {
        BusinessNumber = businessNumber;
    }

    //Withdraw: Takes "Value" from "account"'s balance
    public void Withdraw(int Value, Bank_Accounts account){
        account.setBalance(account.getBalance()-Value);
    }
    //Deposit: Puts "Value" into "account"'s balance
    public void Deposit(int Value, Bank_Accounts account){
        account.setBalance(account.getBalance()+Value);
    }
    //Transfer: Takes "Value" from "account1" and deposits it in "account2" if account 2 is not an ISA
    public void Transfer(int Value, Bank_Accounts account1, Bank_Accounts account2){
        account1.setBalance(account1.getBalance()-Value);
        switch (account2.getAccountType()){
            case "Business", "Current" -> account2.setBalance(account2.getBalance()+Value);
            //If ISA, checks the deposit limit and deposits accordingly
            case "ISA"-> {
                if(true) {
                    //if ISA limit has not been reached and will not be surpassed
                    account2.setBalance(account2.getBalance()+Value);
                } else{
                    account1.setBalance(account1.getBalance()+Value);
                    System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                }
            }
        }
    }
    public void Pay(int Value, Bank_Accounts account1, Bank_Accounts account2){
        Transfer(Value,account1,account2);
        //Add to log file
    }
    public void Upkeep(Bank_Accounts account){
        account.setBalance(account.getBalance()-50);
    }
}
