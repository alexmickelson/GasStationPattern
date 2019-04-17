public class Main{

    public static void main(String[] args) throws InterruptedException {

        ITank tank85 = new Tank(1000);
        ITank tank89 = new Tank(1000);
        var time = TimeService.getInstance();
        for (int x=0; x<10; x++)
        {
            ICustomer c = new Customer();

            //Set how much costumer wants
            //Gallons
            c.SetDesiredAmountOfGas(1+Math.round(Math.random()*100)%17);
            c.SetMaxAvailableMoney(50);


            PumpDriver driver = new PumpDriver(tank85, tank89, c, time);
            driver.run();

            System.out.println("\n\n> Started Thread:" + x);
            Thread.sleep(2000);
        }
    }
}
