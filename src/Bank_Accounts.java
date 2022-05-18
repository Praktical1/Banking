public class Bank_Accounts {
    private int BankNumber;
    private int PIN;
    private Bank bank;
    public Bank_Accounts() {
        BankNumber = this.BankNumber;
        PIN = this.PIN;
        bank = this.bank;
    }

    public int getBankNumber() {
        return BankNumber;
    }

    public void setBankNumber(int bankNumber) {
        BankNumber = bankNumber;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
