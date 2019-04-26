import jdk.jfr.Frequency;

import java.sql.SQLOutput;
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
    public int customerslostduetoqueueoverfill;

    public int getFrequency(){
        return Frequency;
    }
    public void setFrequency(int f){
        Frequency = f;
    }

    public  CustomerGenerator(GasStation station, ITimeObservable clock){
        AverageGasDesired = 10;
        this.speed = 10;
        this.Frequency = 100;
        customerarrivallist = new LinkedList<>();
        this.clock = clock;
        this.clock.subscribe(this);
        this.station = station;
        customerslostduetoqueueoverfill = 0;
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
        cust.ArrivalTime = currenttick + random.nextInt(Frequency); //maybe tune this equation
        synchronized (customerarrivallist){

            customerarrivallist.add(cust);
        }

    }

    public void SetFrequency(int frequency){
        this.Frequency = frequency;
    }


    @Override
    public void update(int ticks) {
        currenttick = ticks;
        LinkedList<CustomerArrival> newlist = new LinkedList<CustomerArrival>();
        synchronized (customerarrivallist){
            for (CustomerArrival thing:customerarrivallist) {

                newlist.add(thing);

            }

            for (CustomerArrival thing:customerarrivallist) {
                if(thing.ArrivalTime == currenttick){
                    if(station.GetQueueLength() > 4){

                        newlist.remove(thing);
                        customerslostduetoqueueoverfill++;
                    }else{
                        station.AddCustomer(thing.customer);
                        newlist.remove(thing);
                    }

                }
            }
        }
        System.out.println("TOTAL customers lost: " + customerslostduetoqueueoverfill);
        System.out.println("Adding Customer to station");
        System.out.println("Size of list: " + customerarrivallist.size());
        customerarrivallist = newlist;
    }

    @Override
    public void changespeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {

        while(true){
            try {
                Thread.sleep(speed*Frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(!clock.isPaused()){
                GenerateCustomer();
            }
        }
    }

    public void SetAverageGasRequested(int i){
        this.AverageGasDesired = i;
    }
    public int GetAverageGasRequested(){
        return this.AverageGasDesired;
    }
}

