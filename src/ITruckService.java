import java.util.LinkedList;

public interface ITruckService extends ITimeObserver {
    void CallTruck(ITank tank, double amt);
    void SetMinimumWaitTime(int min);
    void SetPumpRate(double rate);
    public int GetMinimumWaitTime();
    LinkedList<TankServiceSchedule> GetServiceSchedule();
}
