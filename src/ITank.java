public interface ITank {
    //Review this one
    double RetrieveGasFromTank(double amount);

    boolean GiveGasToTank(double amount);
    double getLevel();


}
