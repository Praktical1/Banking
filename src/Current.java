public class Current{
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
            System.out.println("Error: Insufficient Funds");
        } else {
            if (AccountType.equals("ISA")) {
                ISA ISAAccount = Main_Program.FindISAAccount(account);
                if (ISAAccount.getCurrentAnnualDeposit() + Transferred < ISA.MaxAnnualDeposit) {
                    account.setBalance(account.getBalance() + Transferred);
                    getAccount().setBalance(getAccount().getBalance() - Transferred);
                    ISAAccount.setCurrentAnnualDeposit(ISAAccount.getCurrentAnnualDeposit()+Transferred);
                } else {
                    System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                }
            } else {
                account.setBalance(account.getBalance() + Transferred);
                getAccount().setBalance(getAccount().getBalance() - Transferred);
            }
        }
    }
    private void pay(int Payment, Bank_Accounts account) {
        String AccountType = getAccount().getAccountType();
        if(getAccount().getOwner().equals(account.getOwner())) {
            if (getAccount().getBalance() < Payment) {
                System.out.println("Error: Insufficient Funds");
            } else {
                if (AccountType.equals("ISA")) {
                    ISA ISAAccount = Main_Program.FindISAAccount(account);
                    if (ISAAccount.getCurrentAnnualDeposit() + Payment < ISA.MaxAnnualDeposit) {
                        account.setBalance(account.getBalance() + Payment);
                        getAccount().setBalance(getAccount().getBalance() - Payment);
                        ISAAccount.setCurrentAnnualDeposit(ISAAccount.getCurrentAnnualDeposit() + Payment);
                        Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(), account.getBankNumber(), account.getBank().getISASortCode(), Payment);
                    } else {
                        System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                    }
                } else {
                    account.setBalance(account.getBalance() + Payment);
                    getAccount().setBalance(getAccount().getBalance() - Payment);
                    Log.Log(getAccount().getBankNumber(), getAccount().getBank().getBusinessSortCode(), account.getBankNumber(), account.getBank().getCurrentSortCode(), Payment);
                }
            }
        } else {
            System.out.println("Error: Cannot Transfer externally, Please use the Pay function to send money to someone else");
        }
    }

    private void Deposit(int Deposited) {
        Deposited = Account.VerifyPayment(Deposited);
        getAccount().setBalance(getAccount().getBalance() + Deposited);
    }

    private void Withdraw(int Withdrawn) {
        if (getAccount().getBalance()<Withdrawn) {
            System.out.println("Error: Insufficient Funds");
        } else {
            Withdrawn = Account.VerifyPayment(Withdrawn);
            getAccount().setBalance(getAccount().getBalance() - Withdrawn);
        }
    }
}