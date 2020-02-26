package base;
/*
* 代码块加载顺序高于构造方法
* 构造代码块在每次创建对象时都会被调用，并且构造代码块的执行顺序优于类构造函数
* */
public class CodeBlock02 {
    {
        System.out.println("第二构造块333");
    }
    public CodeBlock02(){
        System.out.println("构造方法222");
    }
    {
        System.out.println("第一代码块111");
    }

    public static void main(String[] args) {
        new CodeBlock02();
        System.out.println("============");
        new CodeBlock02();
    }
    /*
    *
    * 第二构造块333
    第一代码块111
    构造方法222
    ============
    第二构造块333
    第一代码块111
    构造方法222
    * */
}
