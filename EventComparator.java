import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    public int compare(Event e1, Event e2) {
        //System.out.println("e1 " + e1.getTime() + " e2 " + e2.getTime());
        if (e1.getTime() < e2.getTime()) {
            return -1;
        } else if (e1.getTime() == e2.getTime()) {
            return e1.getCustomer().getCustID() - e2.getCustomer().getCustID();
            //return 0;
        } else {
            return 1;
        }
    }
}