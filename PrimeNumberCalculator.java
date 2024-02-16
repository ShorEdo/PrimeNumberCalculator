/**
 * The main class for calculating all the prim numbers from 2 to the given number with multiple threads.
 * Made by Edo Shor.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrimeNumberCalculator {
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 1;
        long numbers = 10000000;
        Scanner scan = new Scanner(System.in);
        boolean isGoodInput = false;

        System.out.println("This program will calculate all the prime number from 2 to the given number.");
        System.out.println("Please enter the number: ");
        while(!isGoodInput) {
            try {
                numbers = scan.nextLong();
                if(numbers >= 3) {
                    isGoodInput = true;
                } else {
                    System.out.println("Number needs to be bigger then 2, please try again:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input needs to be numbers only please try again: ");
            }
        }
        isGoodInput = false;
        System.out.println("Please enter the number of threads to use: ");
        while(!isGoodInput) {
            try {
                numThreads = scan.nextInt();
                if(numThreads >= 1) {
                    isGoodInput = true;
                } else {
                    System.out.println("Number of threads need to be bigger then 1, please try again:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input needs to be numbers only please try again: ");
            }
        }
        PrimeNumberPool pool = new PrimeNumberPool(numbers);
        PrimeNumberThread[] threads = new PrimeNumberThread[numThreads];
        for(int i = 0; i < numThreads; i++) {
            threads[i] = new PrimeNumberThread(pool);
        }
        for(int i = 0; i < numThreads; i++) {
            threads[i].start();
        }
        for(int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        pool.printPool();
    }
}
