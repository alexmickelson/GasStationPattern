public class DriverStation {
    public static void main(String[] args){


        ITimeObservable clock = TimeService.getInstance();
        clock.subscribe(new TimeReporter());
        clock.ChangeSpeedOfProgram(100);
        ITank tank89 = new Tank(1000);
        ITank tank85 = new Tank(1000);



        ITruckService truckService = new TruckService(clock);
        var station = new GasStation(truckService,tank85,tank89,clock);

        for(int i = 0 ;i<10;i++) {
            var c = new Customer();
            c.SetDesiredAmountOfGas(1+Math.round(Math.random()*100)%17);
            c.SetMaxAvailableMoney(100);
            station.AddCustomer(c);
            System.out.println("Tank 85: "+tank85.GetGallonsOrdered()+"Gallons Ordered");


            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
