import java.util.function.Supplier;


class Simulator {
    private final int numServer;
    private final int numSelfCounter;
    private final ImList<Server> serverList;
    private final ImList<Customer> cusList;
    private final int qmax;
    

    public Simulator(int numServer, int numSelfCounter, int qmax, ImList<Double> arrivalTimes, 
        Supplier<Double> serviceTimes, Supplier<Double> restTimes) {

        this.numServer = numServer;
        this.numSelfCounter = numSelfCounter;
        this.qmax = qmax;

        ImList<Server> temServerList = new ImList<Server>();
        for (int i = 1; i <= numServer; i++) {
            temServerList = temServerList.add(new Server(i, qmax, restTimes));
        }
        if (numSelfCounter > 0) {
            temServerList = temServerList.add(new SelfCounter(numServer + 1, numSelfCounter, qmax));
        }
        this.serverList = temServerList;

        ImList<Customer> temCusList = new ImList<Customer>();
        for (int i = 1; i <= arrivalTimes.size(); i++) {
            temCusList = temCusList.add(new Customer(i, arrivalTimes.get(i - 1), serviceTimes)); 
        }

        this.cusList = temCusList;
    }
    


    private String processEvents(PQ<Event> eventPQ, ImList<Server> severs, 
        ImList<Customer> customers) {

        String output = "";
        int numCust = customers.size();
        int numLeft = 0;
        double waited = 0.0;


        while (!eventPQ.isEmpty()) {

            Pair<Event, PQ<Event>> pollPair = eventPQ.poll();
            Event event = pollPair.first();
            eventPQ = pollPair.second();
            
            Pair<Double, Integer> counting = event.countServed(waited, numLeft);
            waited = counting.first();
            numLeft = counting.second();

            output += event.toString();
            
            Pair<Event, ImList<Server>> nextPair = event.nextEvent(severs);
            Event nextEvent = nextPair.first();
            severs = nextPair.second();
            
            if (event != nextEvent) {
                eventPQ = eventPQ.add(nextEvent);
            }


        }

        int numServed = numCust - numLeft;
        double avgWait;
        if (numServed == 0) {
            avgWait = 0;
        } else {
            avgWait = waited / numServed;
        }
        output += String.format("[%.3f %d %d]", avgWait, numServed, numLeft);
        //output += "[" + avgWait + " " + numServed + " " + numLeft + "]";
        return output;
    }

   

    public String simulate() {
        String result;
        //ImList<Server> servers = this.serverList;
        PQ<Event> eventPQ = new PQ<Event>(new EventComparator()); //Havent implement comparator
        for (Customer cust : cusList) {
            eventPQ = eventPQ.add(new Arrive(cust));
        }

        result = processEvents(eventPQ, this.serverList, this.cusList);
        return result;
    }
}
