import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GasStation implements ITimeObserver {
    private IPump[] pumps;
    private ITruckService truckService;
    private ITank tank89;
    private ITank tank85;
    private double minLevel;

    private LinkedList<ICustomer> customerQueue = new LinkedList<>();
    public GasStation( ITruckService truckService,ITank tank85,ITank tank89,ITimeObservable clock){
        //customerQueue.add();
        //customerQueue.remove();
        clock.subscribe(this);
        minLevel = 250;
        pumps = new Pump[6];

        this.tank85 = tank85;
        this.tank89 = tank89;
        this.truckService = truckService;
        for(int i = 0; i < pumps.length; i++){
            pumps[i] = new Pump(tank85,tank89,clock);
        }
    }

    public void AddCustomer(ICustomer customer){
        //synchronized (GasStation.class){
            var res = customerQueue.add(customer);
        //}
    }

    public int GetQueueLenght(){
        return customerQueue.size();
    }


     public void update(int ticks){
        //check to see if we need to schedual a truck for tank 89
        if(tank89.getLevel() <= minLevel)
        {
            truckService.CallTruck(tank89,(tank89.getLevel() - 1000));
            //here we would lower the amount of money in the gas station by the number of dollars we just ordered
        }
        //check to see if we need to schedual a truck for tank 85
        if(tank89.getLevel() <= minLevel)
        {
             truckService.CallTruck(tank85,(tank85.getLevel() - 1000));
             //here we would lower the amount of money in the gas station by the number of dollars we just ordered
        }
        //here we are checking to see if there are any cusotomers in the queue and any cost
        if(customerQueue.size() > 0){
            for(int i = 0; i < pumps.length; i++)
            {
                if(!pumps[i].IsBusy() && (customerQueue.size() > 0)){
                    var c = customerQueue.remove();
                    pumps[i].SetCustomer(c);
                    Thread t = new Thread(pumps[i]);
                    t.start();

                }

            }

        }
        if(ticks%100 == 0){
            log("the world is happy and everything is beautiful");
        }
    }

    private void log(String s){
        System.out.println("Station:     " + s);
        return;
    }
}

