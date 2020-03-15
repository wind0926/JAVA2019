


# 一、数据类型
**数据类型分类:**  
Java的数据类型分为两大类：   
基本数据类型：包括 整数 、 浮点数 、 字符 、 布尔 。   
引用数据类型：包括 类 、 数组 、 接口 。   
## 基本数据类型   
四类八种基本数据类型：  
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%201.png) 

boolean 只有两个值：true、false，可以使用 1 bit 来存储，但是具体大小没有明确规定。JVM 会在编译时期将 boolean 类型的数据转换为 int，使用 1 来表示 true，0 表示 false。JVM 支持 boolean 数组，但是是通过读写 byte 数组来实现的。

**自动转换：** 将取值范围小的类型自动提升为取值范围大的类型。  
**转换规则**  
范围小的类型向范围大的类型提升， byte、short、char 运算时直接提升为 int。   
byte、short、char‐‐>int‐‐>long‐‐>float‐‐>double   
**强制转换:**   
int i = (int)1.5;  // double类型数据强制转成int类型，直接去掉小数点. 

## 包装类型

基本类型都有对应的包装类型，基本类型与其对应的包装类型之间的赋值使用自动装箱与拆箱完成。

```java
Integer x = 2;     // 装箱 调用了 Integer.valueOf(2)
int y = x;         // 拆箱 调用了 X.intValue()
```

## 缓存池

new Integer(123) 与 Integer.valueOf(123) 的区别在于：

- new Integer(123) 每次都会新建一个对象；
- Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。

```java
Integer x = new Integer(123);
Integer y = new Integer(123);
System.out.println(x == y);    // false
Integer z = Integer.valueOf(123);
Integer k = Integer.valueOf(123);
System.out.println(z == k);   // true
```

valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

在 Java 8 中，Integer 缓存池的大小默认为 -128\~127。

```java
static final int low = -128;
static final int high;
static final Integer cache[];

static {
    // high value may be configured by property
    int h = 127;
    String integerCacheHighPropValue =
        sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
    if (integerCacheHighPropValue != null) {
        try {
            int i = parseInt(integerCacheHighPropValue);
            i = Math.max(i, 127);
            // Maximum array size is Integer.MAX_VALUE
            h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
        } catch( NumberFormatException nfe) {
            // If the property cannot be parsed into an int, ignore it.
        }
    }
    high = h;

    cache = new Integer[(high - low) + 1];
    int j = low;
    for(int k = 0; k < cache.length; k++)
        cache[k] = new Integer(j++);

    // range [-128, 127] must be interned (JLS7 5.1.7)
    assert IntegerCache.high >= 127;
}
```

编译器会在自动装箱过程调用 valueOf() 方法，因此多个值相同且值在缓存池范围内的 Integer 实例使用自动装箱来创建，那么就会引用相同的对象。

```java
Integer m = 123;
Integer n = 123;
System.out.println(m == n); // true
```

基本类型对应的缓冲池如下：

- boolean values true and false
- all byte values
- short values between -128 and 127
- int values between -128 and 127
- char in the range \u0000 to \u007F

在使用这些基本类型对应的包装类型时，如果该数值范围在缓冲池范围内，就可以直接使用缓冲池中的对象。

在 jdk 1.8 所有的数值类缓冲池中，Integer 的缓冲池 IntegerCache 很特殊，这个缓冲池的下界是 - 128，上界默认是 127，但是这个上界是可调的，在启动 jvm 的时候，通过 -XX:AutoBoxCacheMax=&lt;size&gt; 来指定这个缓冲池的大小，该选项在 JVM 初始化的时候会设定一个名为 java.lang.IntegerCache.high 系统属性，然后 IntegerCache 初始化的时候就会读取该系统属性来决定上界。


# 二、String

## 概览

String 被声明为 final，因此它不可被继承。(Integer 等包装类也不能被继承）

在 Java 8 中，String 内部使用 char 数组存储数据。

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];
}
```

在 Java 9 之后，String 类的实现改用 byte 数组存储字符串，同时使用 `coder` 来标识使用了哪种编码。

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final byte[] value;

    /** The identifier of the encoding used to encode the bytes in {@code value}. */
    private final byte coder;
}
```

value 数组被声明为 final，这意味着 value 数组初始化之后就不能再引用其它数组。并且 String 内部没有改变 value 数组的方法，因此可以保证 String 不可变。

## 不可变的好处

**1. 可以缓存 hash 值**  

因为 String 的 hash 值经常被使用，例如 String 用做 HashMap 的 key。不可变的特性可以使得 hash 值也不可变，因此只需要进行一次计算。

**2. String Pool 的需要**  

如果一个 String 对象已经被创建过了，那么就会从 String Pool 中取得引用。只有 String 是不可变的，才可能使用 String Pool。

<div align="center"> <img src="https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/string%E6%B1%A0.png"/> </div><br>

**3. 安全性**  

String 经常作为参数，String 不可变性可以保证参数不可变。例如在作为网络连接参数的情况下如果 String 是可变的，那么在网络连接过程中，String 被改变，改变 String 的那一方以为现在连接的是其它主机，而实际情况却不一定是。

