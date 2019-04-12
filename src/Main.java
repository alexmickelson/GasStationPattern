public class Main{

    public static void main(String[] args) throws InterruptedException {
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
