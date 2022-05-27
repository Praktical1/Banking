public class Bank {
    private String Name;
    private int CurrentSortCode;
    private int ISASortCode;
    private int BusinessSortCode;
    private final int Index;

    public Bank(int currentSortCode, int ISASortCode, int businessSortCode, String name, int index) {
        CurrentSortCode = currentSortCode;
        this.ISASortCode = ISASortCode;
        BusinessSortCode = businessSortCode;
        Name = name;
        Index = index;
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

    public String getName() {
        return Name;
    }

    public int getIndex() { return Index; }

    public void setName(String name) {
        Name = name;
    }
}