**4. 线程安全**  

String 不可变性天生具备线程安全，可以在多个线程中安全地使用。


## String, StringBuffer and StringBuilder

**1. 可变性**  

- String 不可变
- StringBuffer 和 StringBuilder 可变

**2. 线程安全**  

- String 不可变，因此是线程安全的
- StringBuilder 不是线程安全的
- StringBuffer 是线程安全的，内部使用 synchronized 进行同步


StringBuffer是线程安全的，支持并发操作，支持多线程；  
StringBuilder是线程不安全的，不支持并发操作，支持单线程。    
StringBuffer继承抽象类AbstractStringBuilder，StringBuffer可以给指定对象的实体初始化容量字符串长度再另外加16个字符串，
super(str.length+16),这是自动扩容，还有就是扩容算法：使用append（）方法在字符串后面追加东西的时候，如果超过了字符串存储空间大小就需要进行扩容，
传入一个方法：ensureCapacityInternal进行是否扩容判断，需要扩容就调用expendCapacity方法进行扩容。  

StringBuffer类可以创建可修改的字符串序列。该类有StringBuffer(),StringBuffer(int size),StringBuffer(String s)三个改造方法。

1.StringBuffer()的初始容量可以容纳16个字符，当该对象的实体存放的字符的长度大于16时，实体容量就自动增加。StringBuffer对象可以通过length()方法获取实体中存放的字符序列长度，通过capacity()方法来获取当前实体的实际容量。

2.StringBuffer(int size)可以指定分配给该对象的实体的初始容量参数为参数size指定的字符个数。当该对象的实体存放的字符序列的长度大于size个字符时，实体的容量就自动的增加。以便存放所增加的字符。 

3.StringBuffer(String s)可以指定给对象的实体的初始容量为参数字符串s的长度额外再加16个字符。当该对象的实体存放的字符序列长度大于size个字符时，实体的容量自动的增加，以便存放所增加的字符。 

接下来介绍StringBuffer()怎样扩容： 

首先我们需要知道StringBuffer和StringBuilder类都继承了抽象类AbstractStringBuilder类； 
源码都调用父类来进行初始化：
<div align="center"> <img src="https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/stringbuffer1.png"/> </div><br>

StringBuffer(String s)的初始化：
<div align="center"> <img src="https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/strignbuffer2.png"/> </div><br>

扩容算法：
使用append()方法在字符串后面追加东西的时候，如果长度超过了该字符串存储空间大小了就需要进行扩容：构建新的存储空间更大的字符串，将旧的复制过去；
 
再进行字符串append添加的时候，会先计算添加后字符串大小，传入一个方法：ensureCapacityInternal 这个方法进行是否扩容的判断，需要扩容就调用expandCapacity方法进行扩容：
<div align="center"> <img src="https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/strignbuffer3.png"/> </div><br>

尝试将新容量扩为大小变成2倍+2   if 判断一下 容量如果不够，直接扩充到需要的容量大小。




## String Pool

字符串常量池（String Pool）保存着所有字符串字面量（literal strings），这些字面量在编译时期就确定。不仅如此，还可以使用 String 的 intern() 方法在运行过程将字符串添加到 String Pool 中。

当一个字符串调用 intern() 方法时，如果 String Pool 中已经存在一个字符串和该字符串值相等（使用 equals() 方法进行确定），那么就会返回 String Pool 中字符串的引用；否则，就会在 String Pool 中添加一个新的字符串，并返回这个新字符串的引用。

下面示例中，s1 和 s2 采用 new String() 的方式新建了两个不同字符串，而 s3 和 s4 是通过 s1.intern() 方法取得同一个字符串引用。intern() 首先把 s1 引用的字符串放到 String Pool 中，然后返回这个字符串引用。因此 s3 和 s4 引用的是同一个字符串。

```java
String s1 = new String("aaa");
String s2 = new String("aaa");
System.out.println(s1 == s2);           // false
String s3 = s1.intern();
String s4 = s1.intern();
System.out.println(s3 == s4);           // true
```

如果是采用 "bbb" 这种字面量的形式创建字符串，会自动地将字符串放入 String Pool 中。

```java
String s5 = "bbb";
String s6 = "bbb";
System.out.println(s5 == s6);  // true
```

在 Java 7 之前，String Pool 被放在运行时常量池中，它属于永久代。而在 Java 7，String Pool 被移到堆中。这是因为永久代的空间有限，在大量使用字符串的场景下会导致 OutOfMemoryError 错误。


## new String("abc")

使用这种方式一共会创建两个字符串对象（前提是 String Pool 中还没有 "abc" 字符串对象）。

- "abc" 属于字符串字面量，因此编译时期会在 String Pool 中创建一个字符串对象，指向这个 "abc" 字符串字面量；
- 而使用 new 的方式会在堆中创建一个字符串对象。

创建一个测试类，其 main 方法中使用这种方式来创建字符串对象。

```java
public class NewStringTest {
    public static void main(String[] args) {
        String s = new String("abc");
    }
}
```

使用 javap -verbose 进行反编译，得到以下内容：

