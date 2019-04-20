import java.util.LinkedList;
import java.util.Random;

public class TruckService implements ITruckService {
    private LinkedList<TankServiceSchedule> tankList;
    private int tickscurrent;
    private int minimumWaitTime;
    private double PumpRate;

    TruckService(ITimeObservable time){
        tankList = new LinkedList<>();
        time.subscribe(this);
    }

    @Override
    public void CallTruck(ITank tank, double amt) {
        //generate a random number between 50 and 250
        Random rand = new Random();
        //add that to the current ticks
        int n = rand.nextInt(200);
        //add 50-250  ms to the currnt time b
        n = n + minimumWaitTime + tickscurrent;
        TankServiceSchedule TankServiceSchedule = new TankServiceSchedule(tank, n, amt);

        for (var tankService : tankList) {
            if (tankService.tank != tank) {
            }
            tankList.add(TankServiceSchedule);
        }
    }

    @Override
    public void update(int ticks) {
        this.tickscurrent = ticks;
        LinkedList<TankServiceSchedule> newtanklist = new LinkedList<TankServiceSchedule>();
        for (var tankService : tankList) {
         newtanklist.add(tankService);
        }



        for (var tankService : tankList) {
            if (tankService.serviceTime >= ticks) {
                if(tankService.amt <= 0){
                    if(newtanklist.contains(tankService)){
                        newtanklist.remove(tankService);
                    }
                }else{
                    tankService.tank.GiveGasToTank(PumpRate);
                    tankService.amt = tankService.amt - PumpRate;
                }
            }

        }

        tankList = newtanklist;
    }

    public void SetMinimumWaitTime(int min){
        minimumWaitTime = min;
    }

    public void SetPumpRate(double rate){
        PumpRate = rate;
    }

}

