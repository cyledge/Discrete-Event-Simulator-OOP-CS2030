class Rest extends Event {
    //private final Customer cust;
    //private final double time;
    private final int serverID;

    Rest(Customer cust, int serverID, double time) { // time is rest end
        super(cust, time);
        this.serverID = serverID;
        //this.time = time;
    }

    @Override
    public String toString() { // for debugging only
        return "";
        //return String.format("%.3f rest end after %s finished, server %s rest\n", 
        //    this.getTime(), this.getCustomer(), this.serverID);
    }

    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        Server server = serverList.get(serverID - 1);
        server = server.finishOne(this.getCustomer().getCustID());
        serverList = serverList.set(serverID - 1, server); 
        return new Pair<Event, ImList<Server>>(this, serverList);
    }



    public Pair<Double, Integer> countServed(double totalWait, int numLeft) {
        return new Pair<Double, Integer>(totalWait, numLeft);
    }

}