```java
// ...
Constant pool:
// ...
   #2 = Class              #18            // java/lang/String
   #3 = String             #19            // abc
// ...
  #18 = Utf8               java/lang/String
  #19 = Utf8               abc
// ...

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=2, args_size=1
         0: new           #2                  // class java/lang/String
         3: dup
         4: ldc           #3                  // String abc
         6: invokespecial #4                  // Method java/lang/String."<init>":(Ljava/lang/String;)V
         9: astore_1
// ...
```

在 Constant Pool 中，#19 存储这字符串字面量 "abc"，#3 是 String Pool 的字符串对象，它指向 #19 这个字符串字面量。在 main 方法中，0: 行使用 new #2 在堆中创建一个字符串对象，并且使用 ldc #3 将 String Pool 中的字符串对象作为 String 构造函数的参数。

以下是 String 构造函数的源码，可以看到，在将一个字符串对象作为另一个字符串对象的构造函数参数时，并不会完全复制 value 数组内容，而是都会指向同一个 value 数组。

```java
public String(String original) {
    this.value = original.value;
    this.hash = original.hash;
}
```

# 三、运算

## 参数传递

Java 的参数是以值传递的形式传入方法中，而不是引用传递。

java中只有值传递，没有引用传递  
 1.方法内如果传基本类型，传的是复印件，原件不动  
 2.方法内如果传引用类型，传递的是引用（地址）  
main方法在Java栈中，先进入栈底  

```java 
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
```
```java
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
```



## float 与 double

Java 不能隐式执行向下转型，因为这会使得精度降低。

1.1 字面量属于 double 类型，不能直接将 1.1 直接赋值给 float 变量，因为这是向下转型。

```java
// float f = 1.1;
```

1.1f 字面量才是 float 类型。

```java
float f = 1.1f;
```

## 隐式类型转换

因为字面量 1 是 int 类型，它比 short 类型精度要高，因此不能隐式地将 int 类型向下转型为 short 类型。

```java
short s1 = 1;
// s1 = s1 + 1;
```

但是使用 += 或者 ++ 运算符会执行隐式类型转换。

```java
s1 += 1;
s1++;
```

上面的语句相当于将 s1 + 1 的计算结果进行了向下转型：

```java
s1 = (short) (s1 + 1);
```


## switch

从 Java 7 开始，可以在 switch 条件判断语句中使用 String 对象。

```java
String s = "a";
switch (s) {
    case "a":
        System.out.println("aaa");
        break;
    case "b":
        System.out.println("bbb");
        break;
}
```

switch 不支持 long，是因为 switch 的设计初衷是对那些只有少数几个值的类型进行等值判断，如果值过于复杂，那么还是用 if 比较合适。




# 四、关键字

## final

1.当用final去修饰一个类的时候，表示这个类不能被继承。  
2.被final修饰的方法不能被重写.  
  **注意：**  
  a. 一个类的private方法会隐式的被指定为final方法。
  b. 如果父类中有final修饰的方法，那么子类不能去重写。  
3.修饰成员变量   
  **注意：**   
  a. 必须要赋初始值，而且是只能初始化一次。  
  b. 被fianl修饰的成员变量赋值，有两种方式：1、直接赋值 2、全部在构造方法中赋初值。  
  c. 如果修饰的成员变量是基本类型，则表示这个变量的值不能改变。  
  d. 如果修饰的成员变量是一个引用类型，则是说这个引用的地址的值不能修改，但是这个引用所指	向的对象里面的内容还是可以改变的。  
。

## static

**1. 静态变量**  

- 静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它。静态变量在内存中只存在一份。
- 实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。

```java
public class A {

    private int x;         // 实例变量
    private static int y;  // 静态变量

    public static void main(String[] args) {
        // int x = A.x;  // Non-static field 'x' cannot be referenced from a static context
        A a = new A();
        int x = a.x;
        int y = A.y;
    }
}
```

**2. 静态方法**  

静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。

```java
public abstract class A {
    public static void func1(){
    }
    // public abstract static void func2();  // Illegal combination of modifiers: 'abstract' and 'static'
}
```

只能访问所属类的静态字段和静态方法，方法中不能有 this 和 super 关键字，因此这两个关键字与具体对象关联。

```java
public class A {

    private static int x;
    private int y;

    public static void func1(){
        int a = x;
        // int b = y;  // Non-static field 'y' cannot be referenced from a static context
        // int b = this.y;     // 'A.this' cannot be referenced from a static context
    }
}
```

**3. 静态语句块**  

静态语句块在类初始化时运行一次。

```java
public class A {
    static {
        System.out.println("123");
    }

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
    }
}
```

```html
123
```

**4. 静态内部类**  

非静态内部类依赖于外部类的实例，也就是说需要先创建外部类实例，才能用这个实例去创建非静态内部类。而静态内部类不需要。

```java
public class OuterClass {

    class InnerClass {
    }

    static class StaticInnerClass {
    }

    public static void main(String[] args) {
        // InnerClass innerClass = new InnerClass(); // 'OuterClass.this' cannot be referenced from a static context
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}
```

静态内部类不能访问外部类的非静态的变量和方法。

**5. 静态导包**  

