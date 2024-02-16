/**
 * The node class for the linked list in the PrimeNumberPool class, simple one way node that holds a number with
 * simple methods.
 */
public class Node {
    private long number;
    private Node nextNode = null;
    public Node(long number) {
        this.number = number;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
    public long getNumber() {
        return number;
    }
    public Node getNextNode() {
        return nextNode;
    }
    public String toString() {
        return String.format("%d",getNumber());
    }
    public boolean isDividable(Node node) {
        return this.getNumber() % node.getNumber() == 0;
    }
}
