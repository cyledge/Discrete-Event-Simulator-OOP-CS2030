class Serve extends Event {
    //private final Customer cust;
    //private final double time;
    private final Server ser;
    private final String output;
    private final double newTime;

    Serve(Customer cust, Server ser, double time) {
        super(cust, time);
        double serTime = cust.getServiceTime();
        this.newTime = time + serTime;
        this.ser = ser.serveNewCus(this.getCustomer(), newTime);
        this.output = this.ser.toString();

    }

    @Override
    public String toString() {
        return String.format("%.3f %s serves by %s\n", this.getTime(), this.getCustomer(), 
            this.output);
    }
     
     
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        int serverID = this.ser.getSerID();
        
        serverList = serverList.set(serverID - 1, this.ser);
        Event next = new Done(this.getCustomer(), this.newTime, serverID, this.output);
        return new Pair<Event, ImList<Server>>(next, serverList);
    }

    public Pair<Double, Integer> countServed(double totalWait, int numLeft) {
        return new Pair<Double, Integer>(totalWait, numLeft);
    }

}