public class ObserverDriverClass implements ITimeObserver {

    @Override
    public void update(int ticks) {
        System.out.println(ticks + " Ticks");
    }


    @Override
    public void changespeed(int speed) {

    }
}
