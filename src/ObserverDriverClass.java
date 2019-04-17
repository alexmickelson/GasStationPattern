public class ObserverDriverClass implements ITimeObserver {

    @Override
    public void update(int ticks) {
        System.out.println(ticks + " Ticks");
    }
}
