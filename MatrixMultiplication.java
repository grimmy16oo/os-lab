import java.util.Scanner;

class MatrixThread extends Thread {

    int row;
    int[][] A, B, C;
    int colA, colB;

    MatrixThread(int row, int[][] A, int[][] B, int[][] C, int colA, int colB) {
        this.row = row;
        this.A = A;
        this.B = B;
        this.C = C;
        this.colA = colA;
        this.colB = colB;
    }

    public void run() {

        for (int j = 0; j < colB; j++) {

            C[row][j] = 0;

            for (int k = 0; k < colA; k++) {
                C[row][j] += A[row][k] * B[k][j];
            }
        }
    }
}

public class MatrixMultiplication {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rows and columns of Matrix A: ");
        int rowA = sc.nextInt();
        int colA = sc.nextInt();

        System.out.print("Enter rows and columns of Matrix B: ");
        int rowB = sc.nextInt();
        int colB = sc.nextInt();

        if (colA != rowB) {
            System.out.println("Matrix multiplication is not possible.");
            return;
        }

        int[][] A = new int[rowA][colA];
        int[][] B = new int[rowB][colB];
        int[][] C = new int[rowA][colB];

        System.out.println("\nEnter Matrix A:");

        for (int i = 0; i < rowA; i++)
            for (int j = 0; j < colA; j++)
                A[i][j] = sc.nextInt();

        System.out.println("\nEnter Matrix B:");

        for (int i = 0; i < rowB; i++)
            for (int j = 0; j < colB; j++)
                B[i][j] = sc.nextInt();

        MatrixThread[] threads = new MatrixThread[rowA];

        // Create one thread for each row
        for (int i = 0; i < rowA; i++) {
            threads[i] = new MatrixThread(i, A, B, C, colA, colB);
            threads[i].start();
        }

        // Wait for all threads
        for (int i = 0; i < rowA; i++) {
            threads[i].join();
        }

        System.out.println("\nResult Matrix:");

        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colB; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}