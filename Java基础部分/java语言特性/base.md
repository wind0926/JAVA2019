# Java基础  
**JVM（Java Virtual Machine ）:** Java虚拟机，简称JVM，是运行所有Java程序的假想计算机，是Java程序的运行环境，是Java 最具吸引力的特性之一。我们编  写的Java代码，都运行在 JVM 之上。   
**跨平台：** 任何软件的运行，都必须要运行在操作系统之上，而我们用Java编写的软件可以运行在任何的操作系 统上，这个特性称为Java语言的跨平台特性。该特性是由JVM实现的，我们编写的程序运行在JVM上，而JVM运行在操作系统上。  
**JRE (Java Runtime Environment) ：** 是Java程序的运行时环境，包含 JVM 和运行时所需要的 核心类库 。   
**JDK (Java Development Kit)：** 是Java程序开发工具包，包含 JRE 和开发人员使用的工具。  

### 标识符
标识符可以包含 英文字母26个(区分大小写) 、 0-9数字 、 $（美元符号） 和 _（下划线） 。   
标识符不能以数字开头。   
标识符不能是关键字。

### 数据类型
**数据类型分类:**  
Java的数据类型分为两大类：   
基本数据类型：包括 整数 、 浮点数 、 字符 、 布尔 。   
引用数据类型：包括 类 、 数组 、 接口 。   
**基本数据类型**   
四类八种基本数据类型：  
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%201.png)  

### 数据类型转换 

**自动转换：** 将取值范围小的类型自动提升为取值范围大的类型。  
**转换规则**  
范围小的类型向范围大的类型提升， byte、short、char 运算时直接提升为 int。   
byte、short、char‐‐>int‐‐>long‐‐>float‐‐>double   
**强制转换:**   
int i = (int)1.5;  // double类型数据强制转成int类型，直接去掉小数点.   
**Java中汉字占几个字节？**    
可能占2、3、4个字节，看编码格式  
```java  
String a="唐";  
System.out.println("UTF-8编码长度:"+a.getBytes("utf-8").length);//3  
System.out.println("GBK编码长度:"+a.getBytes("GBK").length);//2  
System.out.println(a.length());//1  
```  

### 自动装箱和自动拆箱
**Java 中为每个基本类型都提供了相应的包装类型**  
**自动装箱：java自动将原始类型值转换成对应的对象；**      
**自动拆箱：反之;**    
```java  
例如：Inteager i=100;  
自动装箱 inteager i=Inteager.valueOf(100);  
Int t=i;
自动拆箱：t.integer.intValue();  
```

### 方法重载
**方法重载：** 指在同一个类中，允许存在一个以上的同名方法，只要它们的参数列表不同即可，与修饰符和返 回值类型无关。   
**参数列表：** 个数不同，数据类型不同，顺序不同。   
**重载方法调用：** JVM通过方法的参数列表，调用不同的方法。   
构造器可以被重载。  
**重载规则：**    
1.被重载的方法必须改变参数列表；  
2.被重载的方法可以改变返回类型；  
3.被重载的方法可以改变访问修饰符；  
4.方法能够在同一类中或者在一个子类中被重载。  

### 类与对象的关系 
类是对一类事物的描述，是抽象的。   
对象是一类事物的实例，是具体的。   
类是对象的模板，对象是类的实体。  

### 对象内存图 
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%202.png)


### Java中String、StringBuilder、StringBuffer的区别，StingBuffer是如何扩容的？
在String源码中：  
```java
private final char value[];  
```
String是final修饰的char类型的value数组，是不可改变的。  
StringBuffer是线程安全的，支持并发操作，支持多线程；  
StringBuilder是线程不安全的，不支持并发操作，支持单线程。    
StringBuffer继承抽象类AbstractStringBuilder，StringBuffer可以给指定对象的实体初始化容量字符串长度再另外加16个字符串，super(str.length+16),这是自动扩容，还有就是扩容算法：使用append（）方法在字符串后面追加东西的时候，如果超过了字符串存储空间大小就需要进行扩容，传入一个方法：ensureCapacityInternal进行是否扩容判断，需要扩容就调用expendCapacity方法进行扩容。  
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%204.png)

### “a==b” 和“a.equal(b)”的区别？
==在比较基本类型的时候，比较的是值，在比较引用类型时，比较两个对象的地址值，  
equals方法存于object类中，如果不重写方法，那么与==原理相同，绝大多数类都重写了equal方法，重写后的equal方法比较的是两个对象的值。
### 如何编写equals方法？
重写equals方法的要求：1.自反性 2.对称性 3.传递性 4.一致性 5.非空性 
```java
Public boolean equals(Object object){
If(obj instanceof Person){
Person person=(Person)obj; //向下转型
 Return name.equalsIgnoreCase(person.getName().trim());
}
Return false;
}
```

