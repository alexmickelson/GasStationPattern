import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Controller implements ITimeObserver, ActionListener {
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
                      ITruckService truckService){//,
                      //SwingStationFrame view){
        this.station = station;
        this.clock = clock;
        clock.ChangeSpeedOfProgram(100);
        this.tank85 = tank85;
        this.tank89 = tank89;
        this.truckService = truckService;


        customerGenerator = new CustomerGenerator(station,clock);
        Thread c = new Thread(customerGenerator);
        c.start();
        //clock.start();
        this.view = new SwingStationFrame(this);
        Thread t = new Thread(this.view);//view not updateing
        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //initialize button values
        this.view.buttons.carArrivalSpeed.setStat(customerGenerator.getFrequency()+"");


        clock.subscribe(this);
    }

    private void updatePump(int i, SwingPumpPanel pump){

        if(station.pumps[i].IsBusy()) {
            pump.setCarDisplay("CAR HERE");
        }else {
            pump.setCarDisplay("NO CAR");
        }
        pump.setRequestedFuel(station.pumps[i].GetGradeChosen());
        pump.setGallonsRequested(Math.round(station.pumps[i].getAmountRequested()*100)/100.0);
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
        view.tanks.regular.setCurrentAmount(Math.round(tank85.getLevel()*100)/100.0);
        view.tanks.regular.setTotalCapacity(Math.round(tank85.getMaxAmount()*100)/100.0);
        view.tanks.regular.setReorderPoint(Math.round(station.minLevel*100)/100.0);
        //view.tanks.regular.setOrderPlaced(truckService.);
        //view.tanks.regular.setOrderQty();

        view.tanks.premium.setCurrentAmount(Math.round(tank89.getLevel()*100)/100.0);
        view.tanks.premium.setTotalCapacity(Math.round(tank89.getMaxAmount()*100)/100.0);
        view.tanks.premium.setReorderPoint(Math.round(station.minLevel*100)/100.0);
        //view.tanks.premium.setOrderPlaced(truckService.);
        //view.tanks.premium.setOrderQty();

        //STATS
        //tank
        view.stats.tank.setPremiumTotalGallonsOrdered(Math.round(tank89.GetGallonsOrdered()*100)/100);
        view.stats.tank.setPremiumNumberOfOrders(tank89.GetNumberOfOrders());
        view.stats.tank.setPremiumTotalGallonsDelivered(Math.round(tank89.GetGallonsDeliverd()*100)/100);
        //view.stats.tank.setPremiumTotalGallonsOrderedOverage();

        view.stats.tank.setRegularTotalGallonsOrdered(Math.round(tank85.GetGallonsOrdered()*100)/100);
        view.stats.tank.setRegularNumberOfOrders(tank85.GetNumberOfOrders());
        view.stats.tank.setRegularTotalGallonsDelivered(Math.round(tank85.GetGallonsDeliverd()*100)/100);
        //view.stats.tank.setRegularTotalGallonsOrderedOverage();

        //sales
        view.stats.sales.setPremiumSold(Math.round(station.getGasStationTotal89pumped()*100)/100.0);
        view.stats.sales.setMigradeSold(Math.round(station.getGasStationTotal87pumped()*100)/100.0);
        view.stats.sales.setRegularSold(Math.round(station.getGasStationTotal85pumped()*100)/100.0);

        //cars
        //view.stats.cars.setArrived();
        //view.stats.cars.setServed();
        view.stats.cars.setLostNoPremium(station.getTotalCustomersLost89Grade());
        view.stats.cars.setLostNoMedium(station.getTotalCustomersLost87Grade());
        view.stats.cars.setLostNoRegular(station.getTotalCustomersLost85Grade());
        view.stats.cars.setTotalLost(customerGenerator.customerslostduetoqueueoverfill); //getter
    }

    @Override
    public void changespeed(int speed) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();

        if(clicked == view.buttons.orderSpeed.getUp()){

        } else if (clicked == view.buttons.orderSpeed.getDown()){

        } else if (clicked == view.buttons.carArrivalSpeed.getUp()){

            customerGenerator.setFrequency(customerGenerator.getFrequency()+5);
            view.buttons.carArrivalSpeed.setStat(customerGenerator.getFrequency() + "");

        } else if (clicked == view.buttons.carArrivalSpeed.getDown()){
            customerGenerator.setFrequency(customerGenerator.getFrequency()-5==0?5:customerGenerator.getFrequency()-5);
            view.buttons.carArrivalSpeed.setStat(customerGenerator.getFrequency() + "");

        } else if (clicked == view.buttons.pumpingSpeed.getUp()){

        } else if (clicked == view.buttons.pumpingSpeed.getDown()){

        } else if (clicked == view.buttons.avgGasReq.getUp()){

        } else if (clicked == view.buttons.avgGasReq.getDown()){

        }
    }
}