在使用静态变量和方法时不用再指明 ClassName，从而简化代码，但可读性大大降低。

```java
import static com.xxx.ClassName.*
```

**6. 初始化顺序**  

静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。

```java
public static String staticField = "静态变量";
```

```java
static {
    System.out.println("静态语句块");
}
```

```java
public String field = "实例变量";
```

```java
{
    System.out.println("普通语句块");
}
```

最后才是构造函数的初始化。

```java
public InitialOrderTest() {
    System.out.println("构造函数");
}
```

存在继承的情况下，初始化顺序为：

- 父类（静态变量、静态语句块）
- 子类（静态变量、静态语句块）
- 父类（实例变量、普通语句块）
- 父类（构造函数）
- 子类（实例变量、普通语句块）
- 子类（构造函数）

# 五、Object 通用方法

## 概览

```java

public native int hashCode()

public boolean equals(Object obj)

protected native Object clone() throws CloneNotSupportedException

public String toString()

public final native Class<?> getClass()

protected void finalize() throws Throwable {}

public final native void notify()

public final native void notifyAll()

public final native void wait(long timeout) throws InterruptedException

public final void wait(long timeout, int nanos) throws InterruptedException

public final void wait() throws InterruptedException
```

## equals()

**1. 等价关系**  

两个对象具有等价关系，需要满足以下五个条件：

Ⅰ 自反性

```java
x.equals(x); // true
```

Ⅱ 对称性

```java
x.equals(y) == y.equals(x); // true
```

Ⅲ 传递性

```java
if (x.equals(y) && y.equals(z))
    x.equals(z); // true;
```

Ⅳ 一致性

多次调用 equals() 方法结果不变

```java
x.equals(y) == x.equals(y); // true
```

Ⅴ 与 null 的比较

对任何不是 null 的对象 x 调用 x.equals(null) 结果都为 false

```java
x.equals(null); // false;
```

**2. 等价与相等**  

- 对于基本类型，== 判断两个值是否相等，基本类型没有 equals() 方法。
- 对于引用类型，== 判断两个变量是否引用同一个对象，而 equals() 判断引用的对象是否等价。

```java
Integer x = new Integer(1);
Integer y = new Integer(1);
System.out.println(x.equals(y)); // true
System.out.println(x == y);      // false
```

**3. 实现**  

- 检查是否为同一个对象的引用，如果是直接返回 true；
- 检查是否是同一个类型，如果不是，直接返回 false；
- 将 Object 对象进行转型；
- 判断每个关键域是否相等。

```java
public class EqualExample {

    private int x;
    private int y;
    private int z;

    public EqualExample(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EqualExample that = (EqualExample) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return z == that.z;
    }
}
```

## hashCode()

hashCode() 返回哈希值，而 equals() 是用来判断两个对象是否等价。等价的两个对象散列值一定相同，但是散列值相同的两个对象不一定等价，这是因为计算哈希值具有随机性，两个值不同的对象可能计算出相同的哈希值。

在覆盖 equals() 方法时应当总是覆盖 hashCode() 方法，保证等价的两个对象哈希值也相等。

HashSet  和 HashMap 等集合类使用了 hashCode()  方法来计算对象应该存储的位置，因此要将对象添加到这些集合类中，需要让对应的类实现 hashCode()  方法。

下面的代码中，新建了两个等价的对象，并将它们添加到 HashSet 中。我们希望将这两个对象当成一样的，只在集合中添加一个对象。但是 EqualExample 没有实现 hashCode() 方法，因此这两个对象的哈希值是不同的，最终导致集合添加了两个等价的对象。

```java
EqualExample e1 = new EqualExample(1, 1, 1);
EqualExample e2 = new EqualExample(1, 1, 1);
System.out.println(e1.equals(e2)); // true
HashSet<EqualExample> set = new HashSet<>();
set.add(e1);
set.add(e2);
System.out.println(set.size());   // 2
```

理想的哈希函数应当具有均匀性，即不相等的对象应当均匀分布到所有可能的哈希值上。这就要求了哈希函数要把所有域的值都考虑进来。可以将每个域都当成 R 进制的某一位，然后组成一个 R 进制的整数。

R 一般取 31，因为它是一个奇素数，如果是偶数的话，当出现乘法溢出，信息就会丢失，因为与 2 相乘相当于向左移一位，最左边的位丢失。并且一个数与 31 相乘可以转换成移位和减法：`31*x == (x<<5)-x`，编译器会自动进行这个优化。

```java
@Override
public int hashCode() {
    int result = 17;
    result = 31 * result + x;
    result = 31 * result + y;
    result = 31 * result + z;
    return result;
}
```

## toString()

默认返回 ToStringExample@4554617c 这种形式，其中 @ 后面的数值为散列码的无符号十六进制表示。

```java
public class ToStringExample {

    private int number;

    public ToStringExample(int number) {
        this.number = number;
    }
}
```

```java
ToStringExample example = new ToStringExample(123);
System.out.println(example.toString());
```

```html
ToStringExample@4554617c
```

## clone()

**1. cloneable**  

clone() 是 Object 的 protected 方法，它不是 public，一个类不显式去重写 clone()，其它类就不能直接去调用该类实例的 clone() 方法。

