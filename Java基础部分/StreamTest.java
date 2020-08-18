

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author tanglilei
 * @since 2020/6/30 11:04
 */
public class StreamTest {
    public void testStream(){
        //stream() − 为集合创建串行流。
        List<String> strings = Arrays.asList("abc", "aew", "efe", "", "123");
        strings.stream().filter(s -> {
            return !s.isEmpty();
        }).collect(Collectors.toList());
        System.out.println(strings);

        //forEach// Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
        Random random = new Random();
        random.ints().limit(4).forEach(System.out::println);

        //map
        //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
//            List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        List<Integer> collect = numbers.stream().map(integer -> {
            return integer * integer;
        }).collect(Collectors.toList());
        System.out.println(collect);

        //filter
        //filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
        //
        List<String> str = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //// 获取空字符串的数量
        long count = str.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);

        // limit
        //limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：

        Random random1 = new Random();
        random1.ints().limit(10).forEach(System.out::println);

        //sorted
        //sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
        System.out.println("+++++++++++++++++");
        Random random2 = new Random();
        random2.ints().limit(10).sorted().forEach(System.out::println);

        //并行（parallel）程序
        //parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
        System.out.println("+++++++++++++++++");

        List<String> strings1 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        int count1 = (int) strings1.parallelStream().filter(string -> string.isEmpty()).count();
        //我们可以很容易的在顺序运行和并行直接切换。
        System.out.println(count1);

        //  Collectors
        //  Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        System.out.println("+++++++++++++++++");

        List<String> strings3 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings3.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        //Collectors.joining(", ")以“,”收集所有元素
        String mergedString = strings3.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("合并字符串: " + mergedString);
            /*
            *
            * joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
            joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix) 方法接受一个字符串序列作为拼接符，并在拼接完成后添加传递的前缀和后缀。假如我们传递的分隔符为 "-"，前缀为 "[" ， 后缀为 "]" 。那么输出结果为 [A-B-C-D]

                JoiningExample.java
                package cn.twle.util.stream;
               import java.util.Arrays;
               import java.util.List;
               import java.util.stream.Collectors;
               *public class JoiningExample { public static void main(String[] args) {
               * List<String> list = Arrays.asList("A","B","C","D","[","]"); String result= list.stream().collect(Collectors.joining("-")); System.out.println(result); } }
运行结果为 [A-B-C-D]
            * */
        String  mergedStringTest= strings3.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(",","[","]"));
        System.out.println(mergedStringTest);


        // 统计
        //另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
        System.out.println("+++++++++++++++++++++++++++++");
        List<Integer> integers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) ->{
            return x;
        } ).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());




/*
*  List<Long> codeIds = esCodeDaos.stream().map(x->x.getId()).collect(Collectors.toList());
* */


        String s=" ";
        boolean empty = s.isEmpty();
        boolean blank = StringUtils.isBlank(s);
        System.out.println(empty);
        System.out.println(blank);

    }

    @Test
    public void compare(){
        String str="tanglilei";
        System.out.println(str.compareTo("tanglilei"));
    }
}
