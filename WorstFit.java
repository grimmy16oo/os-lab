import java.util.*;

public class WorstFit {

    public static void main(String args[]) {

        Scanner sc=new Scanner(System.in);

        System.out.print("Blocks: ");
        int m=sc.nextInt();

        int block[]=new int[m];

        for(int i=0;i<m;i++)
            block[i]=sc.nextInt();

        System.out.print("Processes: ");
        int n=sc.nextInt();

        int process[]=new int[n];

        for(int i=0;i<n;i++)
            process[i]=sc.nextInt();

        for(int i=0;i<n;i++){

            int worst=-1;

            for(int j=0;j<m;j++){

                if(block[j]>=process[i]){

                    if(worst==-1 || block[j]>block[worst])
                        worst=j;
                }
            }

            if(worst!=-1){

                System.out.println("Process "+process[i]+" -> Block "+(worst+1));

                block[worst]-=process[i];
            }
            else
                System.out.println("Not Allocated");
        }
    }
}