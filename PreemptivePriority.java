import java.util.*;

class Process {
    int pid, at, bt, rt, priority;
    int ct, tat, wt;
    boolean completed;

    Process(int pid, int at, int bt, int priority) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
        this.priority = priority;
        this.completed = false;
    }
}

public class PreemptivePriority {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time, Burst Time and Priority of P" + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            int pr = sc.nextInt();

            p[i] = new Process(i + 1, at, bt, pr);
        }

        int completed = 0;
        int time = 0;

        ArrayList<Integer> gantt = new ArrayList<>();
        ArrayList<Integer> startTime = new ArrayList<>();

        while (completed < n) {

            int idx = -1;
            int highestPriority = Integer.MAX_VALUE;

            // Find highest priority process
            for (int i = 0; i < n; i++) {

                if (!p[i].completed && p[i].at <= time) {

                    if (p[i].priority < highestPriority) {
                        highestPriority = p[i].priority;
                        idx = i;
                    }
                    else if (p[i].priority == highestPriority) {

                        // Tie -> Smaller Remaining Time
                        if (idx == -1 || p[i].rt < p[idx].rt)
                            idx = i;
                    }
                }
            }

            // CPU Idle
            if (idx == -1) {
                time++;
                continue;
            }

            // Add to Gantt Chart only if CPU switches
            if (gantt.isEmpty() || gantt.get(gantt.size() - 1) != p[idx].pid) {
                gantt.add(p[idx].pid);
                startTime.add(time);
            }

            // Execute for 1 unit
            p[idx].rt--;
            time++;

            if (p[idx].rt == 0) {

                p[idx].completed = true;
                completed++;

                p[idx].ct = time;
                p[idx].tat = p[idx].ct - p[idx].at;
                p[idx].wt = p[idx].tat - p[idx].bt;
            }
        }

        // Gantt Chart
        System.out.println("\nGantt Chart:");
        System.out.print("|");

        for (int id : gantt)
            System.out.print(" P" + id + " |");

        System.out.println();

        for (int t : startTime)
            System.out.print(t + "\t");

        System.out.println(time);

        // Process Table
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("PID\tAT\tBT\tPR\tCT\tTAT\tWT");

        double totalWT = 0;
        double totalTAT = 0;

        for (Process pr : p) {

            System.out.println("P" + pr.pid + "\t"
                    + pr.at + "\t"
                    + pr.bt + "\t"
                    + pr.priority + "\t"
                    + pr.ct + "\t"
                    + pr.tat + "\t"
                    + pr.wt);

            totalWT += pr.wt;
            totalTAT += pr.tat;
        }

        System.out.println("----------------------------------------------------------------");

        System.out.printf("Average Waiting Time = %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time = %.2f\n", totalTAT / n);

        sc.close();
    }
}