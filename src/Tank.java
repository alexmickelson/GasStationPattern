public class Tank implements ITank {
    double amount;
    double MaxAmount;
    double GallonsOrdered;
    double GallonsUsed;
    int TotalNumberOfOrders;
    double GallonsDelivered;
    private boolean orderScheduled;
    private double currentOrderAmount;

    public Tank(double initalAmountInGallons){
        this.GallonsOrdered = 0;
        this.orderScheduled = false;
        //the inital amount has been recived because the tank starts full.
        this.GallonsDelivered = initalAmountInGallons;
        this.amount = initalAmountInGallons;
        MaxAmount = initalAmountInGallons;
        GallonsOrdered = initalAmountInGallons;
        TotalNumberOfOrders = 1;
        GallonsUsed = 0;
    }

    public double RetrieveGasFromTank(double amount) {
        synchronized (Tank.class) {
            if (this.amount - amount >= 0) {
                this.amount -= amount;
                GallonsUsed += amount;
                return amount;
            } else {
                amount = this.amount;
                this.amount = 0;
                GallonsUsed += amount;
                return amount;
            }
        }
    }

    @Override
    public boolean GiveGasToTank(double amount) {
        synchronized (Tank.class) {
            if (this.amount + amount <= this.MaxAmount) {
                this.amount += amount;
                return true;
            } else {
                return false;
            }
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

    @Override
    public void AddNewOrder() {
        TotalNumberOfOrders++;
    }

    @Override
    public int GetNumberOfOrders() {
        return TotalNumberOfOrders;
    }

    @Override
    public void AddGallonsDeliverd(double gallons) {
        GallonsDelivered += gallons;
    }

    @Override
    public double GetGallonsDeliverd() {
        return GallonsDelivered;
    }

    @Override
    public boolean IsOrderScheduld() {
        return orderScheduled;
    }

    @Override
    public void SetOrderSchedueld(boolean orderScheduled) {
        this.orderScheduled = orderScheduled;
    }

    @Override
    public double GetCurrentOrderAmount() {
        return currentOrderAmount;
    }

    @Override
    public void SetCurrentOrderAmount(double orderAmmount) {
        this.currentOrderAmount = orderAmmount;
    }


}
