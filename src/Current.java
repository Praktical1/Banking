public class Current {
    private int BankNumber;
    private int SortCode;
    private int Balance;
    private void transfer(int BankNumber, int SortCode, int RecipientBankNumber, int RecipientSortCode, int Transferred) {
        Balance = Bank_Accounts.getBalance();
        String AccountType = Bank_Accounts.getAccountType();
        if (Balance<Transferred) {
            System.out.println("Insufficient Balance");
        } else {
            if (AccountType.equals("ISA")) {
                if (ISA.AnnualDeposit + Transferred < ISA.Max_Annual_Deposit) {
                    Deposit(RecipientBankNumber,RecipientSortCode,Transferred);
                    Withdraw(BankNumber,SortCode,Transferred);
                } else {
                    System.out.println("Cannot transfer, Annual deposit limit will be reached");
                }
            } else if (AccountType.equals("Business")) {
                Deposit(RecipientBankNumber,RecipientSortCode,Transferred);
                Withdraw(BankNumber,SortCode,Transferred);
            } else {
                System.out.println("Unrecognized account type, check code");
            }
        }
    }
    private void Deposit(int BankNumber, int SortCode, int Deposited) {
        Bank_Accounts.setBalance() = Bank_Accounts.getBalance() + Deposited;
    }

    private boolean Withdraw(int bankNumber, int sortCode, int Withdrawn) {
        Balance = Balance - Withdrawn;
    }
}
