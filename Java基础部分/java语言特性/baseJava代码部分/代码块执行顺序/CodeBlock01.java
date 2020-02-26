package base;
/*
* 普通代码块：在方法或语句中出现{}就称为普通代码块。
* 普通代码块和一般的语句执行顺序由他们在代码中出现的次序决定--“先出现先执行”
* */
public class CodeBlock01 {
    public static void main(String[] args) {
        {
            int x=11;
            System.out.println("普通代码块内的变量x="+x);
        }
        {
            int y=13;
            System.out.println("普通代码块内的变量y="+y);
        }
        int x=12;
        System.out.println("主方法内的变量x="+x);
    }
    /*
    * 普通代码块内的变量x=11
普通代码块内的变量y=13
主方法内的变量x=12
    *
    * */
}
