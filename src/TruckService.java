import jdk.swing.interop.SwingInterOpUtils;

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
        PumpRate = 2.5;
    }

    @Override
    public void CallTruck(ITank tank, double amt) {
        //generate a random number between 50 and 25
        Random rand = new Random();
        //add that to the current ticks
        int n = rand.nextInt(50);
        //add 50-250  ms to the currnt time b
        n = n + minimumWaitTime + tickscurrent;

        TankServiceSchedule TankServiceSchedule = new TankServiceSchedule(tank, n, amt);
        boolean tankalreadyadded = false;
        for (var tankService : tankList) {
            if (tankService.tank == tank) {
                tankalreadyadded = true;
            }

        }
        if(!tankalreadyadded){
            tank.AddGallonsOrdered(amt);
            tank.AddNewOrder();
            tank.SetOrderSchedueld(true);
            tank.SetCurrentOrderAmount(amt);
            tankList.add(TankServiceSchedule);
            System.out.println("Truck has been called YEET");
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
            if (tankService.serviceTime <= ticks) {
                tankService.tank.SetOrderSchedueld(false);
                tankService.tank.SetCurrentOrderAmount(0);
                if(tankService.amt <= 0){
                    if(newtanklist.contains(tankService)){
                        newtanklist.remove(tankService);
                    }
                }else{
                    if((tankService.amt - PumpRate) < 0){
                        tankService.tank.GiveGasToTank(tankService.amt);
                        tankService.tank.AddGallonsDeliverd(tankService.amt);
                        tankService.amt = 0;
                        System.out.println("Tank is at: "+tankService.tank.getLevel()+" Gallons");
                    }else{
                        tankService.tank.GiveGasToTank(PumpRate);
                        tankService.tank.AddGallonsDeliverd(PumpRate);
                        tankService.amt = tankService.amt - PumpRate;
                        System.out.println("Tank is at: "+tankService.tank.getLevel()+" Gallons");
                    }



                }
            }

        }
        tankList = newtanklist;
    }

    public void SetMinimumWaitTime(int min){

        minimumWaitTime = min;
    }

    public int GetMinimumWaitTime(){
        return this.minimumWaitTime;
    }

    public void SetPumpRate(double rate){
        PumpRate = rate;
    }

    public LinkedList<TankServiceSchedule> GetServiceSchedule(){
        return tankList;
    }
    
    @Override
    public void changespeed(int speed) {

    }
}

