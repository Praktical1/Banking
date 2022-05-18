public class Current {
    private int BankNumber;
    private int SortCode;
    private int Balance;
    public void transfer(int BankNumber, int SortCode, int RecipientBankNumber, int RecipientSortCode, int Transferred) {
        if (Balance<Transferred) {
            System.out.println("Insufficient Balance");
        } else {
            Withdraw(BankNumber,SortCode,Transferred);
            if (AccountType.Equals("ISA"){
                if ISA.
            }
            Deposit(RecipientBankNumber,RecipientSortCode,Transferred);
        }
    }
    private void Deposit(int BankNumber, int SortCode, int Deposited) {
        Balance = Balance + Deposited;
    }

    private boolean Withdraw(int bankNumber, int sortCode, int Withdrawn) {
        Balance = Balance - Withdrawn;
    }
}
