public class TruckService implements ITruckService {
    double AmtRequested;
    @Override
    public boolean SendTruck(double AmtRequested) {
        this.AmtRequested = AmtRequested;
        return true;
    }

    @Override
    public double GetTruckGas() {
        return AmtRequested;
    }
}
