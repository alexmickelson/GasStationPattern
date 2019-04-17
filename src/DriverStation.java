public class DriverStation {
    public static void main(String[] args){

        ITimeObservable clock = TimeService.getInstance();
        clock.subscribe(new TimeReporter());
        ITank tank89 = new Tank(1000);
        ITank tank85 = new Tank(1000);

        ITruckService truckService = new TruckService(clock);
        var station = new GasStation(truckService,tank85,tank89,clock);

        //for(int i = 0 ;i<10;i++) {
            var c = new Customer();
            c.SetDesiredAmountOfGas(30);
            c.SetMaxAvailableMoney(100);
            station.AddCustomer(c);
        //}
        //
    }
}