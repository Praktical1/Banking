public class Current extends Bank_Accounts{
    public Current(int bankNumber, int PIN, int balance, Bank bank, String accountType, Customer owner){
        super(bankNumber, PIN, balance, bank, accountType, owner);
    }

    public void transfer(int Transferred, Bank_Accounts account) {
        String AccountType = getAccountType();
        if(getOwner().equals(account.getOwner())) {
            if (getBalance()<Transferred) {
                System.out.println("Error: Insufficient Funds");
            } else {
                if (AccountType.equals("ISA")) {
                    ISA ISAAccount = Main_Program.FindISAAccount(account);
                    if (ISAAccount.getCurrentAnnualDeposit() + Transferred < ISA.MaxAnnualDeposit) {
                        account.setBalance(account.getBalance() + Transferred);
                        setBalance(getBalance() - Transferred);
                        ISAAccount.setCurrentAnnualDeposit(ISAAccount.getCurrentAnnualDeposit()+Transferred);
                    } else {
                        System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                    }
                } else {
                    account.setBalance(account.getBalance() + Transferred);
                    setBalance(getBalance() - Transferred);
                }
            }
        } else {
            System.out.println("Error: Cannot Transfer externally, Please use the Pay function to send money to someone else");
        }
    }
    private void pay(int Payment, Bank_Accounts account) {
        String AccountType = getAccountType();
        if(!getOwner().equals(account.getOwner())) {
            if (getBalance() < Payment) {
                System.out.println("Error: Insufficient Funds");
            } else {
                if (AccountType.equals("ISA")) {
                    ISA ISAAccount = Main_Program.FindISAAccount(account);
                    if (ISAAccount.getCurrentAnnualDeposit() + Payment < ISA.MaxAnnualDeposit) {
                        account.setBalance(account.getBalance() + Payment);
                        setBalance(getBalance() - Payment);
                        ISAAccount.setCurrentAnnualDeposit(ISAAccount.getCurrentAnnualDeposit() + Payment);
                        Log.Log(getBankNumber(), getBank().getBusinessSortCode(), account.getBankNumber(), account.getBank().getISASortCode(), Payment);
                    } else {
                        System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                    }
                } else {
                    account.setBalance(account.getBalance() + Payment);
                    setBalance(getBalance() - Payment);
                    Log.Log(getBankNumber(), getBank().getBusinessSortCode(), account.getBankNumber(), account.getBank().getCurrentSortCode(), Payment);
                }
            }
        } else {
            System.out.println("Error: Cannot pay to own account, Please use the transfer function to send money to someone else");
        }
    }

    private void Deposit(int Deposited) {
        Deposited = VerifyPayment(Deposited);
        setBalance(getBalance() + Deposited);
    }

    private void Withdraw(int Withdrawn) {
        if (getBalance()<Withdrawn) {
            System.out.println("Error: Insufficient Funds");
        } else {
            Withdrawn = VerifyPayment(Withdrawn);
            setBalance(getBalance() - Withdrawn);
        }
    }
}