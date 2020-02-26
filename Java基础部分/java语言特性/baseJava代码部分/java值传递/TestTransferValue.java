package base;
/*
* 方法的参数传递机制
* */
/*
*java中只有值传递，没有引用传递
* 1.方法内如果传基本类型，传的是复印件，原件不动
* 2.方法内如果传引用类型，传递的是引用（地址）
 main方法在Java栈中，先进入栈底
*
*
* */
class Person{
    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
public class TestTransferValue {

    public void changeValue1(int age){
       age=30;
    }
    public void changeValue2(Person person){
        person.setPersonName("xxx");
    }
    public void changeValue3(String str){
        str="xxx";
    }

    public static void main(String[] args) {

        TestTransferValue test = new TestTransferValue();
        int age=20;
        test.changeValue1(age);
        System.out.println("age-----"+age);//20

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName----"+person.getPersonName());//xxx

        String str="abc";//在字符串常量池
        //如果字符串常量池中有直接复用，如果没有，新建
        test.changeValue3(str);
        System.out.println("String-----"+str);//abc
    }
}
