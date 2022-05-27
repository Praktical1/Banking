public class Current extends Bank_Accounts{
    public Current(int bankNumber, String PIN, int balance, int bankindex, String accountType, int index){
        super(bankNumber, PIN, balance, bankindex, accountType, index);
    }

    public void Transfer(int Transferred, Bank_Accounts account) {
        if (Transferred > 0) {
            if(getOwner()==account.getOwner()) {
                if (getBalance()<Transferred) {
                    System.out.println("Error: Insufficient Funds");
                } else {
                    if (account.getAccountType().equals("ISA")) {
                        ISA ISAAccount = Main_Program.FindISAAccount(account);
                        if (ISAAccount.getCurrentAnnualDeposit() + Transferred <= ISA.MaxAnnualDeposit) {
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
        } else {
            System.out.println("Please input positive value");
        }
    }
    public void Pay(int Payment, Bank_Accounts account) {
        if (Payment > 0) {
            if(getOwner()!=account.getOwner()) {
                if (getBalance() < Payment) {
                    System.out.println("Error: Insufficient Funds");
                } else {
                    if (account.getAccountType().equals("ISA")) {
                        ISA ISAAccount = Main_Program.FindISAAccount(account);
                        if (ISAAccount.getCurrentAnnualDeposit() + Payment <= ISA.MaxAnnualDeposit) {
                            account.setBalance(account.getBalance() + Payment);
                            setBalance(getBalance() - Payment);
                            ISAAccount.setCurrentAnnualDeposit(ISAAccount.getCurrentAnnualDeposit() + Payment);
                            Log.Log(getBankNumber(), Main_Program.FindBank(getBank()).getCurrentSortCode(), account.getBankNumber(), Main_Program.FindBank(account.getBank()).getISASortCode(), Payment);
                        } else {
                            System.out.println("Error: ISA deposit limit will be surpassed by this transaction");
                        }
                    } else if (account.getAccountType().equals("Current")) {
                        account.setBalance(account.getBalance() + Payment);
                        setBalance(getBalance() - Payment);
                        Log.Log(getBankNumber(), Main_Program.FindBank(getBank()).getCurrentSortCode(), account.getBankNumber(), Main_Program.FindBank(account.getBank()).getCurrentSortCode(), Payment);
                    } else {
                        account.setBalance(account.getBalance() + Payment);
                        setBalance(getBalance() - Payment);
                        Log.Log(getBankNumber(), Main_Program.FindBank(getBank()).getCurrentSortCode(), account.getBankNumber(), Main_Program.FindBank(account.getBank()).getBusinessSortCode(), Payment);
                    }
                }
            } else {
                System.out.println("Error: Cannot pay to own account, Please use the transfer function to send money to someone else");
            }
        } else {
            System.out.println("Please input positive value");
        }
    }

    public void Deposit(int Deposited) {
        if (Deposited > 0) {
            setBalance(getBalance() + Deposited);
        } else {
            System.out.println("Please input positive value");
        }
    }

    public void Withdraw(int Withdrawn) {
        if (Withdrawn > 0) {
            if (getBalance()<Withdrawn) {
                System.out.println("Error: Insufficient Funds");
            } else {
                Withdrawn = VerifyPayment(Withdrawn);
                setBalance(getBalance() - Withdrawn);
            }
        } else {
            System.out.println("Please input positive value");
        }
    }
}