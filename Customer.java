import java.util.function.Supplier;

class Customer {
    private final int cusID;
    private final double arrTime;
    private final Supplier<Double> serviceTime;

    public Customer(int cusID, double arrTime, Supplier<Double> serviceTime) {
        this.cusID = cusID;
        this.arrTime = arrTime;
        this.serviceTime = serviceTime;
    }

    public int getCustID() {
        return this.cusID;
    }

    public double getArrTime() {
        return this.arrTime;
    }


    public Double getServiceTime() {
        return this.serviceTime.get();
    }


    public String toString() {
        return Integer.toString(cusID);
    }
}
