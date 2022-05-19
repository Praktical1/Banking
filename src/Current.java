public class Current {
    private static int Balance;
    private static void transfer(int AccountNumber, int SortCode, int PIN, int RecipientAccountNumber, int RecipientSortCode, int Transferred) {
        Balance = Bank_Accounts.getBalance();
        String AccountType = Bank_Accounts.getAccountType();
        if (Balance<Transferred) {
            System.out.println("Insufficient Balance");
        } else {
            if (AccountType.equals("ISA")) {
                if (ISA.getAmountAddedIntoAccount() + Transferred < ISA.Max_Annual_Deposit) {
                    ISA.Deposit(RecipientAccountNumber,RecipientSortCode,Transferred);
                    Withdraw(AccountNumber,SortCode,PIN, Transferred);
                } else {
                    System.out.println("Cannot transfer, Annual deposit limit will be reached");
                }
            } else if (AccountType.equals("Business")) {
                Business.Deposit(RecipientAccountNumber,RecipientSortCode,Transferred);
                Withdraw(AccountNumber,SortCode,PIN,Transferred);
            } else {
                Deposit(RecipientAccountNumber,RecipientSortCode,Transferred);
                Withdraw(AccountNumber,SortCode,PIN,Transferred);
            }
        }
    }
    private static void pay(int AccountNumber, int SortCode, int PIN, int RecipientAccountNumber, int RecipientSortCode, int Payment) {
        Balance = Bank_Accounts.getBalance();
        String AccountType = Bank_Accounts.getAccountType();
        if (Balance<Payment) {
            System.out.println("Insufficient Balance");
        } else {
            if (AccountType.equals("ISA")) {
                if (ISA.getAmountAddedIntoAccount() + Payment < ISA.Max_Annual_Deposit) {
                    ISA.Deposit(RecipientAccountNumber,RecipientSortCode,Payment);
                    Withdraw(AccountNumber,SortCode,PIN,Payment);
                    Log.Log(AccountNumber,SortCode,RecipientAccountNumber,RecipientSortCode,Payment);
                } else {
                    System.out.println("Cannot transfer, Annual deposit limit will be reached");
                }
            } else if (AccountType.equals("Business")) {
                Business.Deposit(RecipientAccountNumber,RecipientSortCode,Payment);
                Withdraw(AccountNumber,SortCode,PIN,Payment);
                Log.Log(AccountNumber,SortCode,RecipientAccountNumber,RecipientSortCode,Payment);
            } else {
                Deposit(RecipientAccountNumber,RecipientSortCode,Payment);
                Withdraw(AccountNumber,SortCode,PIN,Payment);
                Log.Log(AccountNumber,SortCode,RecipientAccountNumber,RecipientSortCode,Payment);
            }
        }
    }

    private static void Deposit(int AccountNumber, int SortCode, int Deposited) {
        Bank_Accounts.setBalance(Bank_Accounts.getBalance() + Deposited);
    }

    private static void Withdraw(int AccountNumber, int sortCode, int PIN, int Withdrawn) {
        Bank_Accounts.setBalance(Bank_Accounts.getBalance() + Withdrawn);
    }
}