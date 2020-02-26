package JUCProject.question;

/*
* * 1 标准访问，先打印短信还是邮件
2 停4秒在短信方法内，先打印短信还是邮件
3 普通的hello方法，是先打短信还是hello
4 现在有两部手机，先打印短信还是邮件
5 两个静态同步方法，1部手机，先打印短信还是邮件
6 两个静态同步方法，2部手机，先打印短信还是邮件
7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
8 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
*
* */
/*
        1、邮件
        2、邮件
        3、Hello
        4、邮件
        5、短信
        6、短信
        7、邮件
        8、邮件
*/

class Phone{
    public  synchronized void sendEmail() throws InterruptedException {

        System.out.println("----------sendEmail");
    }
    public  synchronized void sendSMS() throws InterruptedException {

        System.out.println("----------sendSMS");
    }
    public  void hello(){
        System.out.println("----------hello");
    }
}

public class Lock_8 {
    public static void main(String[] args) {
        Phone phone01 = new Phone();
        Phone phone02 = new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone01.sendEmail();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone02.sendSMS();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
