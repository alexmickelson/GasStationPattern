public class Tank implements ITank {
    double amount;
    double MaxAmount;
    double GallonsOrdered;
    double GallonsUsed;


    public Tank(double initalAmountInGallons){
        this.amount = initalAmountInGallons;
        MaxAmount = initalAmountInGallons;
        GallonsOrdered = 0;
        GallonsUsed = 0;
    }

    public double RetrieveGasFromTank(double amount) {

        //if (this.amount < 2000)
        //{
        //    //Call for the trucks
        //    truckService.SendTruck(6000);
        //}
        if(this.amount - amount >= 0)
        {
            this.amount -= amount;
            GallonsUsed += amount;
            return amount;
        }
        else{
            amount = this.amount;
            this.amount = 0;
            GallonsUsed += amount;
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

    @Override
    public double getMaxAmount(){
        return MaxAmount;
    }
    @Override
    public void AddGallonsOrdered(double gallonsOrdered){
        this.GallonsOrdered = this.GallonsOrdered + gallonsOrdered;
    }
    @Override
    public double GetGallonsOrdered(){
        return this.GallonsOrdered;
    }
    @Override
    public double GetGallonsUsed(){
        return this.GallonsUsed;
    }





}
