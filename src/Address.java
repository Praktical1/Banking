public class Address {
    String House;
    String Road;
    String Town;
    String County;
    String PostCode;

    public Address(String house, String road, String town, String county, String postCode) {
        House = house;
        Road = road;
        Town = town;
        County = county;
        PostCode = postCode;
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
