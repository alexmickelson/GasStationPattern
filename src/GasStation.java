import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GasStation implements ITimeObserver {
    public IPump[] pumps;
    private ITruckService truckService;
    private ITank tank89;
    private ITank tank85;
    private double minLevel;
    public double getMinTankLevel(){
        return minLevel;
    }
    public void setMinTankLevel(double level){
        minLevel = level;
    }
    private int totalCustomersLost85Grade;
    private int totalCustomersLost87Grade;
    private int totalCustomersLost89Grade;
    private double gasStationTotal85pumped;
    private double gasStationTotal87pumped;
    private double gasStationTotal89pumped;
    private int totalCustomersArrived;
    private int totalCustomersServed;

    public int getTotalCustomersLost85Grade(){
        return totalCustomersLost85Grade;
    }
    public int getTotalCustomersLost87Grade(){
        return totalCustomersLost87Grade;
    }
    public int getTotalCustomersLost89Grade(){
        return totalCustomersLost89Grade;
    }

    public double getGasStationTotal85pumped(){
        return gasStationTotal85pumped;
    }
    public double getGasStationTotal87pumped(){
        return gasStationTotal87pumped;
    }
    public double getGasStationTotal89pumped(){
        return gasStationTotal89pumped;
    }

    public int getTotalCustomersArrived(){ return totalCustomersArrived; }
    public int getTotalStationCustomersServed(){ return totalCustomersServed; }
    private LinkedList<ICustomer> customerQueue = new LinkedList<>();
    public GasStation( ITruckService truckService,ITank tank85,ITank tank89,ITimeObservable clock){
        //customerQueue.add();
        //customerQueue.remove();
        clock.subscribe(this);
        minLevel = 20;
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
            totalCustomersArrived++;
        }
    }

    public int GetQueueLength(){
        synchronized (customerQueue){
            log("Customer Queue Size: "+ customerQueue.size());
            return customerQueue.size();
        }
    }


     public void update(int ticks){
        //check to see if we need to schedule a truck for tank 89
        if(tank89.getLevel() <= minLevel)
        {
            truckService.CallTruck(tank89,(tank89.getMaxAmount()-tank89.getLevel()));
            //here we would lower the amount of money in the gas station by the number of dollars we just ordered
        }
        //check to see if we need to schedule a truck for tank 85
        if(tank85.getLevel() <= minLevel)
        {

            truckService.CallTruck(tank85,(tank85.getMaxAmount()-tank85.getLevel()));
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
        if(ticks%100 == 0) {
            log("the world is happy and everything is beautiful");
        }
        getLostTotals();
        getPumpedTotals();
        getTotalCustomersServed();
    }

    private void log(String s){
        System.out.println("Station:     " + s);
        return;
    }

    @Override
    public void changespeed(int time) {

    }
    private void getLostTotals()
    {
        int temp85=0;
        int temp87=0;
        int temp89=0;
        for(int i = 0; i < pumps.length; i++) {
            temp85 += pumps[i].Get85LostCustomers();
            temp87 += pumps[i].Get87LostCustomers();
            temp89 += pumps[i].Get89LostCustomers();
        }
        totalCustomersLost85Grade = temp85;
        totalCustomersLost87Grade = temp87;
        totalCustomersLost89Grade = temp89;

        log("Total Lost Customers 85: " + totalCustomersLost85Grade);
        log("Total Lost Customers 87: " + totalCustomersLost87Grade);
        log("Total Lost Customers 89: " + totalCustomersLost89Grade);
    }

    private void getPumpedTotals()
    {
        var t85 = 0;
        var t87 = 0;
        var t89 = 0;

        for(int i = 0; i < pumps.length; i++) {
            t85 += pumps[i].Get85GasAmountPumped();
            t87 += pumps[i].Get87GasAmountPumped();
            t89 += pumps[i].Get89GasAmountPumped();
        }

        gasStationTotal85pumped = t85;
        gasStationTotal87pumped = t87;
        gasStationTotal89pumped = t89;
        log("[Tank 85] Total Amount Pumped: " + gasStationTotal85pumped);
        log("[Tank 87] Total Amount Pumped: " + gasStationTotal87pumped);
        log("[Tank 89] Total Amount Pumped: " + gasStationTotal89pumped);
    }
    private void getTotalCustomersServed()
    {
        var tCustomers = 0;

        for(int i = 0; i < pumps.length; i++) {
            tCustomers += pumps[i].getTotalCustomersServed();
        }

        totalCustomersServed = tCustomers;
        log("Total Customers Served: " + totalCustomersServed);
    }
}

