import java.util.*;

class Process {
    int pid, at, bt, rt;
    int ct, tat, wt;
    boolean completed;

    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt;
        this.completed = false;
    }
}

public class SJFPreemptive {

    static class Gantt {
        int pid;
        int time;

        Gantt(int pid, int time) {
            this.pid = pid;
            this.time = time;
        }
    }

    static void runSJF(ArrayList<Process> processes) {

        int n = processes.size();
        int completed = 0;
        int time = 0;

        ArrayList<Gantt> gantt = new ArrayList<>();

        while (completed < n) {

            int idx = -1;
            int minRT = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {

                Process p = processes.get(i);

                if (!p.completed && p.at <= time) {

                    if (p.rt < minRT) {
                        minRT = p.rt;
                        idx = i;
                    }
                }
            }

            // CPU Idle
            if (idx == -1) {

                if (gantt.isEmpty() || gantt.get(gantt.size() - 1).pid != -1)
                    gantt.add(new Gantt(-1, time));

                time++;
                continue;
            }

            Process cur = processes.get(idx);

            if (gantt.isEmpty() || gantt.get(gantt.size() - 1).pid != cur.pid)
                gantt.add(new Gantt(cur.pid, time));

            cur.rt--;
            time++;

            if (cur.rt == 0) {

                cur.completed = true;
                completed++;

                cur.ct = time;
                cur.tat = cur.ct - cur.at;
                cur.wt = cur.tat - cur.bt;
            }
        }

        gantt.add(new Gantt(-2, time));

        printGantt(gantt);
        printTable(processes);
    }

    static void printGantt(ArrayList<Gantt> gantt) {

        System.out.println("\nGantt Chart:");

        System.out.println("------------------------------------------");

        System.out.print("|");

        for (int i = 0; i < gantt.size() - 1; i++) {

            if (gantt.get(i).pid == -1)
                System.out.printf(" IDLE |");
            else
                System.out.printf(" P%-2d |", gantt.get(i).pid);
        }

        System.out.println();

        for (int i = 0; i < gantt.size() - 1; i++) {
            System.out.printf("%-6d", gantt.get(i).time);
        }

        System.out.println(gantt.get(gantt.size() - 1).time);

        System.out.println("------------------------------------------");
    }

    static void printTable(ArrayList<Process> processes) {

        double totalWT = 0;
        double totalTAT = 0;

        System.out.println("\nProcess Table");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s %-5s %-5s %-5s %-5s %-5s\n",
                "PID", "AT", "BT", "CT", "TAT", "WT");

        for (Process p : processes) {

            System.out.printf("%-5d %-5d %-5d %-5d %-5d %-5d\n",
                    p.pid, p.at, p.bt, p.ct, p.tat, p.wt);

            totalWT += p.wt;
            totalTAT += p.tat;
        }

        System.out.println("---------------------------------------------------------------");

        System.out.printf("Average Waiting Time    : %.2f\n", totalWT / processes.size());
        System.out.printf("Average Turnaround Time : %.2f\n", totalTAT / processes.size());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of test cases: ");
        int t = sc.nextInt();

        while (t-- > 0) {

            System.out.print("\nEnter number of processes: ");
            int n = sc.nextInt();

            ArrayList<Process> processes = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                System.out.print("Enter Arrival Time and Burst Time of P" + (i + 1) + ": ");

                int at = sc.nextInt();
                int bt = sc.nextInt();

                processes.add(new Process(i + 1, at, bt));
            }

            runSJF(processes);
        }

        sc.close();
    }
}