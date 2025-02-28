class Counter {
    private final double serveUntil;
    private final int counterID;
    private final int custID;
    private final boolean serving;

    Counter(int custID, int counterID, double serveUntil) {
        this.custID = custID;
        this.counterID = counterID;
        this.serveUntil = serveUntil;
        this.serving = true;
    }

    Counter(int counterID) {
        this.custID = 0;
        this.counterID = counterID;
        this.serveUntil = 0;
        this.serving = false;
    }

    public double getServeUntil() {
        return this.serveUntil;
    }

    public boolean isServing() {
        return this.serving;
    }

    public int getCounterID() {
        return this.counterID;
    }

    public int getCustID() {
        return this.custID;
    }

    public Counter counterFinish() {
        return new Counter(this.counterID);
    }

    public Counter serveNewCust(Customer customer, double serveUntil) {
        return new Counter(customer.getCustID(), this.counterID, serveUntil);
    }




}