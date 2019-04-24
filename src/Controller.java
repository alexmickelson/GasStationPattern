import java.text.DecimalFormat;

public class Controller implements ITimeObserver{
    public GasStation station;
    public CustomerGenerator customerGenerator;
    public ITimeObservable clock;
    public ITank tank85;
    public ITank tank89;
    public ITruckService truckService;
    public SwingStationFrame view;

    public Controller(GasStation station,
                      ITimeObservable clock,
                      ITank tank85,
                      ITank tank89,
                      ITruckService truckService,
                      SwingStationFrame view){
        this.station = station;
        this.clock = clock;
        //clock.ChangeSpeedOfProgram(1);
        this.tank85 = tank85;
        this.tank89 = tank89;
        this.truckService = truckService;
        this.view = view;

        customerGenerator = new CustomerGenerator(station,clock,10);
        Thread c = new Thread(customerGenerator);
        clock.subscribe(this);
        c.start();

        //clock.start();



    }

    private void updatePump(int i, SwingPumpPanel pump){

        if(station.pumps[i].IsBusy()) {
            pump.setCarDisplay("CAR HERE");
        }else {
            pump.setCarDisplay("NO CAR");
        }
        pump.setRequestedFuel(station.pumps[i].GetGradeChosen());
        //pump.setGallonsRequested(station.pumps[i].); //getgallonsrequested from pump
        pump.setGallonsPumped(Math.round(station.pumps[i].GetCurrentPumpedAmount()*100)/100.0);
        pump.setGrade85Total(Math.round(station.pumps[i].Get85GasAmountPumped()*100)/100.0);
        pump.setGrade87Total(Math.round(station.pumps[i].Get87GasAmountPumped()*100)/100.0);
        pump.setGrade89Total(Math.round(station.pumps[i].Get89GasAmountPumped()*100)/100.0);
    }

    @Override
    public void update(int ticks) {
        //pumps
        int i = 0;
        updatePump(i++, view.pumps.pump1);
        updatePump(i++, view.pumps.pump2);
        updatePump(i++, view.pumps.pump3);
        updatePump(i++, view.pumps.pump4);
        updatePump(i++, view.pumps.pump5);
        updatePump(i++, view.pumps.pump6);

        //tanks
        view.tanks.regular.setCurrentAmount(Math.round(tank85.getLevel()*100)/100);
        view.tanks.regular.setTotalCapacity(Math.round(tank85.getMaxAmount()*100)/100);
        view.tanks.regular.setReorderPoint(Math.round(station.minLevel*100)/100);
        //view.tanks.regular.setOrderPlaced(truckService.);
        //view.tanks.regular.setOrderQty();

        view.tanks.premium.setCurrentAmount(Math.round(tank89.getLevel()*100)/100);
        view.tanks.premium.setTotalCapacity(Math.round(tank89.getMaxAmount()*100)/100);
        view.tanks.premium.setReorderPoint(Math.round(station.minLevel*100)/100);
        //view.tanks.premium.setOrderPlaced(truckService.);
        //view.tanks.premium.setOrderQty();

        //STATS
        //tank
        //view.stats.tank.setPremiumTotalGallonsOrdered(station.);
        //view.stats.tank.setPremiumNumberOfOrders();
        //view.stats.tank.setPremiumTotalGallonsDelivered();
        //view.stats.tank.setPremiumTotalGallonsOrderedOverage();

        //view.stats.tank.setRegularTotalGallonsOrdered(station.);
        //view.stats.tank.setRegularNumberOfOrders();
        //view.stats.tank.setRegularTotalGallonsDelivered();
        //view.stats.tank.setRegularTotalGallonsOrderedOverage();

        //sales
        view.stats.sales.setPremiumSold(Math.round(station.gasStationTotal89pumped*100)/100); //getter
        view.stats.sales.setMigradeSold(Math.round(station.gasStationTotal87pumped*100)/100); //getter
        view.stats.sales.setRegularSold(Math.round(station.gasStationTotal85pumped*100)/100); //getter

        //cars
        //view.stats.cars.setArrived();
        //view.stats.cars.setServed();
        view.stats.cars.setLostNoPremium(station.totalCustomersLost89Grade); //getter
        view.stats.cars.setLostNoMedium(station.totalCustomersLost87Grade); //getter
        view.stats.cars.setLostNoRegular(station.totalCustomersLost85Grade); //getter
        view.stats.cars.setTotalLost(customerGenerator.customerslostduetoqueueoverfill); //getter
    }

    @Override
    public void changespeed(int speed) {

    }


}
