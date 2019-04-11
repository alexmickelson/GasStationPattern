public class MockDriver {
    public MockDriver() {

    }
    public void doStuff(){
        Tank tank89 = new Tank(50);
        Tank tank85 = new Tank(50);
        Pump p = new Pump(tank85, tank89);
        Customer c = new Customer();

        //Set how much costumer wants
        //Gallons
        c.DesiredAmountOfGas(25);

        p.PumpTransaction(c);
        c.ReceiveReceipt(p.getReceipt());
    }
}
