public class Tank implements ITank {
    double amount;

    double MaxAmount;
    ITruckService truckService;

    public Tank(double initalAmountInGallons){
        this.amount = initalAmountInGallons;
        truckService = new TruckService();
        MaxAmount = 1000;
    }

    public double RetrieveGasFromTank(double amount) {

        if (this.amount < 2000)
        {
            //Call for the trucks
            truckService.SendTruck(6000);
        }
        if(this.amount - amount >= 0)
        {
            this.amount -= amount;
            return amount;
        }
        else{
            amount = this.amount;
            this.amount = 0;
            return amount;
        }
    }

    @Override
    public boolean GiveGasToTank(double amount) {
        if(this.amount + amount <= this.MaxAmount){
            this.amount += amount;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public double getLevel() {
        return amount;
    }
}
