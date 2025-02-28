import java.util.function.Supplier;


// list of serveUntil
// Override some method
class SelfCounter extends Server {
    //private final int serID;         in Server
    //private final double serveUntil; in Server
    //private final boolean serving;   in Server
    //private final ImList<Customer> queue;   in Server
    //private final int qmax;          in Server
    private final ImList<Counter> counterList;
    private final int isServingNum;
    private final int numSelfCounter;
    private final int printServerID;

    SelfCounter(int serID, int numSelfCounter, int qmax) {
        super(serID, qmax, () -> 0.0);
        ImList<Counter> temCounterList = new ImList<Counter>();
        for (int i = 0; i < numSelfCounter; i++) {
            temCounterList = temCounterList.add(new Counter(serID + i));
        }
        //System.out.println(temCounterList);
        this.counterList = temCounterList;
        this.isServingNum = 0;
        this.numSelfCounter = numSelfCounter;
        this.printServerID = serID;
    }

    SelfCounter(int serID, double serveTil, int numSelfCounter, int qmax, ImList<Customer> queue, 
        int isServingNum, ImList<Counter> counterList, int printServerID) {
        
        super(serID, serveTil, qmax, queue, numSelfCounter > isServingNum, () -> 0.0);
        this.numSelfCounter = numSelfCounter;
        this.isServingNum = isServingNum;
        this.counterList = counterList;
        this.printServerID = printServerID;
    }


    @Override
    public boolean isServing() {
        return this.isServingNum == this.numSelfCounter;
    }

    @Override
    public String toString() {
        return "self-check " + this.printServerID;
    }

    @Override    
    public String waitAt() {
        return "self-check " + super.toString();
    }

    @Override
    public double getServeUntil() {
        double earliest = counterList.get(0).getServeUntil();
        for (Counter counter : this.counterList) {
            if (counter.getServeUntil() < earliest) {
                earliest = counter.getServeUntil();
            }
        }
        return earliest;
    }

    @Override  // not sure if can just return Server or SelfCounter
    public SelfCounter serveNewCus(Customer cust, double serveTil) {
        ImList<Counter> newCounterList = this.counterList;
        int printID = this.getSerID();
        for (int i = 0; i < this.numSelfCounter; i++) {
            //System.out.println("numSelfCounter: " + numSelfCounter);
            //System.out.println("size of counterList: " + this.counterList.size());
            //System.out.println("i: " + i);
            if (!this.counterList.get(i).isServing()) {
                Counter curCounter = counterList.get(i).serveNewCust(cust, serveTil);
                printID = curCounter.getCounterID();
                newCounterList = counterList.set(i, curCounter);
                break;
            }
        }
        return new SelfCounter(this.getSerID(), this.getServeUntil(), this.numSelfCounter, 
            this.getQmax(), this.getQueue(), this.isServingNum + 1, newCounterList, printID);
    }

    @Override
    public SelfCounter newWait(Customer cust) { 
        ImList<Customer> newqueue = this.getQueue().add(cust);
        return new SelfCounter(this.getSerID(), this.getServeUntil(), this.numSelfCounter, 
            this.getQmax(), newqueue, this.isServingNum, this.counterList, this.printServerID);
    }

    @Override
    public Server nextQueue() {
        ImList<Customer> newqueue = this.getQueue().remove(0);
        return new SelfCounter(this.getSerID(), this.getServeUntil(), this.numSelfCounter, 
            this.getQmax(), newqueue, this.isServingNum, this.counterList, this.printServerID);
    }

    @Override
    public SelfCounter makeMeRest(double restDur) {
        return this;
    }

    @Override
    public SelfCounter finishOne(int custID) {
        ImList<Counter> temCounterList = this.counterList;
        for (int i = 0; i < this.numSelfCounter; i++) {
            if (custID == this.counterList.get(i).getCustID()) {
                Counter newCounter = this.counterList.get(i).counterFinish();
                temCounterList = temCounterList.set(i, newCounter);
                break;
            }
        }
        return new SelfCounter(this.getSerID(), this.getServeUntil(), this.numSelfCounter, 
            this.getQmax(), this.getQueue(), this.isServingNum - 1, temCounterList, 
            this.printServerID);        
    }

}