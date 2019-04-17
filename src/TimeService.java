import java.util.Vector;

public class TimeService implements ITimeObservable, Runnable {
    private int ticks;
    private int milliSecondDelay;
    private static volatile boolean exists = false;
    private static TimeService oneInstance;

    private Vector<ITimeObserver> subscribers;

    private TimeService (){
        ticks = 0;
        milliSecondDelay = 500;
        subscribers = new Vector<ITimeObserver>();
        exists=true;
        Thread t = new Thread(this);
        t.start();
    }

    public static ITimeObservable getInstance(){
        if(!exists){
            synchronized (TimeService.class){
                if (!exists){
                    oneInstance = new TimeService();
                }
            }
        }
        return oneInstance;
    }

    @Override
    public boolean subscribe(ITimeObserver observer) {
        return subscribers.add(observer);
    }

    @Override
    public boolean unsubscribe(ITimeObserver observer) {
        return subscribers.remove(observer);
    }

    @Override
    public void valueUpdated() {
        for(var sub : subscribers){
            sub.update(ticks);
        }
    }

    @Override
    public void run() {
        while (true){
            //swallow because run is overridden and cannot throw an exception
            try {
                Thread.sleep(milliSecondDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticks++;
            valueUpdated();
        }

    }
}
