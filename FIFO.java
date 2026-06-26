import java.util.*;

public class FIFO {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];

        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> memory = new HashSet<>();

        int faults = 0;

        for (int page : pages) {

            if (!memory.contains(page)) {

                faults++;

                if (memory.size() == frames) {

                    int removed = queue.poll();
                    memory.remove(removed);
                }

                memory.add(page);
                queue.add(page);
            }

            System.out.println(memory);
        }

        System.out.println("Total Page Faults = " + faults);
    }
}