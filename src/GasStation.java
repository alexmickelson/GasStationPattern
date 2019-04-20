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
            pumps[i] = new Pump(tank85,tank89,clock, i);
        }
    }

    public void AddCustomer(ICustomer customer){
        synchronized (customerQueue){
            var res = customerQueue.add(customer);
        }
    }

    public int GetQueueLength(){
        synchronized (customerQueue){
            return customerQueue.size();
        }
    }


     public void update(int ticks){
        //check to see if we need to schedule a truck for tank 89
        if(tank89.getLevel() <= minLevel)
        {
            //truckService.CallTruck(tank89,(0));
            truckService.CallTruck(tank89,(1000-tank89.getLevel()));
            //here we would lower the amount of money in the gas station by the number of dollars we just ordered
        }
        //check to see if we need to schedule a truck for tank 85
        if(tank89.getLevel() <= minLevel)
        {    //truckService.CallTruck(tank89,(0));
             truckService.CallTruck(tank85,(1000-tank85.getLevel()));
             //here we would lower the amount of money in the gas station by the number of dollars we just ordered
        }
        //here we are checking to see if there are any customers in the queue and any cost
        if(GetQueueLength() > 0){
            for(int i = 0; i < pumps.length; i++)
            {
                if(!pumps[i].IsBusy() && (GetQueueLength() > 0)){
                    ICustomer c;
                    synchronized (customerQueue){
                        c = customerQueue.remove();
                    }
                    pumps[i].SetCustomer(c);
                    Thread t = new Thread(pumps[i]);
                    t.start();
                    log("Added Customer at Pump " +  i);

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

    @Override
    public void changespeed(int time) {

    }
}

