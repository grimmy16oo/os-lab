import java.util.*;

public class FirstFit {

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

            boolean allocated=false;

            for(int j=0;j<m;j++){

                if(block[j]>=process[i]){

                    System.out.println("Process "+process[i]+" -> Block "+(j+1));

                    block[j]-=process[i];

                    allocated=true;

                    break;
                }
            }

            if(!allocated)
                System.out.println("Not Allocated");
        }
    }
}