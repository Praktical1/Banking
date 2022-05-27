public class Address {
    private String House;
    private String Road;

    @Override
    public String toString() {
        return House+"_"+Road+"_"+Town+"_"+County+"_"+PostCode;
    }

    private String Town;
    private String County;
    private String PostCode;

    public Address(String house, String road, String town, String county, String postCode) {
        this.House = house;
        this.Road = road;
        this.Town = town;
        this.County = county;
        this.PostCode = postCode;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getRoad() {
        return Road;
    }

    public void setRoad(String road) {
        Road = road;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }
}