### 方法重写 ：
  子类和父类之间的关系，子类重写父类的方法，方法名、参数类型、参数个数、参数列表都不能改变。  
1.返回类型与重写方法的返回类型可以不相同，但是必须是父类返回类型的派生类；  
2.访问权限不能比父类中被重写的方法的访问权限更低；    
3.父类的成员方法只能被他的子类重写；  
4.声明为final的方法不能被重写；  
5.声明为static的方法不能被重写，但是能够被再次声明；  
6.构造方法不能被重写。  

### 抽象类 
### 抽象方法 
使用 abstract 关键字修饰方法，该方法就成了抽象方法，抽象方法只包含一个方法名，而没有方法体。 
定义格式：   
修饰符 abstract 返回值类型 方法名 (参数列表)；   
代码举例：  
```java
public abstract void run()；
```
### 抽象类 
如果一个类包含抽象方法，那么该类必须是抽象类。 
定义格式：abstract class 类名字 {   
} 
代码举例：  
```java 
public abstract class Animal {   
public abstract void run()；   
}   
```
### 抽象的使用 
继承抽象类的子类必须重写父类所有的抽象方法。否则，该子类也必须声明为抽象类。最终，必须有子类实现该父类的抽象方法，否则，从最初的父类到最终的子类都不能创建对象，失去意义。   
1. 抽象类不能创建对象，如果创建，编译无法通过而报错。只能创建其非抽象子类的对象。   
2. 抽象类中，可以有构造方法，是供子类创建对象时，初始化父类成员使用的。   
3. 抽象类中，不一定包含抽象方法，但是有抽象方法的类必定是抽象类。  
4. 抽象类的子类，必须重写抽象父类中所有的抽象方法，否则，编译无法通过而报错。除非该子类也是抽象类。  

### 抽象类和接口的区别有哪些？
1.抽象类可以没有抽象方法，但接口中的方法必须是抽象方法；    
2.抽象类中可以存在普通属性，方法，静态属性和方法；    
3.接口中的变量必须是static final类型的，必须被初始化，只有常亮没有变量；    
4.抽象类只能单继承，接口可以继承多个父接口。    
在jdk8之前，interface之中可以定义变量和方法，变量必须是public、static、final的，方法必须是public、abstract的。由于这些修饰符都是默认的，所以在JDK8之前，下面的写法都是等价的。  
```java
public interface JDK8BeforeInterface {  
    public static final int field1 = 0;  
  
    int field2 = 0;  
  
    public abstract void method1(int a) throws Exception;  
  
    void method2(int a) throws Exception;  
}
```
JDK8及以后，允许我们在接口中定义static方法和default方法。  
```java
public interface JDK8Interface {  
  
    // static修饰符定义静态方法  
    static void staticMethod() {  
        System.out.println("接口中的静态方法");  
    }  
  
    // default修饰符定义默认方法  
    default void defaultMethod() {  
        System.out.println("接口中的默认方法");  
    }  
}
```

### 抽象类和接口如何选择？
如果准备为不相关的类创建一些提供共有功能的东西，请使用接口。  
抽象类允许你为子类提供默认功能。  
当创建一个可以被随意更改的独立项目时，使用优先于抽象类的接口，因为它提供更多的设计灵活性。  
使用接口来引入多态行为而无需子类化并模拟多重继承 - 允许特定类型支持多种行为。  

### static关键字  
在static方法内部不能调用非静态方法，反过来是可以的。而且可以在没有创建任何对象的前提下，仅仅通过类本身来调用static方法。  
static可以用来修饰类的成员方法、类的成员变量，另外可以编写static代码块来优化程序性能。  
>>static方法    
static方法一般称作静态方法，由于静态方法不依赖于任何对象就可以进行访问，因此对于静态方法	来说，是没有this的，因为它不依附于任何对象，既然都没有对象，就谈不上this了。    
>>static变量  
static变量也称作静态变量，静态变量和非静态变量的区别是：静态变量被所有的对象所共享，在内存中只有一个副本，它当且仅当在类初次加载时会被初始化。而非静态变量是对象所拥有的，在创建对象的时候被初始化，存在多个副本，各个对象拥有的副本互不影响。static成员变量的初始化顺序按照定义的顺序进行初始化。    
>>static代码块  
static关键字还有一个比较关键的作用就是用来形成静态代码块以优化程序性能。static块可以置于类中的任何地方，类中可以有多个static块。在类初次被加载的时候，会按照static块的顺序来执行每个static块，并且只会执行一次。    


