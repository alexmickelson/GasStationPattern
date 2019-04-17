import java.util.Scanner;

public class DriverTimeService {
    public static void main(String[] args){
        ITimeObservable timeService = TimeService.getInstance();

        timeService.subscribe(new ObserverDriverClass());
        timeService.subscribe(new ObserverDriverClass());
        timeService.subscribe(new ObserverDriverClass());
        timeService.subscribe(new ObserverDriverClass());


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timeService = TimeService.getInstance();

        timeService.subscribe(new ObserverDriverClass());
        timeService.subscribe(new ObserverDriverClass());
        timeService.subscribe(new ObserverDriverClass());
        timeService.subscribe(new ObserverDriverClass());


        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}
