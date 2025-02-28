import java.util.function.Supplier;

class Server {
    private final int serID;
    private final double serveUntil; // can be resting
    private final boolean serving; // can be resting
    private final ImList<Customer> queue;
    private final int qmax;
    private final Supplier<Double> restTimes;


    Server(int serID, double serveUntil, int qmax, boolean serving, 
        Supplier<Double> restTimes) {
        
        this.serID = serID;
        this.serveUntil = serveUntil;
        this.serving = serving;
        this.qmax = qmax;
        this.queue = new ImList<Customer>();
        this.restTimes = restTimes;
    }

    Server(int serID, double serveUntil, int qmax, ImList<Customer> queue, boolean serving,
        Supplier<Double> restTimes) {
        this.serID = serID;
        this.serveUntil = serveUntil;
        this.serving = serving;
        this.qmax = qmax;
        this.queue = queue;
        this.restTimes = restTimes;
    }

    Server(int serID, int qmax, Supplier<Double> restTimes) {
        this.serID = serID;
        this.serveUntil = 0.0;
        this.serving = false;
        this.qmax = qmax;
        this.queue = new ImList<Customer>();
        this.restTimes = restTimes;
    }

    public boolean isFull() {
        if (this.queue.size() < this.qmax) {
            return false;
        }
        return true;
    }

    public boolean isServing() {
        return this.serving;
    }

    protected int getQmax() {
        return this.qmax;
    }

    public int getSerID() {
        return this.serID;
    }

    public double getServeUntil() {
        return this.serveUntil;
    }

    public ImList<Customer> getQueue() {
        return this.queue;
    }

    public Server serveNewCus(Customer cust, double serveTil) {
        return new Server(this.serID, serveTil, this.qmax, this.queue, true, this.restTimes);
    }

    public Server makeMeRest(double restDur) {
        return new Server(this.serID, this.serveUntil + restDur, this.qmax, this.queue, true, 
            this.restTimes);
    }

    public String toString() {
        return Integer.toString(this.serID);
    }

    public String waitAt() {
        return this.toString();
    }

    public Server nextQueue() {
        ImList<Customer> newqueue = this.queue.remove(0);
        return new Server(this.serID, this.serveUntil, this.qmax, newqueue, false, this.restTimes);
    }

    public Server finishOne(int custID) {
        return new Server(this.serID, this.serveUntil, this.qmax, this.queue, false, 
            this.restTimes);
    }

    public Server newWait(Customer cust) {
        ImList<Customer> newqueue = this.queue.add(cust);
        return new Server(this.serID, this.serveUntil, this.qmax, newqueue, this.serving, 
            this.restTimes);
    }

    // also for SelfCounter
    public double getRestDur() {
        return restTimes.get();
    }
}