```java
public class CloneExample {
    private int a;
    private int b;
}
```

```java
CloneExample e1 = new CloneExample();
// CloneExample e2 = e1.clone(); // 'clone()' has protected access in 'java.lang.Object'
```

重写 clone() 得到以下实现：

```java
public class CloneExample {
    private int a;
    private int b;

    @Override
    public CloneExample clone() throws CloneNotSupportedException {
        return (CloneExample)super.clone();
    }
}
```

```java
CloneExample e1 = new CloneExample();
try {
    CloneExample e2 = e1.clone();
} catch (CloneNotSupportedException e) {
    e.printStackTrace();
}
```

```html
java.lang.CloneNotSupportedException: CloneExample
```

以上抛出了 CloneNotSupportedException，这是因为 CloneExample 没有实现 Cloneable 接口。

应该注意的是，clone() 方法并不是 Cloneable 接口的方法，而是 Object 的一个 protected 方法。Cloneable 接口只是规定，如果一个类没有实现 Cloneable 接口又调用了 clone() 方法，就会抛出 CloneNotSupportedException。

```java
public class CloneExample implements Cloneable {
    private int a;
    private int b;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```

**2. 浅拷贝**  

拷贝对象和原始对象的引用类型引用同一个对象。

```java
public class ShallowCloneExample implements Cloneable {

    private int[] arr;

    public ShallowCloneExample() {
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }

    @Override
    protected ShallowCloneExample clone() throws CloneNotSupportedException {
        return (ShallowCloneExample) super.clone();
    }
}
```

```java
ShallowCloneExample e1 = new ShallowCloneExample();
ShallowCloneExample e2 = null;
try {
    e2 = e1.clone();
} catch (CloneNotSupportedException e) {
    e.printStackTrace();
}
e1.set(2, 222);
System.out.println(e2.get(2)); // 222
```

**3. 深拷贝**  

拷贝对象和原始对象的引用类型引用不同对象。

```java
public class DeepCloneExample implements Cloneable {

    private int[] arr;

    public DeepCloneExample() {
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }

    @Override
    protected DeepCloneExample clone() throws CloneNotSupportedException {
        DeepCloneExample result = (DeepCloneExample) super.clone();
        result.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result.arr[i] = arr[i];
        }
        return result;
    }
}
```

```java
DeepCloneExample e1 = new DeepCloneExample();
DeepCloneExample e2 = null;
try {
    e2 = e1.clone();
} catch (CloneNotSupportedException e) {
    e.printStackTrace();
}
e1.set(2, 222);
System.out.println(e2.get(2)); // 2
```

**4. clone() 的替代方案**  

使用 clone() 方法来拷贝一个对象即复杂又有风险，它会抛出异常，并且还需要类型转换。Effective Java 书上讲到，最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。

```java
public class CloneConstructorExample {

    private int[] arr;

    public CloneConstructorExample() {
        arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public CloneConstructorExample(CloneConstructorExample original) {
        arr = new int[original.arr.length];
        for (int i = 0; i < original.arr.length; i++) {
            arr[i] = original.arr[i];
        }
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int get(int index) {
        return arr[index];
    }
}
```

```java
CloneConstructorExample e1 = new CloneConstructorExample();
CloneConstructorExample e2 = new CloneConstructorExample(e1);
e1.set(2, 222);
System.out.println(e2.get(2)); // 2
```

# 六、继承

## 访问权限
![](https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/%E5%9B%BE%E7%89%87%203.png)



## 抽象类与接口

**1. 抽象类**  

抽象类和抽象方法都使用 abstract 关键字进行声明。如果一个类中包含抽象方法，那么这个类必须声明为抽象类。

抽象类和普通类最大的区别是，抽象类不能被实例化，只能被继承。

```java
public abstract class AbstractClassExample {

    protected int x;
    private int y;

    public abstract void func1();

    public void func2() {
        System.out.println("func2");
    }
}
```

```java
public class AbstractExtendClassExample extends AbstractClassExample {
    @Override
    public void func1() {
        System.out.println("func1");
    }
}
```

```java
// AbstractClassExample ac1 = new AbstractClassExample(); // 'AbstractClassExample' is abstract; cannot be instantiated
AbstractClassExample ac2 = new AbstractExtendClassExample();
ac2.func1();
```

**2. 接口**  

接口是抽象类的延伸，在 Java 8 之前，它可以看成是一个完全抽象的类，也就是说它不能有任何的方法实现。

从 Java 8 开始，接口也可以拥有默认的方法实现，这是因为不支持默认方法的接口的维护成本太高了。在 Java 8 之前，如果一个接口想要添加新的方法，那么要修改所有实现了该接口的类，让它们都实现新增的方法。

接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。

接口的字段默认都是 static 和 final 的。

```java
public interface InterfaceExample {

    void func1();

    default void func2(){
        System.out.println("func2");
    }

    int x = 123;
    // int y;               // Variable 'y' might not have been initialized
    public int z = 0;       // Modifier 'public' is redundant for interface fields
    // private int k = 0;   // Modifier 'private' not allowed here
    // protected int l = 0; // Modifier 'protected' not allowed here
    // private void fun3(); // Modifier 'private' not allowed here
}
```

