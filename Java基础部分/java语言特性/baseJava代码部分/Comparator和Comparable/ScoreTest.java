package base;
/*
* 1、Comparable的代码如下：

public interface Comparable<T> {
    public int compareTo(T o);
}
2、Comparator的代码如下:

public interface Comparator<T> {
    int compare(T o1, T o2);
    boolean equals(Object obj);

    // jdk1.8 后的方法
    default Comparator<T> reversed() {
        return Collections.reverseOrder(this);
    }
*
*
*
* */

/*
*
* Comparable和Comparator的主要区别在于：

　　(1).Comparator 和 Comparable都是Java中的内部比较器接口，都是用来实现对一个自定义的类进行排序

　　(2). 不同的是实现Comparable接口是定义在类的内部，比较代码需要嵌入类的内部结构中

　　(3).  Comparator 实现在类的外部，单独实现第一个比较器，不需要对原来的类进行结构上的变化，属于无侵入式的。
    (4).Comparator更加符合开闭原则，对修改关闭，对扩展开放
*
* */
import java.util.Arrays;

public class ScoreTest {
    public static void main(String[] args) {
        Score score = new Score(2,1);
        Score score1 = new Score(3,3);
        Score score2 = new Score(1,4);
        Score score3 = new Score(2,2);
        Score score4 = new Score(5,5);
        Score[] help=new Score[]{score,score1,score2,score3,score4};
        Arrays.sort(help,new ScoreComparator());
        System.out.println(Arrays.toString(help));
    }
}
