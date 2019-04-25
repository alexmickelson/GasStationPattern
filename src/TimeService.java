import java.util.Vector;

public class TimeService extends Thread implements ITimeObservable{
    private int ticks;
    private int milliSecondDelay;
    private static volatile boolean exists = false;
    public boolean isPaused;
    private static volatile TimeService oneInstance;

    private Vector<ITimeObserver> subscribers;

    private TimeService (){
        ticks = 0;
        isPaused = false;
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
    public void pause(){
        isPaused = true;
    }

    @Override
    public void start(){
        isPaused = false;
    }

    @Override
    public boolean subscribe(ITimeObserver observer) {
        synchronized (TimeService.class){
            return subscribers.add(observer);
        }
    }

    @Override
    public boolean unsubscribe(ITimeObserver observer) {
        synchronized (TimeService.class) {
            return subscribers.remove(observer);
        }
    }

    @Override
    public void valueUpdated() {
        synchronized (subscribers){
            for (var sub : subscribers) {
                sub.update(ticks);
            }
        }
    }

    @Override
    public void SpeedChanged(){
        for(var sub : subscribers){
            sub.changespeed(milliSecondDelay);
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
            if(!isPaused) {
                ticks++;
                valueUpdated();
            }
        }

    }

    public boolean isPaused(){
        return isPaused;
    }

    public void ChangeSpeedOfProgram(int speed){
        milliSecondDelay = speed;
        valueUpdated();
    }
}
