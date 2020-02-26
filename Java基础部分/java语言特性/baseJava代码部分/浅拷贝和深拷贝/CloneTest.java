package Java116;
/*
* https://blog.csdn.net/weixin_43610698/article/details/92579805
* */
/*
* 原因出在Student stu2 = stu1; 这一句。该语句的作用是将stu1的引用赋值给stu2，这样，stu1和stu2指向内存堆中同一个对象。
* */
public class CloneTest {
    public static void main(String[] args) {
   /*     Student stu1 = new Student("哼哼", 22, new Address("上海"));
        Student stu2 = stu1;
        System.out.println("直接复制没有修改的");
        System.out.println(stu1);
        System.out.println(stu2);
        stu2.setName("小哈");
        stu2.setAge(20);
        System.out.println("修改stu2后");
        System.out.println(stu1);
        System.out.println(stu2);*/


    //浅拷贝
        /*
        *
        *
        * 从输出结果中我们可以看出同过clone方法克隆出来的对象只是原有对象的副本，是不同的两个对象，但是他们属于同一个类。

可以很明显的发现当我们修改stu3的年龄时，stu4 的年龄没有变化，但是修改stu3的地址时，stu4的地址也发生了变化，这肯定不是我们想要的，这是为什么呢？

在浅克隆中，如果原型对象的成员变量是基本类型时，将复制一份给克隆对象；如果原型对象的成员变量是引用类型，则将引用对象的地址复制一份给克隆对象，也就是说原型对象和克隆对象的成员变量指向相同的内存地址。

简单来说，在浅克隆中，当对象被复制时只复制它本身和其中包含的基本类型的成员变量，而引用类型的成员对象并没有复制

        * */
        Address arr = new Address("北京");
        Student stu3 = new Student("哼哼", 22, arr);
        try {
            System.out.println(stu3);
           Student stu4 = (Student) stu3.clone();
            System.out.println("两个对象是否是同一个对象" + (stu3 == stu4));
            System.out.println("两个对象是都属于同一个类：" + stu3.getClass().equals(stu4.getClass()));
            stu3.setAge(34);
            stu3.getAddress().setAddress("上海");
            System.out.println("修改stu3后");
            System.out.println(stu3);
            System.out.println(stu4);
        } catch (CloneNotSupportedException e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}