### final关键字
1.修饰类当用final去修饰一个类的时候，表示这个类不能被继承。  
2.修饰方法被final修饰的方法不能被重写.  
**注意：**  
a. 一个类的private方法会隐式的被指定为final方法。
b. 如果父类中有final修饰的方法，那么子类不能去重写。  
3.修饰成员变量  
**注意：**   
a. 必须要赋初始值，而且是只能初始化一次。  
b. 被fianl修饰的成员变量赋值，有两种方式：1、直接赋值 2、全部在构造方法中赋初值。  
c. 如果修饰的成员变量是基本类型，则表示这个变量的值不能改变。  
d. 如果修饰的成员变量是一个引用类型，则是说这个引用的地址的值不能修改，但是这个引用所指	向的对象里面的内容还是可以改变的。  

### Final、finalize、finally的不同之处？
Final是一个修饰符可以修饰变量、方法和类，被final修饰后意味着值在初始化后不能改变，不能改变对象的引用，但可以使用set方法；  
GC垃圾回收机制，将调用finalize（）方法将对象从内存中清除之前进行的清理工作。  
Finally是一个关键字，与try catch一起进行异常处理，finally代码块一定会被执行到。  

### 引用类型转换 
向上转型   
向上转型：  
使用格式：   
父类类型 变量名 = new 子类类型();   
如：Animal a = new Cat();   
向下转型   
向下转型  
使用格式：   
子类类型 变量名 = (子类类型) 父类变量名;   
如:Cat c =(Cat) a;   

### 权限修饰符
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%203.png)


### 内部类 
成员内部类   
成员内部类 ：定义在类中方法外的类。 
定义格式：  
```java
class 外部类 { 
class 内部类{ 
 } 
}
```
访问特点   
内部类可以直接访问外部类的成员，包括私有成员。   
外部类要访问内部类的成员，必须要建立内部类的对象。   
创建内部类对象格式：   
外部类名.内部类名 对象名 = new 外部类型().new 内部类型()；   


### 匿名内部类
前提  
匿名内部类必须继承一个父类或者实现一个父接口。   
格式  
```java
new 父类名或者接口名(){ 
// 方法重写 
@Override 
public void method() { 
// 执行语句 
 } 
}; 
```

### 内部类总结：
1.为什么使用内部类?  
使用内部类最吸引人的原因是：每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。  
1.1.使用内部类最大的优点就在于它能够非常好的解决多重继承的问题,使用内部类还能够为我们带来如下特性:  
(1)内部类可以用多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立。  
(2)在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或者继承同一个类。  
(3)创建内部类对象的时刻并不依赖于外围类对象的创建。  
(4)内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体。  
(5)内部类提供了更好的封装，除了该外围类，其他类都不能访问。  
### 2.内部类分类:    
**(一).成员内部类:** 
```java
public class Outer{
	private int age = 99;
	String name = "Coco";
	public class Inner{
		String name = "Jayden";
		public void show(){
		System.out.println(Outer.this.name);
		System.out.println(name);
		System.out.println(age);
	}
}
public Inner getInnerClass(){
	return new Inner();
}
public static void main(String[] args){
	Outer o = new Outer();
	Inner in = o.new Inner();
	in.show();
	}
}
```
1.Inner 类定义在 Outer 类的内部，相当于 Outer 类的一个成员变量的位置，Inner 类可以使用任意访问	控制符，如 public 、 protected 、 private 等；  
2.Inner 类中定义的 show() 方法可以直接访问 Outer 类中的数据，而不受访问控制符的影响，如直接访问 Outer 类中的私有属性age；  
3.定义了成员内部类后，必须使用外部类对象来创建内部类对象，而不能直接去 new 一个内部类对象，即：内部类 对象名 = 外部类对象.new 内部类( );  
4.编译上面的程序后，会发现产生了两个 .class 文件: Outer.class,Outer$Inner.class{}  
5.成员内部类中不能存在任何 static 的变量和方法,可以定义常量:  
   (1)因为非静态内部类是要依赖于外部类的实例,而静态变量和方法是不依赖于对象的,仅与类相关,  
    简而言之:在加载静态域时,根本没有外部类,所在在非静态内部类中不能定义静态域或方法,编译不通过;  
   非静态内部类的作用域是实例级别；  
   (2)常量是在编译器就确定的,放到所谓的常量池了；  
**★★友情提示:**  
1.外部类是不能直接使用内部类的成员和方法的，可先创建内部类的对象，然后通过内部类的对象来访问其成员变量和方法;  
2.如果外部类和内部类具有相同的成员变量或方法，内部类默认访问自己的成员变量或方法，如果要访问外部类的成员变量,可以使用 this 关键字,如:Outer.this.name  

