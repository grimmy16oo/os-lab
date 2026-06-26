import java.util.*;

class Process {
    String name;
    int burstTime;
    int remainingTime;
    int startTime = -1;
    int endTime = 0;
    int waitingTime = 0;

    Process(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobin {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        ArrayList<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Burst Time of P" + (i + 1) + ": ");
            int bt = sc.nextInt();
            processes.add(new Process("P" + (i + 1), bt));
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        Queue<Process> queue = new LinkedList<>(processes);

        int time = 0;

        while (!queue.isEmpty()) {

            Process p = queue.poll();

            // First time getting CPU
            if (p.startTime == -1)
                p.startTime = time;

            if (p.remainingTime > tq) {

                time += tq;
                p.remainingTime -= tq;

                queue.add(p);

            } else {

                time += p.remainingTime;
                p.remainingTime = 0;

                p.endTime = time;
                p.waitingTime = p.endTime - p.burstTime;
            }
        }

        System.out.println("\nProcess\tBurst Time\tStart Time\tEnd Time\tWaiting Time");

        for (Process p : processes) {
            System.out.printf("%s\t%d\t\t%d\t\t%d\t\t%d\n",
                    p.name,
                    p.burstTime,
                    p.startTime,
                    p.endTime,
                    p.waitingTime);
        }

        sc.close();
    }
}