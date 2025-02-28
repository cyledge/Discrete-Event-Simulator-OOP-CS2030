class Wait extends Event {
    //private final Customer cust;
    //private final double time;
    private final Server ser;

    Wait(Customer cust, double time, Server ser) {
        super(cust, time);
        this.ser = ser;
    }

    @Override
    public String toString() {
        return String.format("%.3f %s waits at %s\n", this.getTime(), this.getCustomer(), 
            this.ser.waitAt());
    }
     
     
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        int serverID = this.ser.getSerID();
        Server server = this.ser.newWait(this.getCustomer()); // should move this to Wait
        serverList = serverList.set(server.getSerID() - 1, server); // this also
        double waited = server.getServeUntil() - this.getTime();
        Event next = new WaitA(this.getCustomer(), server.getServeUntil(), serverID, waited);
        return new Pair<Event, ImList<Server>>(next, serverList);

    }

    public Pair<Double, Integer> countServed(double totalWait, int numLeft) {
        return new Pair<Double, Integer>(totalWait, numLeft);
    }
    //if the server of that Wait is UseUntil > Wait/WaitA time --> WaitA
}