**(二).静态内部类: 是 static 修饰的内部类**  
1.静态内部类不能直接访问外部类的非静态成员，但可以通过 new 外部类().成员 的方式访问;    
2.如果外部类的静态成员与内部类的成员名称相同，可通过“类名.静态成员”访问外部类的静态成员,如果外部类的静态成员与内部类的成员名称不相同，则可通过“成员名”直接调用外部类的静态成员;  
3.创建静态内部类的对象时，不需要外部类的对象，可以直接创建 内部类 对象名 = new 内部类();  
```java
public class Outer{
	private int age = 99;
	static String name = "Coco";
	public static  class Inner{
		String name = "Jayden";
		public void show(){
			System.out.println(Outer.name);
			System.out.println(name); 
		}
}
public static void main(String[] args){
	Inner i = new Inner();
	i.show();
	}
}
```
**(三).方法内部类：**   
其作用域仅限于方法内，方法外部无法访问该内部类    
(1)局部内部类就像是方法里面的一个局部变量一样，是不能有 public、protected、private 以及 static 修饰符的；  
(2)只能访问方法中定义的 final 类型的局部变量,因为:  
  当方法被调用运行完毕之后，局部变量就已消亡了。但内部类对象可能还存在,直到没有被引用时才会消亡。此时就会出现一种情况，就是内部类要访问一个不存在的局部变量;  
==>使用final修饰符不仅会保持对象的引用不会改变,而且编译器还会持续维护这个对象在回调方法中的生命周期.  
局部内部类并不是直接调用方法传进来的参数，而是内部类将传进来的参数通过自己的构造器备份到了自己的内部，自己内部的方法调用的实际是自己的属性而不是外部类方法的参数;  
防止被篡改数据,而导致内部类得到的值不一致  
   /*  
使用的形参为何要为 final???  
 在内部类中的属性和外部方法的参数两者从外表上看是同一个东西，但实际上却不是，所以他们两者是可以任意变化的，也就是说在内部类中我对属性的改变并不会影响到外部的形参，而然这从程序员的角度来看这是不可行的， 毕竟站在程序的角度来看这两个根本就是同一个，如果内部类该变了，而外部方法的形参却没有改变这是难以理解和不可接受的，所以为了保持参数的一致性，就规定使用 final 来避免形参的不改变  
 */    
 ```java
public class Outer{
	public void Show(){
		final int a = 25;
		int b = 13;
	class Inner{
	int c = 2;
	public void print(){
	System.out.println("访问外部类:" + a);
	System.out.println("访问内部类:" + c);
	}
}
Inner i = new Inner();
i.print();
}
public static void main(String[] args){
  Outer o = new Outer();
   o.show();
  }
}    
```
(3).注意:在JDK8版本之中,方法内部类中调用方法中的局部变量,可以不需要修饰为 final,匿名内部类也是一样的，主要是JDK8之后增加了 Effectively final 功能

http://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html  

反编译jdk8编译之后的class文件,发现内部类引用外部的局部变量都是 final 修饰的  

**(四).匿名内部类:** 

(1).匿名内部类是直接使用 new 来生成一个对象的引用;  
(2).对于匿名内部类的使用它是存在一个缺陷的，就是它仅能被使用一次，创建匿名内部类时它会立即创建一个该类的实例,该类的定义会立即消失，所以匿名内部类是不能够被重复使用;  
(3).使用匿名内部类时，我们必须是继承一个类或者实现一个接口，但是两者不可兼得，同时也只能继承一个类或者实现一个接口;  
(4).匿名内部类中是不能定义构造函数的,匿名内部类中不能存在任何的静态成员变量和静态方法;  
(5).匿名内部类中不能存在任何的静态成员变量和静态方法,匿名内部类不能是抽象的,它必须要实现继承的类或者实现的接口的所有抽象方法  
(6).匿名内部类初始化:使用构造代码块！利用构造代码块能够达到为匿名内部类创建一个构造器的效果  
```java
  public class OuterClass {
            public InnerClass getInnerClass(final int   num,String str2){
                return new InnerClass(){
                    int number = num + 3;
                    public int getNumber(){
                        return number;
                    }
                };        /* 注意：分号不能省 */
            }
            public static void main(String[] args) {
                OuterClass out = new OuterClass();
                InnerClass inner = out.getInnerClass(2, "chenssy");
                System.out.println(inner.getNumber());
            }
        }
        interface InnerClass {
            int getNumber();
        }   

```