```java
public class InterfaceImplementExample implements InterfaceExample {
    @Override
    public void func1() {
        System.out.println("func1");
    }
}
```

```java
// InterfaceExample ie1 = new InterfaceExample(); // 'InterfaceExample' is abstract; cannot be instantiated
InterfaceExample ie2 = new InterfaceImplementExample();
ie2.func1();
System.out.println(InterfaceExample.x);
```

**3. 比较**  
1.抽象类可以没有抽象方法，但接口中的方法必须是抽象方法；    
2.抽象类中可以存在普通属性，方法，静态属性和方法；    
3.接口中的变量必须是static final类型的，必须被初始化，只有常亮没有变量；    
4.抽象类只能单继承，接口可以继承多个父接口。

**4. 使用选择**  

如果准备为不相关的类创建一些提供共有功能的东西，请使用接口。  
抽象类允许你为子类提供默认功能。  
当创建一个可以被随意更改的独立项目时，使用优先于抽象类的接口，因为它提供更多的设计灵活性。  
使用接口来引入多态行为而无需子类化并模拟多重继承 - 允许特定类型支持多种行为。

在很多情况下，接口优先于抽象类。因为接口没有抽象类严格的类层次结构要求，可以灵活地为一个类添加行为。并且从 Java 8 开始，接口也可以有默认的方法实现，使得修改接口的成本也变的很低。


## super

- 访问父类的构造函数：可以使用 super() 函数访问父类的构造函数，从而委托父类完成一些初始化的工作。应该注意到，子类一定会调用父类的构造函数来完成初始化工作，一般是调用父类的默认构造函数，如果子类需要调用父类其它构造函数，那么就可以使用 super() 函数。
- 访问父类的成员：如果子类重写了父类的某个方法，可以通过使用 super 关键字来引用父类的方法实现。

```java
public class SuperExample {

    protected int x;
    protected int y;

    public SuperExample(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void func() {
        System.out.println("SuperExample.func()");
    }
}
```

```java
public class SuperExtendExample extends SuperExample {

    private int z;

    public SuperExtendExample(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public void func() {
        super.func();
        System.out.println("SuperExtendExample.func()");
    }
}
```

```java
SuperExample e = new SuperExtendExample(1, 2, 3);
e.func();
```

```html
SuperExample.func()
SuperExtendExample.func()
```


## 重写与重载

**1. 重写（Override）**  

存在于继承体系中，指子类实现了一个与父类在方法声明上完全相同的一个方法。

为了满足里式替换原则，重写有以下三个限制：

- 子类方法的访问权限必须大于等于父类方法；
- 子类方法的返回类型必须是父类方法返回类型或为其子类型。
- 子类方法抛出的异常类型必须是父类抛出异常类型或为其子类型。

使用 @Override 注解，可以让编译器帮忙检查是否满足上面的三个限制条件。

下面的示例中，SubClass 为 SuperClass 的子类，SubClass 重写了 SuperClass 的 func() 方法。其中：

- 子类方法访问权限为 public，大于父类的 protected。
- 子类的返回类型为 ArrayList<Integer>，是父类返回类型 List<Integer> 的子类。
- 子类抛出的异常类型为 Exception，是父类抛出异常 Throwable 的子类。
- 子类重写方法使用 @Override 注解，从而让编译器自动检查是否满足限制条件。

```java
class SuperClass {
    protected List<Integer> func() throws Throwable {
        return new ArrayList<>();
    }
}

class SubClass extends SuperClass {
    @Override
    public ArrayList<Integer> func() throws Exception {
        return new ArrayList<>();
    }
}
```

在调用一个方法时，先从本类中查找看是否有对应的方法，如果没有再到父类中查看，看是否从父类继承来。否则就要对参数进行转型，转成父类之后看是否有对应的方法。总的来说，方法调用的优先级为：

- this.func(this)
- super.func(this)
- this.func(super)
- super.func(super)


```java
/*
    A
    |
    B
    |
    C
    |
    D
 */


class A {

    public void show(A obj) {
        System.out.println("A.show(A)");
    }

    public void show(C obj) {
        System.out.println("A.show(C)");
    }
}

class B extends A {

    @Override
    public void show(A obj) {
        System.out.println("B.show(A)");
    }
}

class C extends B {
}

class D extends C {
}
```

```java
public static void main(String[] args) {

    A a = new A();
    B b = new B();
    C c = new C();
    D d = new D();

    // 在 A 中存在 show(A obj)，直接调用
    a.show(a); // A.show(A)
    // 在 A 中不存在 show(B obj)，将 B 转型成其父类 A
    a.show(b); // A.show(A)
    // 在 B 中存在从 A 继承来的 show(C obj)，直接调用
    b.show(c); // A.show(C)
    // 在 B 中不存在 show(D obj)，但是存在从 A 继承来的 show(C obj)，将 D 转型成其父类 C
    b.show(d); // A.show(C)

    // 引用的还是 B 对象，所以 ba 和 b 的调用结果一样
    A ba = new B();
    ba.show(c); // A.show(C)
    ba.show(d); // A.show(C)
}
```

