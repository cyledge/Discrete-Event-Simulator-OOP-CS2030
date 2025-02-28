class Leave extends Event {
    //private final Customer cust;
    //private final double time;

    Leave(Customer cust) {
        super(cust, cust.getArrTime());
        //this.time = cust.getArrTime();

    }

    @Override
    public String toString() {
        return String.format("%.3f %s leaves\n", this.getTime(), this.getCustomer());
    }
     
     
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        return new Pair<Event, ImList<Server>>(this, serverList);
    }

    public Pair<Double, Integer> countServed(double totalWait, int numLeft) {
        return new Pair<Double, Integer>(totalWait, numLeft + 1);
    }

}