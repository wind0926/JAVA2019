package base;

class Code{
    public Code(){
        System.out.println("Code的构造方法1111");
    }
    {
        System.out.println("Code的构造块2222");
    }
    static {
        System.out.println("Code的静态代码块3333");
    }
}

public class CodeBlock03 {
    {
        System.out.println("CodeBlock3的构造块4444");
    }
    static {
        System.out.println("CodeBlock3的静态代码块5555");
    }

    public CodeBlock03() {
        System.out.println("Code的构造方法6666");
    }

    public static void main(String[] args) {
        //所有加载静态现行，static加载一次
//       mian静态现行，生成模板
//        静态>构造块>构造方法
        new Code();
        System.out.println("---------");
        new Code();
        System.out.println("---");
        new CodeBlock03();
    }
}
