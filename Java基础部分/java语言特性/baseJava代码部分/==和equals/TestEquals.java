package base;

import java.util.HashSet;
/*
* String s1 = "abc" ;
String s2 = new String("abc");
首先，我们先看看以上两句分别做了什么？
String s1 = “abc” 做了什么
在栈中创建了一个名为 s1 的变量（引用）
如果 String池 中没有 “abc” 存在，则在常量池中创建一个 String 类型的 “abc” 对象，有就不创建
将 “abc” 的地址赋给 s1
所以，此句到底创建了几个对象，根据 “abc” 的情况而定，“abc” 之前存在就是一个，否则就没有创建。

String s2 = new String(“abc”) 做了什么
创建了一个名为 s2 的变量（引用）
如果 String池 中没有 “abc” 存在，则在 String池 中创建一个 String 类型的 “abc” ，有就不创建
使用 new关键字 在堆中创建了一个 String 对象
将用 new 创建的 String 对象的地址赋给 s2
所以，此句到底创建了几个对象，根据 “abc” 的情况而定，“abc” 之前存在就是1个，不存在就是2个。

栈：存放基本类型的数据和对象的引用，但对象本身不存放在栈中，而是存放在堆中
堆：存放用 new 产生的数据

s1 == s2 与 s1.equals(s2)
s1 与 s2 看着都是内容为 “abc” 的字符串，但是使用 == 和 equals() 来判断它们是否相等时会发现，结果并不一样。

s1 == s2
结果为false，根据上述内容我们可以知道，两者虽然内容一致，但是其实两者的指向的内存地址并不一致，而 == 会检查两者的内存地址是否一致，一致为true，不一致为false

s1.equals(s2)
结果为true，因为 equals() 在检查地址不相等后，还会检查两者的内容，两者内容相等，则仍然返回true，毫无疑问，两者的内容是一致的。

* */
/*
* "=="和equals
* "=="：在比较基本类型的时候比较值是否相等，在比较引用类型的时候，比较地址是否相等
* "equals":对于基本类型比较的是值是否相等，如果没有重写equals方法，对于引用类型比较的是地址，反正，比较值
* */
public class TestEquals {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = new String("abc");
        //String 内部重写了equals
        //new 出来的对象==比较是false

        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        HashSet<String> set01 = new HashSet<>();
        //因为重写了equals方法，所以也重写了hashcode方法
//        如何判断是否是同一个对象根据hashcode
        set01.add(s1);
        set01.add(s2);
        System.out.println(set01.size());

        System.out.println("======================");

        Person p1 = new Person("abc");
        Person p2 = new Person("abc");
        System.out.println(p1==p2);
        System.out.println(p1.equals(p2));
        HashSet<Person> set02 = new HashSet<>();
        set02.add(p1);
        set02.add(p2);
        System.out.println(set02.size());

    }
}
