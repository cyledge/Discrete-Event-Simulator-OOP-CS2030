abstract class Event {
    private final Customer cust;
    private final double time;

    Event(Customer cust, double time) {
        this.cust = cust;
        this.time = time;
        //this.time = cust.getArrTime();
    }



    public Customer getCustomer() {             
        return this.cust;
    }

    public double getTime() {
        return this.time;
    }

    abstract Pair<Event, ImList<Server>> nextEvent(ImList<Server> severList);

    abstract Pair<Double, Integer> countServed(double totalWait, int numLeft);
    // count done or leave

}
