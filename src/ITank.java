public interface ITank {
    //Review this one
    double RetrieveGasFromTank(double amount);

    boolean GiveGasToTank(double amount);
    double getLevel();
    double getMaxAmount();
    public void AddGallonsOrdered(double gallonsOrdered);
    public double GetGallonsOrdered();
    public double GetGallonsUsed();
    public void AddNewOrder();
    public int GetNumberOfOrders();
    public void AddGallonsDeliverd(double gallons);
    public double GetGallonsDeliverd();
    public boolean IsOrderScheduld();
    public void SetOrderSchedueld(boolean orderScheduled);
    public double GetCurrentOrderAmount();
    public void SetCurrentOrderAmount(double orderAmmount);



}
