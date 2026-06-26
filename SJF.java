import java.util.*;

class Process {
    int pid, at, bt;
    int ct, tat, wt;
    boolean completed;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        completed = false;
    }
}

public class SJF {

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

        int completed = 0;
        int time = 0;

        ArrayList<Integer> gantt = new ArrayList<>();
        ArrayList<Integer> startTime = new ArrayList<>();

        while (completed < n) 
        {

            int idx = -1;
            int minBT = Integer.MAX_VALUE;

            // Find process with shortest burst time
            for (int i = 0; i < n; i++) 
            {

                if (!p[i].completed && p[i].at <= time && p[i].bt < minBT) {
                    minBT = p[i].bt;
                    idx = i;
                }
            }

            // CPU Idle(means user starts from 1)
            if (idx == -1) {
                time++;
                continue;
            }

            gantt.add(p[idx].pid);
            startTime.add(time);

            time += p[idx].bt;

            p[idx].ct = time;
            p[idx].tat = p[idx].ct - p[idx].at;
            p[idx].wt = p[idx].tat - p[idx].bt;

            p[idx].completed = true;
            completed++;
        }

        // Gantt Chart
        System.out.println("\nGantt Chart");

        // Top line
        System.out.print("|");
        for (int id : gantt) {
            System.out.printf(" P%d |", id);
        }
        System.out.println();

        // Bottom line (time)
        for (int t : startTime) {
            System.out.printf("%-5d", t);
        }
        System.out.println(time);

        // Process Table
        double totalWT = 0;
        double totalTAT = 0;

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");

        for (int i = 0; i < n; i++) {

            System.out.println("P" + p[i].pid + "\t"
                    + p[i].at + "\t"
                    + p[i].bt + "\t"
                    + p[i].ct + "\t"
                    + p[i].tat + "\t"
                    + p[i].wt);

            totalWT += p[i].wt;
            totalTAT += p[i].tat;
        }

        System.out.printf("\nAverage Waiting Time = %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT / n);

        sc.close();
    }
}