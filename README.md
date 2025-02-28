# Discrete-Event-Simulator(OOP) - Java
This project serves as a practice on object-oriented programming. It should be the neatest programming project. All of the codes are written myself.
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
- If there is no waiting customer, then the server becomes idle again.

To run the program, we run on Main.java.
Sample input for $Main.java$:
1st input: number of Human Servers, 2nd input: number of Self-Checkout Counters, 3rd input: max queue length, 4th input: probability that a Human Servers take rest
Then, input a sequence of customers arrival time. (no constraint on the size) 
Example: ten customers with one human server, two self-checkout counters and probability of rest set to 0.5.
```
$ cat 1.in
1 2 2 0.5
0.000000
0.313508
1.204910
2.776499
3.876961
3.909737
9.006391
9.043361
9.105379
9.159630
```
Output:
```
$ cat 4.in | java Main
0.000 1 arrives
0.000 1 serves by 1
0.314 2 arrives
0.314 2 serves by self-check 2
1.000 1 done serving by 1
1.205 3 arrives
1.205 3 serves by 1
1.314 2 done serving by self-check 2
2.205 3 done serving by 1
2.776 4 arrives
2.776 4 serves by self-check 2
3.776 4 done serving by self-check 2
3.877 5 arrives
3.877 5 serves by self-check 2
3.910 6 arrives
3.910 6 serves by self-check 3
4.877 5 done serving by self-check 2
4.910 6 done serving by self-check 3
9.006 7 arrives
9.006 7 serves by 1
9.043 8 arrives
9.043 8 serves by self-check 2
9.105 9 arrives
9.105 9 serves by self-check 3
9.160 10 arrives
9.160 10 waits at 1
10.006 7 done serving by 1
10.043 8 done serving by self-check 2
10.105 9 done serving by self-check 3
10.854 10 serves by 1
11.854 10 done serving by 1
[0.169 10 0]
```
[average wait time, no. of served customer, no. of unserved customer]
