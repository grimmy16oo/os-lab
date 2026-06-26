import java.util.*;

public class Optimal {

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

        for (int i = 0; i < n; i++) {

            int page = pages[i];

            // Page Hit
            if (memory.contains(page)) {
                System.out.println(memory);
                continue;
            }

            faults++;

            // Empty frame available
            if (memory.size() < frames) {
                memory.add(page);
            }

            // Memory full
            else {

                int index = -1;
                int farthest = i + 1;

                for (int j = 0; j < memory.size(); j++) {

                    int k;

                    // Find next use of memory[j]
                    for (k = i + 1; k < n; k++) {
                        if (pages[k] == memory.get(j))
                            break;
                    }

                    // Page never used again
                    if (k == n) {
                        index = j;
                        break;
                    }

                    // Farthest future use
                    if (k > farthest) {
                        farthest = k;
                        index = j;
                    }
                }

                memory.set(index, page);
            }

            System.out.println(memory);
        }

        System.out.println("Total Page Faults = " + faults);

        sc.close();
    }
}