public class DriverStation {
    public static void main(String[] args) throws InterruptedException {


        ITimeObservable clock = TimeService.getInstance();
        clock.subscribe(new TimeReporter());
        clock.ChangeSpeedOfProgram(500);
        ITank tank89 = new Tank(10);
        ITank tank85 = new Tank(10);




        ITruckService truckService = new TruckService(clock);
        var station = new GasStation(truckService,tank85,tank89,clock);

        for(int i = 0 ;i<100;i++) {
            var c = new Customer();
            c.SetDesiredAmountOfGas(1+Math.round(Math.random()*100)%17);
            c.SetMaxAvailableMoney(100);
            station.AddCustomer(c);
            System.out.println("Tank 85: "+tank85.GetGallonsOrdered()+"Gallons Ordered");

//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        int v = 0;
        while(v<100){
            v++;
            Thread.sleep(100);
        }

        clock.pause();

        while(v<200){
            v++;
            Thread.sleep(100);
        }

        clock.start();



    }
}
