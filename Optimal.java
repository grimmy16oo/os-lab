import java.util.*;

public class Optimal {

    static int findVictim(int[] pages, ArrayList<Integer> frame, int index) {

        int farthest = index;
        int victim = -1;

        for (int i = 0; i < frame.size(); i++) {

            int j;

            for (j = index; j < pages.length; j++) {

                if (frame.get(i) == pages[j]) {

                    if (j > farthest) {

                        farthest = j;
                        victim = i;
                    }

                    break;
                }
            }

            if (j == pages.length)
                return i;
        }

        return victim == -1 ? 0 : victim;
    }

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

            if (memory.contains(pages[i])) {

                System.out.println(memory);
                continue;
            }

            faults++;

            if (memory.size() < frames) {

                memory.add(pages[i]);

            } else {

                int pos = findVictim(pages, memory, i + 1);
                memory.set(pos, pages[i]);
            }

            System.out.println(memory);
        }

        System.out.println("Total Page Faults = " + faults);
    }
}