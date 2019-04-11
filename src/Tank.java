public class Tank implements ITank {
    double amount;

    public Tank(double initalAmountInGallons){
        this.amount = initalAmountInGallons;
    }
    public double RetrieveGasFromTank(double amount) {
        this.amount -= amount;
        return amount;
    }

    @Override
    public boolean GiveGasToTank(double amount) {
        this.amount += amount;
        return true;
    }

    @Override
    public double getLevel() {
        return amount;
    }
}
