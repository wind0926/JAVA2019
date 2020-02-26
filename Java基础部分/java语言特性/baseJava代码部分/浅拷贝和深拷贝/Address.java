package Java116;

public class Address implements Cloneable{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address(String address) {
//        super();
        this.address = address;

    }

    @Override
    public String toString() {
        return " [" + address + "]";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}