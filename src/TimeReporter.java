public class TimeReporter implements ITimeObserver {
    @Override
    public void update(int ticks) {
        System.out.println("Current time:   " + ticks);
    }
}
