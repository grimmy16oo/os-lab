import java.util.*;

class Process {
    int pid, at, bt;
    int tat, wt, ct;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
    }
}

public class demo {
    public static void main(String[] args) 
    {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = stdin.nextInt();

        Process[] p = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter AT, BT for process " + (i + 1) + ": ");
            int at = stdin.nextInt();
            int bt = stdin.nextInt();

            p[i] = new Process(i + 1, at, bt);
        }

        // Sort by arrival time (FCFS)
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        int time = 0;
        ArrayList<Integer> ganttTimes = new ArrayList<>();

        System.out.println("\n--- Gantt Chart ---");
        for (Process pr : p) 
            {
            if (time < pr.at) {
                    time = pr.at;        
            }

            ganttTimes.add(time);
            System.out.print("P" + pr.pid + " | ");
            time += pr.bt;

            pr.ct = time;
            pr.tat = pr.ct - pr.at;
            pr.wt = pr.tat - pr.bt;
        }
        ganttTimes.add(time);

        System.out.println("\n");
        for(int t : ganttTimes)
        {
            System.out.println(t + "\t");
        }
         System.out.println();

        int totalWT = 0;
        int totalTAT = 0;

        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        System.out.println("----------------------------------------");

        for (Process pr : p) {
            System.out.println(pr.pid + "\t" + pr.at + "\t" + pr.bt + "\t" + 
                             pr.ct + "\t" + pr.tat + "\t" + pr.wt);
            totalWT += pr.wt;
            totalTAT += pr.tat;
        }

        double avgWT = (double) totalWT / n;
        double avgTAT = (double) totalTAT / n;

        System.out.println("\nAverage Waiting Time: " + String.format("%.2f", avgWT));
        System.out.println("Average Turnaround Time: " + String.format("%.2f", avgTAT));
        
        stdin.close();
    }
}