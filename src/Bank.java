public class Bank {
    private int CurrentSortCode;
    private int ISASortCode;
    private int BusinessSortCode;

    public Bank(int currentSortCode, int ISASortCode, int businessSortCode) {
        CurrentSortCode = currentSortCode;
        this.ISASortCode = ISASortCode;
        BusinessSortCode = businessSortCode;
    }

    public int getCurrentSortCode() {
        return CurrentSortCode;
    }

    public void setCurrentSortCode(int currentSortCode) {
        CurrentSortCode = currentSortCode;
    }

    public int getISASortCode() {
        return ISASortCode;
    }

    public void setISASortCode(int ISASortCode) {
        this.ISASortCode = ISASortCode;
    }

    public int getBusinessSortCode() {
        return BusinessSortCode;
    }

    public void setBusinessSortCode(int businessSortCode) {
        BusinessSortCode = businessSortCode;
    }
}
