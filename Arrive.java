class Arrive extends Event {
    //private final Customer cust;
    //private final double time;

    Arrive(Customer cust) {
        super(cust, cust.getArrTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f %s arrives\n", this.getTime(), this.getCustomer());
    }
     
    //private Event serverNow(ImList<Server> serverList) {


     
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        boolean haveServer = false;
        Event next = this;
        for (Server server : serverList) {
            if (!server.isServing()) {
                //int serverID = server.getSerID();
                next = new Serve(this.getCustomer(), server, this.getTime());
                haveServer = true;
                break;
            }
        }
        if (! haveServer) {
            for (Server server : serverList) {
                if (!server.isFull()) {
                    int serverID = server.getSerID();
                    next = new Wait(this.getCustomer(), this.getTime(), server);
                    haveServer = true;
                    break;
                }
            }
        }
        if (! haveServer) {
            next = new Leave(this.getCustomer());

        }
        return new Pair<Event, ImList<Server>>(next, serverList);
    }

    public Pair<Double, Integer> countServed(double totalWait, int numLeft) {
        return new Pair<Double, Integer>(totalWait, numLeft);
    }

}

