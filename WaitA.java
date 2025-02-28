class WaitA extends Event {
    //private final Customer cust;
    //private final double time;
    private final int serverID;
    private final double waited;

    WaitA(Customer cust, double time, int serverID, double waited) {
        super(cust, time);
        this.serverID = serverID;
        this.waited = waited;

    }

    @Override
    public String toString() {
        return "";
    }
     
     
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        //return Serve
        Server server = serverList.get(serverID - 1);
        if (! server.isServing()) {
            server = server.nextQueue();
            serverList = serverList.set(serverID - 1, server);
            Event next = new Serve(this.getCustomer(), server, this.getTime());
            return new Pair<Event, ImList<Server>>(next, serverList);
        } else {  //return WaitA again
            double waited = server.getServeUntil() - this.getTime();
            Event next = new WaitA(this.getCustomer(), server.getServeUntil(), this.serverID, 
                waited);
            return new Pair<Event, ImList<Server>>(next, serverList);
        }
    }


    public Pair<Double, Integer> countServed(double totalWait, int numLeft) {
        return new Pair<Double, Integer>(totalWait + this.waited, numLeft);
    }

}