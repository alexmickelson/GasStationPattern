public class MyThread extends Thread {
    MockDriver mock;

    public MyThread (MockDriver mock) {
        this.mock = mock;
    }

    public void run() {
        mock.doStuff();
    }
}
