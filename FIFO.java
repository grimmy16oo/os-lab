import java.util.*;

public class FIFO {

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];

        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        //queue keeps pages in the order they entered memory.
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> memory = new HashSet<>();
        //HashSet is used only to check quickly whether a page is already present.

        int faults = 0;

        for (int page : pages) {

            //Check if Page Exists.Yes → Page Hit (do nothing)  No → Page Fault
            if (!memory.contains(page)) 
            {
                faults++;

                //if Memory Full Remove first Page
                if (memory.size() == frames) 
                {
                    int removed = queue.poll(); //queue.poll removes and returns the first element (front) of the queue.
                    memory.remove(removed);
                }

                //Insert New Page
                memory.add(page);
                queue.add(page);
            }

            System.out.println(memory);
        }

        System.out.println("Total Page Faults = " + faults);
    }
}