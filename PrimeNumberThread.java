/**
 * The thread class that calculates if a number is a prime number has to work with class PrimeNumberPool.
 * The thread will pull a number that needs to be checked if it's a prime, The way it checks is by trying  to see if there is
 * a lower "mostly" prime number that divides the pulled number, it won't check further than square root of the number because math.
 * once it has the result if it's not a prime it will change the number in the pool to -1 without synchronization (The
 * reason is it's not a prime so further threads that check with it won't change the outcome.) and for any result it'll
 * try pulling the next number to check if its prime.
 */
public class PrimeNumberThread extends Thread {
    private final PrimeNumberPool pool;
    public PrimeNumberThread(PrimeNumberPool pool) {
        this.pool = pool;
    }
    public void run() {
        Node numberCheck = pool.pullNext();
        while(numberCheck != null){ // runs till no more numbers left in pool
            Node primeToMod = pool.getHead();
            long ceiling = (long) Math.sqrt((double) numberCheck.getNumber());
            while(primeToMod != null && primeToMod.getNumber() <= ceiling) { // runs till divides or out of scope.
                if(primeToMod.getNumber() != -1 && numberCheck.isDividable(primeToMod)) {
                    numberCheck.setNumber(-1);
                }
                primeToMod = primeToMod.getNextNode();
            }
            numberCheck = pool.pullNext();
        }
    }
}
