public interface ITimeObservable {
    boolean subscribe(ITimeObserver observer);
    boolean unsubscribe(ITimeObserver observer);
    void valueUpdated();
    void SpeedChanged();
    void start();
    void pause();
    void setSpeedOfProgram(int speed);
    int getSpeedOfProgram();
    boolean isPaused();
}
