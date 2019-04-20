
import java.util.LinkedList;

public class TruckService implements ITruckService {
    private LinkedList<TankServiceSchedule> tankList;

    TruckService(ITimeObservable time){
        tankList = new LinkedList<>();
        time.subscribe(this);
    }

    @Override
    public void CallTruck(ITank tank, double amt) {
        TankServiceSchedule TankServiceSchedule = new TankServiceSchedule(tank, 10, amt);

        for (var tankService : tankList) {
            if (tankService.tank != tank) {
            }
            tankList.add(TankServiceSchedule);
        }
    }

    @Override
    public void update(int ticks) {
        for (var tankService : tankList) {
            if (tankService.serviceTime >= ticks) {
                //fill tank
            }

        }
    }

    @Override
    public void updateSpeedOfTime(int time) {

    }
}

