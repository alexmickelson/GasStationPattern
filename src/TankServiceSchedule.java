public class TankServiceSchedule{
    public ITank tank;
    public int serviceTime;
    public double amt;

    TankServiceSchedule(ITank tank, int serviceTime, double amt){
        this.tank = tank;
        this.serviceTime = serviceTime;
        this.amt = amt;

    }

    ITank getTank(){
        return tank;
    }

    int getTime(){
        return serviceTime;
    }

    double getAmt(){
        return amt;
    }

}