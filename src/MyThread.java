public class MyThread extends Thread {
    MockDriver mock = new MockDriver();

    public MyThread (String s) {
        super(s);
    }

    public void run() {
        mock.doStuff();
    }
}
