public class Current {
    private Bank_Accounts Account;
    public Current(Bank_Accounts account){
        Account = account;
    }
    public Bank_Accounts getAccount() {
        return Account;
    }
    public void transfer(int Transferred, Bank_Accounts account) {
        String AccountType = getAccount().getAccountType();
        if (getAccount().getBalance()<Transferred) {
            System.out.println("Insufficient Balance");
        } else {
            if (AccountType.equals("ISA")) {
                if (ISA.getAmountAddedIntoAccount() + Transferred < ISA.Max_Annual_Deposit) {
                    account.setBalance(account.getBalance() + Transferred);
                    getAccount().setBalance(getAccount().getBalance() - Transferred);
                    ISA.setAmountAddedIntoAccount(ISA.getAmountAddedIntoAccount()+Transferred);
                } else {
                    System.out.println("Cannot transfer, Annual deposit limit will be reached");
                }
            } else if (AccountType.equals("Business")) {
                account.setBalance(account.getBalance() + Transferred);
                getAccount().setBalance(getAccount().getBalance() - Transferred);
            } else {
                account.setBalance(account.getBalance() + Transferred);
                getAccount().setBalance(getAccount().getBalance() - Transferred);
            }
        }
    }
    private void pay(int Payment, Bank_Accounts account) {
        String AccountType = getAccount().getAccountType();
        if (getAccount().getBalance()<Payment) {
            System.out.println("Insufficient Balance");
        } else {
            if (AccountType.equals("ISA")) {
                if (ISA.getAmountAddedIntoAccount() + Payment < ISA.Max_Annual_Deposit) {
                    account.setBalance(account.getBalance() + Payment);
                    getAccount().setBalance(getAccount().getBalance() - Payment);
                    Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(),account.getBankNumber(),account.getBank().getISASortCode(),Payment);
                } else {
                    System.out.println("Cannot transfer, Annual deposit limit will be reached");
                }
            } else if (AccountType.equals("Business")) {
                account.setBalance(account.getBalance() + Payment);
                getAccount().setBalance(getAccount().getBalance() - Payment);
                Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(),account.getBankNumber(),account.getBank().getBusinessSortCode(),Payment);
            } else {
                account.setBalance(account.getBalance() + Payment);
                getAccount().setBalance(getAccount().getBalance() - Payment);
                Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(),account.getBankNumber(),account.getBank().getCurrentSortCode(),Payment);
            }
        }
    }

    private void Deposit(int Deposited) {
        Deposited = Account.VerifyPayment(Deposited);
        getAccount().setBalance(getAccount().getBalance() + Deposited);
    }

    private void Withdraw(int Withdrawn) {
        Withdrawn = Account.VerifyPayment(Withdrawn);
        getAccount().setBalance(getAccount().getBalance() + Withdrawn);
    }
}