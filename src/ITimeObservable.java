public interface ITimeObservable {
    boolean subscribe(ITimeObserver observer);
    boolean unsubscribe(ITimeObserver observer);
    void valueUpdated();
    void SpeedChanged();
    void start();
    void pause();
    void ChangeSpeedOfProgram(int speed);
    boolean isPaused();
}