**2. 重载（Overload）**  

存在于同一个类中，指一个方法与已经存在的方法名称上相同，但是参数类型、个数、顺序至少有一个不同。

应该注意的是，返回值不同，其它都相同不算是重载。


# 七、反射

每个类都有一个   **Class**   对象，包含了与类有关的信息。当编译一个新类时，会产生一个同名的 .class 文件，该文件内容保存着 Class 对象。

类加载相当于 Class 对象的加载，类在第一次使用时才动态加载到 JVM 中。也可以使用 `Class.forName("com.mysql.jdbc.Driver")` 这种方式来控制类的加载，该方法会返回一个 Class 对象。

反射可以提供运行时的类信息，并且这个类可以在运行时才加载进来，甚至在编译时期该类的 .class 不存在也可以加载进来。

Class 和 java.lang.reflect 一起对反射提供了支持，java.lang.reflect 类库主要包含了以下三个类：

-   **Field**  ：可以使用 get() 和 set() 方法读取和修改 Field 对象关联的字段；
-   **Method**  ：可以使用 invoke() 方法调用与 Method 对象关联的方法；
-   **Constructor**  ：可以用 Constructor 的 newInstance() 创建新的对象。

**反射的优点：**  

*     **可扩展性**   ：应用程序可以利用全限定名创建可扩展对象的实例，来使用来自外部的用户自定义类。
*     **类浏览器和可视化开发环境**   ：一个类浏览器需要可以枚举类的成员。可视化开发环境（如 IDE）可以从利用反射中可用的类型信息中受益，以帮助程序员编写正确的代码。
*     **调试器和测试工具**   ： 调试器需要能够检查一个类里的私有成员。测试工具可以利用反射来自动地调用类里定义的可被发现的 API 定义，以确保一组测试中有较高的代码覆盖率。

**反射的缺点：**  

尽管反射非常强大，但也不能滥用。如果一个功能可以不用反射完成，那么最好就不用。在我们使用反射技术时，下面几条内容应该牢记于心。

*     **性能开销**   ：反射涉及了动态类型的解析，所以 JVM 无法对这些代码进行优化。因此，反射操作的效率要比那些非反射操作低得多。我们应该避免在经常被执行的代码或对性能要求很高的程序中使用反射。

*     **安全限制**   ：使用反射技术要求程序必须在一个没有安全限制的环境中运行。如果一个程序必须在有安全限制的环境中运行，如 Applet，那么这就是个问题了。

*     **内部暴露**   ：由于反射允许代码执行一些在正常情况下不被允许的操作（比如访问私有的属性和方法），所以使用反射可能会导致意料之外的副作用，这可能导致代码功能失调并破坏可移植性。反射代码破坏了抽象性，因此当平台发生改变的时候，代码的行为就有可能也随着变化。


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


# 八、异常

Throwable 可以用来表示任何可以作为异常抛出的类，分为两种：  **Error**   和 **Exception**。其中 Error 用来表示 JVM 无法处理的错误，Exception 分为两种：

-   **受检异常**  ：需要用 try...catch... 语句捕获并进行处理，并且可以从异常中恢复；
-   **非受检异常**  ：是程序运行时错误，例如除 0 会引发 Arithmetic Exception，此时程序崩溃并且无法恢复。

<div align="center"> <img src="https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/%E5%BC%82%E5%B8%B81.png"/> </div><br>

<div align="center"> <img src="https://github.com/wind0926/JAVA2019/blob/master/image/Java%E5%9F%BA%E7%A1%80/GitHub%E5%9B%BE%E7%89%87/%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86.png"/> </div><br>



# 九、泛型

1. Java中的泛型是什么 ? 使用泛型的好处是什么?  

　　这是在各种Java泛型面试中，参数化类型，一开场你就会被问到的问题中的一个，主要集中在初级和中级面试中。那些拥有Java1.4或更早版本的开发背景的人都知道，在集合中存储对象并在使用前进行类型转换是多么的不方便。泛型防止了那种情况的发生。它提供了编译期的类型安全，确保你只能把正确类型的对象放入集合中，避免了在运行时出现ClassCastException。

2. Java的泛型是如何工作的 ? 什么是类型擦除 ?  

　　这是一道更好的泛型面试题。泛型是通过类型擦除来实现的，编译器在编译时擦除了所有类型相关的信息，所以在运行时不存在任何类型相关的信息。例如List<String>在运行时仅用一个List来表示。这样做的目的，是确保能和Java 5之前的版本开发二进制类库进行兼容。你无法在运行时访问到类型参数，因为编译器已经把泛型类型转换成了原始类型。根据你对这个泛型问题的回答情况，你会得到一些后续提问，比如为什么泛型是由类型擦除来实现的或者给你展示一些会导致编译器出错的错误泛型代码。请阅读我的Java中泛型是如何工作的来了解更多信息。

3. 什么是泛型中的限定通配符和非限定通配符 ?  

