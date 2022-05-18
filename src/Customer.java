import java.util.Date;

public class Customer {
    String Name;
    int Age;
    Date DOB;
    int PhoneNumber;
    int MobNumber;
    Address address;

    public Customer(String name, int age, Date DOB, int phoneNumber, int mobNumber, Address address) {
        Name = name;
        Age = age;
        this.DOB = DOB;
        PhoneNumber = phoneNumber;
        MobNumber = mobNumber;
        this.address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getMobNumber() {
        return MobNumber;
    }

    public void setMobNumber(int mobNumber) {
        MobNumber = mobNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
