package Java116;

public class Student implements Cloneable{
    private String name;
    private int age;
    private Address address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Student(String name, int age, Address address) {
        super();
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public Student() {
//        super();
    }
    @Override
    public String toString() {
        return "[" + name + "*******" + age + "*******" + address + "]";

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student stu = (Student) super.clone();//浅复制
        stu.address = (Address) address.clone();//深复制
        return stu;
    }
}

