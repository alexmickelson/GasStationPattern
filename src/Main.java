public class Main{

    public static void main(String[] args) throws InterruptedException {
        Tank tank89 = new Tank(8000);
        Tank tank85 = new Tank(7000);
        MockDriver mock = new MockDriver(8000, 8000);

        for (int x=0; x<10; x++)
        {
            MyThread temp= new MyThread(mock);
            temp.start();
            System.out.println("\n\n> Started Thread:" + x);
            Thread.sleep(2000);
        }
    }
}
