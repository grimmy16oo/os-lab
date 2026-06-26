import java.util.*;

class Process {
    int pid, at, bt;
    int ct, tat, wt;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
    }
}

public class fcfs {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time of P" + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            p[i] = new Process(i + 1, at, bt);
        }

        // Sort by Arrival Time
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        int time = 0;

        System.out.println("\nGantt Chart:");
        System.out.print("|");

        ArrayList<Integer> times = new ArrayList<>();

        for (Process pr : p) {

            if (time < pr.at)
                time = pr.at;   // CPU waits if no process has arrived

            times.add(time);

            System.out.print(" P" + pr.pid + " |");

            time += pr.bt;

            pr.ct = time;
            pr.tat = pr.ct - pr.at;
            pr.wt = pr.tat - pr.bt;
        }

        times.add(time);

        System.out.println();

        for (int t : times)
            System.out.print(t + "\t");

        System.out.println();

        System.out.println("\n-----------------------------------------------------");
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");

        double totalWT = 0;
        double totalTAT = 0;

        for (Process pr : p) {

            System.out.println("P" + pr.pid + "\t"
                    + pr.at + "\t"
                    + pr.bt + "\t"
                    + pr.ct + "\t"
                    + pr.tat + "\t"
                    + pr.wt);

            totalWT += pr.wt;
            totalTAT += pr.tat;
        }

        System.out.println("-----------------------------------------------");

        System.out.printf("Average Waiting Time = %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT / n);

        sc.close();
    }
}