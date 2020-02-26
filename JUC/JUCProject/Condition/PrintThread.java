package JUCProject.Condition;

class Resource{
    private int number=1;
    private Character character='A';


    public void printNumber(){
       while (true){
           synchronized (this){
               notify();
               if (number<=52){
                   System.out.print(number++);
                   System.out.print(number++);
                   try {
                       wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       }

    }

    public void printCharacter(){
        while (true){

            synchronized (this){
                notify();
                if (character<='Z'){
                    System.out.print(character++);
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


public class PrintThread {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(()->{
            for (int i = 0; i < 26; i++) {
               resource.printNumber();
            }
        },"i").start();
        new Thread(()->{
            for (int i = 0; i < 26; i++) {
                resource.printCharacter();
            }
        },"i").start();

    }
}
