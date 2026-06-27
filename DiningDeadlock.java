class Philosopher extends Thread {

    int id;
    Object leftFork;
    Object rightFork;

    Philosopher(int id, Object leftFork, Object rightFork) 
    {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() 
    {
        try 
        {
            while (true) 
            {
                System.out.println("Philosopher " + id + " is Thinking");

                synchronized (leftFork) 
                {
                    System.out.println("Philosopher " + id + " picked LEFT fork");

                    Thread.sleep(100);

                    synchronized (rightFork) 
                    {
                        System.out.println("Philosopher " + id + " is Eating");

                        Thread.sleep(1000);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class DiningDeadlock {

    public static void main(String[] args) 
    {
        Object[] forks = new Object[5];

        for (int i = 0; i < 5; i++)
            forks[i] = new Object();

        for (int i = 0; i < 5; i++) 
        {
            Object left = forks[i];
            Object right = forks[(i + 1) % 5];

            new Philosopher(i, left, right).start();
        }
    }
}


// Every philosopher first locks their left fork and then waits for their right fork. 
// If all philosophers do this at the same time, each holds one fork while waiting for another, 
// creating a circular wait where no one can proceed.