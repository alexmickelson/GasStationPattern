import java.util.Random;

public class MockDriver{

    Tank tank89;
    Tank tank85;
    Pump p;


    public MockDriver(double tank85amount, double tank89amount) {
        tank85 = new Tank(tank85amount);
        tank89 = new Tank(tank89amount);
        p = new Pump(tank85, tank89);
    }
    public void doStuff(){

        Customer c = new Customer();

        //Set how much costumer wants
        //Gallons
        c.DesiredAmountOfGas(1+Math.round(Math.random()*100)%17);

        p.PumpTransaction(c);
        c.ReceiveReceipt(p.getReceipt());

        System.out.println("\n[Remaining Gas in Tank 85]: " + tank85.getLevel());
        System.out.println("[Remaining Gas in Tank 89]: " + tank89.getLevel());
    }
}
