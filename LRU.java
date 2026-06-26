import java.util.*;

public class LRU {

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

        ArrayList<Integer> memory = new ArrayList<>();

        int faults = 0;

        for (int page : pages) {

            // Page Fault
            if (!memory.contains(page)) {
                faults++;

                // Memory Full
                if (memory.size() == frames)
                    memory.remove(0);

                memory.add(page);
            }

            // Page Hit
            else {
                memory.remove(Integer.valueOf(page));
                memory.add(page);
            }
            System.out.println(memory);
        }

        System.out.println("Total Page Faults = " + faults);

        sc.close();
    }
}