　　这是另一个非常流行的Java泛型面试题。限定通配符对类型进行了限制。有两种限定通配符，一种是<? extends T>它通过确保类型必须是T的子类来设定类型的上界，另一种是<? super T>它通过确保类型必须是T的父类来设定类型的下界。泛型类型必须用限定内的类型来进行初始化，否则会导致编译错误。另一方面<?>表示了非限定通配符，因为<?>可以用任意类型来替代。更多信息请参阅我的文章泛型中限定通配符和非限定通配符之间的区别。

4. List<? extends T>和List <? super T>之间有什么区别 ?  

　　这和上一个面试题有联系，有时面试官会用这个问题来评估你对泛型的理解，而不是直接问你什么是限定通配符和非限定通配符。这两个List的声明都是限定通配符的例子，List<? extends T>可以接受任何继承自T的类型的List，而List<? super T>可以接受任何T的父类构成的List。例如List<? extends Number>可以接受List<Integer>或List<Float>。在本段出现的连接中可以找到更多信息。


# 十、注解

Java 注解是附加在代码中的一些元信息，用于一些工具在编译、运行时进行解析和使用，起到说明、配置的功能。注解不会也不能影响代码的实际逻辑，仅仅起到辅助性的作用。



# 十一、特性

## Java 各版本的新特性

**New highlights in Java SE 8**  

1. Lambda Expressions
2. Pipelines and Streams
3. Date and Time API
4. Default Methods
5. Type Annotations
6. Nashhorn JavaScript Engine
7. Concurrent Accumulators
8. Parallel operations
9. PermGen Error Removed

**New highlights in Java SE 7**  

1. Strings in Switch Statement
2. Type Inference for Generic Instance Creation
3. Multiple Exception Handling
4. Support for Dynamic Languages
5. Try with Resources
6. Java nio Package
7. Binary Literals, Underscore in literals
8. Diamond Syntax

- [Difference between Java 1.8 and Java 1.7?](http://www.selfgrowth.com/articles/difference-between-java-18-and-java-17)
- [Java 8 特性](http://www.importnew.com/19345.html)

## Java 与 C++ 的区别

- Java 是纯粹的面向对象语言，所有的对象都继承自 java.lang.Object，C++ 为了兼容 C 即支持面向对象也支持面向过程。
- Java 通过虚拟机从而实现跨平台特性，但是 C++ 依赖于特定的平台。
- Java 没有指针，它的引用可以理解为安全指针，而 C++ 具有和 C 一样的指针。
- Java 支持自动垃圾回收，而 C++ 需要手动回收。
- Java 不支持多重继承，只能通过实现多个接口来达到相同目的，而 C++ 支持多重继承。
- Java 不支持操作符重载，虽然可以对两个 String 对象执行加法运算，但是这是语言内置支持的操作，不属于操作符重载，而 C++ 可以。
- Java 的 goto 是保留字，但是不可用，C++ 可以使用 goto。


## JRE or JDK

- JRE：Java Runtime Environment，java运行环境的简称，为java的运行提供了所需的环境。主要包括了JVM的标准实现和一些java基本类库。
- JDK：Java Development Kit，java开发工具包，提供了java的开发及运行环境。JDK是java开发的核心，集成了JRE以及一些其他的工具，比如编译 java 源码的编译器 javac等。
- 因此可以这样认为：JDK>JRE>JVM，JRE支持了java程序的运行，而JDK则同时支持了java程序的开发。

# 十二、标识符
标识符可以包含 英文字母26个(区分大小写) 、 0-9数字 、 $（美元符号） 和 _（下划线） 。   
标识符不能以数字开头。   
标识符不能是关键字。

# 十三、编码
**Java中汉字占几个字节？**    
可能占2、3、4个字节，看编码格式  

```java
public class UtfTest {
    public static void main(String[] args) throws Exception {
        // 第二个字符为BMP之外的字符，csdn编辑器无法显示该字符，可以在运行结果截图中看到
        String[] strArr = {"中", "鼍", "a", "aa"};
        String[] charsetArr = {"gbk", "utf-8", "utf-16", "gb2312"};
        for(String str : strArr) {
            System.out.println(str);
            for(String charset : charsetArr) {
                byteTest(str, charset);
            }
            System.out.println("============================");
        }

    }
    public static void byteTest(String str, String charset) throws Exception {
        System.out.println("编码：" + charset
                + "\t所占字节数：" + str.getBytes(charset).length);
    }

}
```
```java
/*
* 中
编码：gbk	所占字节数：2
编码：utf-8	所占字节数：3
编码：utf-16	所占字节数：4
编码：gb2312	所占字节数：2
============================
鼍
编码：gbk	所占字节数：2
编码：utf-8	所占字节数：3
编码：utf-16	所占字节数：4
编码：gb2312	所占字节数：2
============================
a
编码：gbk	所占字节数：1
编码：utf-8	所占字节数：1
编码：utf-16	所占字节数：4
编码：gb2312	所占字节数：1
============================
aa
编码：gbk	所占字节数：2
编码：utf-8	所占字节数：2
编码：utf-16	所占字节数：6
编码：gb2312	所占字节数：2
============================

* */
```

# 内部类
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









# 参考资料

- https://github.com/CyC2018/CS-Notes/
- https://www.nowcoder.com/

