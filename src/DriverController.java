public class DriverController {
    public static void main(String[] args){
        ITimeObservable clock = TimeService.getInstance();
        clock.subscribe(new TimeReporter());
        clock.ChangeSpeedOfProgram(300);
        ITank tank89 = new Tank(275);
        ITank tank85 = new Tank(275);




        ITruckService truckService = new TruckService(clock);
        truckService.SetMinimumWaitTime(0);
        var station = new GasStation(truckService,tank85,tank89,clock);
        var view = new SwingStationFrame();
        new Controller(station,clock,tank85,tank89,truckService,view);

    }
}
