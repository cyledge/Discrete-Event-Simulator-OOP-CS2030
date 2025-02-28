# Discrete-Event-Simulator(OOP) - Java
-	There are n Human Servers with its own queue and k Self-Checkout Counter sharing one queue 
-	Each server can serve one customer at a time.
-	Human Servers are allowed to take occasional breaks. When a Human Server finishes serving a customer, there is a chance (p) that the server takes a rest for a certain amount of time which is random and only determined at the time it rests. During the break, the server does not serve the next waiting customer. Upon returning from the break, the server serves the next customer in the queue (if any) immediately.
-	Unlike Human Servers, Self-Checkout Counters do not rest.	
-	Time taken to serve a customer is random and only determined at the time the Server serve as well.
-	When a customer arrives:
  -	the servers are scanned from 1 to n to find the first one that is idle (not serving any customer). This server starts serving the customer immediately (SERVE event).
  -	if all Human Servers are serving customers, then the customer goes to Self-Checkout Counter
  -	if all servers are serving customers, then the customer that just arrived scans the queues from 1 to n, joins the first queue that is not full (not necessarily the shortest) and waits at the end of the queue.
  -	if all servers are serving customers and all queues are full of waiting customers, then a new customer that arrives will just leaves.
- if there is no waiting customer, then the server becomes idle again.
Sample input for $Main.java$
