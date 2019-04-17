public class Main{

    public static void main(String[] args) throws InterruptedException {

        ITank tank85 = new Tank(1000);
        ITank tank89 = new Tank(1000);
        var time = TimeService.getInstance();
        time.subscribe(new TimeReporter());
        for (int x=0; x<10; x++)
        {
            ICustomer c = new Customer();

            c.SetDesiredAmountOfGas(1+Math.round(Math.random()*100)%17);
            c.SetMaxAvailableMoney(50);

            DriverPump driver = new DriverPump(tank85, tank89, c, time);
            driver.start();

        }
    }
}
