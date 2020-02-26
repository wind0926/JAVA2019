package JUCProject.stream;


import java.util.Arrays;
import java.util.List;

class User
{
    private Integer id;
    private String  userName;
    private int     age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(Integer id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * @create 2019-02-26 22:24
 *
 * 题目：请按照给出数据，找出同时满足
 *      偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
 *      最后只输出一个用户名字
 */
public class StreamDemo
{
    public static void main(String[] args)
    {
        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);

        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        list.stream().filter(p -> {
            return p.getId() % 2 == 0;
        }).filter(p -> {
            return p.getAge() > 24;
        }).map(f -> {
            return f.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);











  /*      //四大函数式接口
        //函数型接口
        Function<String,Integer> function=s->{return s.length();};
        System.out.println(function.apply("abc"));

        //断定型接口
        Predicate<String> predicate=s -> {return s.isEmpty();};
        System.out.println(predicate.test("abc"));

        //消费型接口
        Consumer<String> consumer=s -> {
            System.out.println(s);
        };
       consumer.accept("awfe");

        //供给型接口
        Supplier<String> supplier=()->{
            return "java";
        };
        System.out.println(supplier.get());

*/


  /*      list.stream().filter(p -> {
            return p.getId() % 2 == 0;
        }).filter(p -> {
            return p.getAge() > 24;
        }).map(f -> {
            return f.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);


        //    R apply(T t);
        Function<String,Integer> function = t -> {return t.length();};
        System.out.println(function.apply("abc"));

        // boolean test(T t);
        Predicate<String> predicate = t -> {return t.startsWith("a");};
        System.out.println(predicate.test("a"));

        //void accept(T t);
        Consumer<String> consumer = t -> {System.out.println(t);};
        consumer.accept("java1018");


        //    T get();
        Supplier<String> supplier =  () -> {return UUID.randomUUID().toString();};
        System.out.println(supplier.get());;
*/
    }
}


