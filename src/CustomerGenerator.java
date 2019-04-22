import jdk.jfr.Frequency;

import java.util.LinkedList;
import java.util.Random;
public class CustomerGenerator implements ITimeObserver, Runnable {
    private LinkedList<CustomerArrival> customerarrivallist;
    private GasStation station;
    private int AverageGasDesired;
    private int Frequency;
    private int speed;
    private int currenttick;
    private ITimeObservable clock;

    public  CustomerGenerator(GasStation station, ITimeObservable clock, int frequency){
        speed = frequency;
        this.Frequency = frequency;
        customerarrivallist = new LinkedList<>();
        this.clock = clock;
        this.clock.subscribe(this);
        this.station = station;
    }

    public void GenerateCustomer(){
        Random random = new Random();
        Customer customer = new Customer();
        customer.SetDesiredAmountOfGas(random.nextInt(20)+AverageGasDesired);
        customer.SetMaxAvailableMoney(random.nextInt(100)+20);

        switch ((int) (Math.round(Math.random()*100)%3)){
            case 0:
            {
                customer.SetDesiredGrade(GradeEnum.GRADE_85);
                break;
            }
            case 1:
            {
                customer.SetDesiredGrade(GradeEnum.GRADE_87);
                break;
            }
            case 2:
            {
                customer.SetDesiredGrade(GradeEnum.GRADE_89);
                break;
            }
        }

        switch ((int) (Math.round(Math.random()*100)%2)){
            case 0:
            {
                customer.SetMoneyType(CurrencyEnum.CASH);
                break;
            }
            case 1:
            {
                customer.SetMoneyType(CurrencyEnum.CREDIT_CARD);
                break;
            }
        }

        CustomerArrival cust = new  CustomerArrival();
        cust.customer = customer;
        cust.ArrivalTime = Frequency + random.nextInt(Frequency/2);

        customerarrivallist.add(cust);

    }

    public void SetFrequency(int frequency){
        this.Frequency = frequency;
    }


    @Override
    public void update(int ticks) {
        currenttick = ticks;
        LinkedList<CustomerArrival> newlist = new LinkedList<CustomerArrival>();

        for (CustomerArrival thing:customerarrivallist) {
            newlist.add(thing);
        }

        for (CustomerArrival thing:customerarrivallist) {
            if(thing.ArrivalTime == currenttick){
                station.AddCustomer(thing.customer);
                newlist.remove(thing);
            }
        }

        customerarrivallist = newlist;
    }

    @Override
    public void changespeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while(true){
            if(!clock.isPaused()){
                GenerateCustomer();

                try {
                    Thread.sleep(speed*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
