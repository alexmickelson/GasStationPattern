import java.util.ArrayList;
import java.util.Random;

public class PumpDriver implements Runnable{

    private ITimeObservable time;
    ITank tank89;
    ITank tank85;
    IPump p;
    ICustomer c;


    public PumpDriver(ITank tank85amount, ITank tank89amount, ICustomer customer, ITimeObservable time) {
        tank85 = tank85amount;
        tank89 = tank89amount;
        this.time = time;
        p = new Pump(tank85, tank89);
        time.subscribe(p);
        c= customer;
    }
    @Override
    public void run() {
        c.ReceiveReceipt(p.PumpTransaction(c));
        var r = c.GetReceipt();
        r.printReceipt();

        System.out.println("\n[Remaining Gas in Tank 85]: " + tank85.getLevel());
        System.out.println("[Remaining Gas in Tank 89]: " + tank89.getLevel());
    }
}
