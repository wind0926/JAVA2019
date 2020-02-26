package base;

public class TestEquals2 {
    public static void main(String[] args) {
        String s1="abc";
        String s2=new String("abc");
        String s3="abc";
        String s4="xxx";
        String s5="abc"+"xxx";
        String s6=s3+s4;

        System.out.println(s1==s2);
        System.out.println(s1==s5);
        System.out.println(s1==s6);
/*
* 返回字符串对象的规范表示形式。
*
* 字符串池最初为空，由类字符串私下维护。
* 调用intern方法时，如果池中已包含由equals（object）方法确定的与此string对象相等的字符串，
* 则返回池中的字符串。否则此字符串对象将添加到池中，并返回对此字符串的引用。
*
* 因此对于任意两个字符串s和t,s.intern()==t.intern()在且仅当s.equals(t)为true时为true.
*
* 所有文字字符串和字符串值常量表达式都会被插入。String文字在java语言规范的3.3.5节定义。
*
* */
        System.out.println(s1==s6.intern());
        System.out.println(s2==s2.intern());
    }
}
