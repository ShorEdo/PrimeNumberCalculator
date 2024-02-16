/**
 * this class is the data structure that holds in a form of linked list nodes that contain the numbers that needed to be
 * checked if they are prime, after the threads are done the linked list will hold node with prime numbers and -1,
 * The prime number node will be sorted from small to big because there is no change in the linked list, But there will
 * be a lot of nodes in between that contain -1.(The reason not to remove the nodes that are not prime was that it
 * has little impact on the run time, it can be optimized in space to be able to calculate huge numbers by adding a demon
 * thread that saves all the node that been checked after square root of last number to a file to lighten the size.)
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrimeNumberPool {
    private final Node head;
    private Node nextPull;
    private final Lock pullNextLock = new ReentrantLock();

    public PrimeNumberPool(long number) {
        long i = 2;
        Node tail = head = new Node(i++);
        for(;i <= number; i++) {
            tail.setNextNode(new Node(i));
            tail = tail.getNextNode();
        }
        nextPull = head.getNextNode();
    }
    public Node pullNext() {
        pullNextLock.lock();
        try {
            Node node = nextPull;
            if(node != null) {
                nextPull = nextPull.getNextNode();
            }
            return node;
        } finally {
            pullNextLock.unlock();
        }
    }
    public void printPool() {
        Node node = head;
        while(node != null) {
            if(node.getNumber() != -1) {
                System.out.println(node);
            }
            node = node.getNextNode();
        }
    }

    public Node getHead() {
        return head;
    }

}