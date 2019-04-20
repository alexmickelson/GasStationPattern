public interface ITruckService extends ITimeObserver {
    void CallTruck(ITank tank, double amt);
    void SetMinimumWaitTime(int min);
    void SetPumpRate(double rate);
}
