public class Main{

    public static void main(String[] args) throws InterruptedException {
        for (int x=0; x<10; x++)
        {
            MyThread temp= new MyThread("Thread #" + x);
            temp.start();
            System.out.println("Started Thread:" + x);
            Thread.sleep(1000);
        }
    }
}