### JavaIO
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%209.png)
### JAVA NIO 
NIO 主要有三大核心部分：Channel(通道)，Buffer(缓冲区), Selector。传统 IO 基于字节流和字符流进行操作，而 NIO 基于 Channel 和 Buffer(缓冲区)进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。Selector(选择区)用于监听多个通道的事件（比如：连接打开,数据到达）。因此，单个线程可以监听多个数据通道。  
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%208.png)  

NIO 和传统 IO 之间第一个最大的区别是，IO 是面向流的，NIO 是面向缓冲区的。  
从一个客户端向服务端发送数据，然后服务端接收数据的过程。  
客户端发送 数据时，必须先将数据存入 Buffer 中，然后将 Buffer 中的内容写入通道。服务端这边接收数据必 须通过 Channel 将数据读入到 Buffer 中，然后再从 Buffer 中取出数据来处理。 
Selector 类是 NIO 的核心类，Selector 能够检测多个注册的通道上是否有事件发生，如果有事件发生，便获取事件然后针对每个事件进行相应的响应处理。这样一来，只是用一个单线程就可以管理多个通道，也就是管理多个连接。这样使得只有在连接真正有读写事件发生时，才会调用函数来进行读写，就大大地减少了系统开销，并且不必为每个连接都创建一个线程，不用去维护多个线程，并且避免了多线程之间的上下文切换导致的开销。

### 网络编程
### 网络通信协议 
**TCP/IP协议：** 传输控制协议/因特网互联协议( Transmission Control Protocol/Internet Protocol)，是Internet最基本、最广泛的协议。它定义了计算机如何连入因特网，以及数据如何在它们之间传输的标准。它的内部包含一系列的用于处理数据通信的协议，并采用了4层的分层模型，每一层都呼叫它的下一层所提供的协议来完成自己的需求。
**协议分类**  
**TCP：** 传输控制协议 (Transmission Control Protocol)。TCP协议是面向连接的通信协议，即传输数据之前，在发送端和接收端建立逻辑连接，然后再传输数据，它提供了两台计算机之间可靠无差错的数据传输。   
三次握手：TCP协议中，在发送数据的准备阶段，客户端与服务器之间的三次交互，以保证连接的可靠。   
第一次握手，客户端向服务器端发出连接请求，等待服务器确认。   
第二次握手，服务器端向客户端回送一个响应，通知客户端收到了连接请求。   
第三次握手，客户端再次向服务器端发送确认信息，确认连接。  
**UDP：** 用户数据报协议(User Datagram Protocol)。UDP协议是一个面向无连接的协议。传输数据时，不需要建立连接，不管对方端服务是否启动，直接将数据、数据源和目的地都封装在数据包中，直接发送。每个数据包的大小限制在64k以内。它是不可靠协议，因为无连接，所以传输速度快，但是容易丢失数据。日常应用中,例如视频会议、QQ聊天等。

**网络编程三要素**   
协议  
IP地址  
端口号   

简单的TCP网络程序 
服务端实现：  
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%2010.png)
客户端实现： 
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%2011.png)


### 反射：  
* 获取Class对象的方式：  
		1. Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
			* 多用于配置文件，将类名定义在配置文件中。读取文件，加载类
		2. 类名.class：通过类名的属性class获取
			* 多用于参数的传递
		3. 对象.getClass()：getClass()方法在Object类中定义着。
			* 多用于对象的获取字节码的方式

		* 结论：
			同一个字节码文件(*.class)在一次程序运行过程中，只会被加载一次，不论通过哪一种方式获取的Class对象都是同一个。
* Class对象功能：
		* 获取功能：
			1. 获取成员变量们
				* Field[] getFields() ：获取所有public修饰的成员变量
				* Field getField(String name)   获取指定名称的 public修饰的成员变量

				* Field[] getDeclaredFields()  获取所有的成员变量，不考虑修饰符
				* Field getDeclaredField(String name)  
			2. 获取构造方法们
				* Constructor<?>[] getConstructors()  
				* Constructor<T> getConstructor(类<?>... parameterTypes)  

				* Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)  
				* Constructor<?>[] getDeclaredConstructors()  
			3. 获取成员方法们：
				* Method[] getMethods()  
				* Method getMethod(String name, 类<?>... parameterTypes)  

				* Method[] getDeclaredMethods()  
				* Method getDeclaredMethod(String name, 类<?>... parameterTypes)  

			4. 获取全类名	
				* String getName()  

	* Field：成员变量
		* 操作：
			1. 设置值
				* void set(Object obj, Object value)  
			2. 获取值
				* get(Object obj) 

			3. 忽略访问权限修饰符的安全检查
				* setAccessible(true):暴力反射



