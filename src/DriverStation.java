public class DriverStation {
    public static void main(String[] args){

        ITimeObservable clock = TimeService.getInstance();
        clock.subscribe(new TimeReporter());
        ITank tank89 = new Tank(0);
        ITank tank85 = new Tank(0);



        ITruckService truckService = new TruckService(clock);
        var station = new GasStation(truckService,tank85,tank89,clock);

        for(int i = 0 ;i<1;i++) {
            var c = new Customer();
            c.SetDesiredAmountOfGas(30);
            c.SetMaxAvailableMoney(100);
            station.AddCustomer(c);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
