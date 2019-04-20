public interface ITank {
    //Review this one
    double RetrieveGasFromTank(double amount);

    boolean GiveGasToTank(double amount);
    double getLevel();
    double getMaxAmount();
    public void AddGallonsOrdered(double gallonsOrdered);
    public double GetGallonsOrdered();


    public double GetGallonsUsed();



}
