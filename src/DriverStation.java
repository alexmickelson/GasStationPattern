public class DriverStation {
    public static void main(String[] args) throws InterruptedException {


        ITimeObservable clock = TimeService.getInstance();
        clock.subscribe(new TimeReporter());
        clock.ChangeSpeedOfProgram(300);
        ITank tank89 = new Tank(275);
        ITank tank85 = new Tank(275);




        ITruckService truckService = new TruckService(clock);
        var station = new GasStation(truckService,tank85,tank89,clock);
        CustomerGenerator customerGenerator = new CustomerGenerator(station,clock);
        customerGenerator.run();





//        int v = 0;
//        while(v<100){
//            v++;
//            Thread.sleep(100);
//        }
//
//        clock.pause();
//
//        while(v<200){
//            v++;
//            Thread.sleep(100);
//        }

        clock.start();



    }
}
