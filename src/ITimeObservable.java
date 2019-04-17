public interface ITimeObservable {
    boolean subscribe(ITimeObserver observer);
    boolean unsubscribe(ITimeObserver observer);
    void valueUpdated();

}