class Done extends Event {
    //private final Customer cust;
    //private final double time;
    private final int serverID;
    private final String output;

    Done(Customer cust, double time, int serverID, String output) {
        super(cust, time);
        this.serverID = serverID;
        this.output = output;

    }

    @Override
    public String toString() {
        return String.format("%.3f %s done serving by %s\n", this.getTime(), this.getCustomer(), 
            this.output);
    }
     
     
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        Server server = serverList.get(serverID - 1);
        double restDur = server.getRestDur();
        server = server.makeMeRest(restDur);
        serverList = serverList.set(serverID - 1, server);

        Event next = new Rest(this.getCustomer(), this.serverID, this.getTime() + restDur);
        return new Pair<Event, ImList<Server>>(next, serverList);
    }

    public Pair<Double, Integer> countServed(double totalWait, int numLeft) {
        return new Pair<Double, Integer>(totalWait, numLeft);
    }


}