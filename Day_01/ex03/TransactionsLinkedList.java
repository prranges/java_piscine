import java.util.UUID;

class TransactionNotFoundException extends RuntimeException {}

public class TransactionsLinkedList implements TransactionsList{

    private class Node {
        Transaction transaction;
        Node next;

        public Node(Transaction transaction) {
            this.transaction = transaction;
            this.next = null;
        }
    }

    private Node start = null;
    private Node end = null;
    private Integer size = 0;

    @Override
    public void add(Transaction transaction) {
        Node newNode = new Node(transaction);
        ++size;
        if (start == null) {
            start = newNode;
            end = newNode;
            return;
        }
        end.next = newNode;
        end = newNode;
    }

    @Override
    public void remove(UUID identifier) {
        Node prevNode = null;
        for (Node tmp = start; tmp != null ; tmp = tmp.next) {
            if (tmp.transaction.getIdentifier() == identifier) {
                if (prevNode == null) {
                    start = tmp.next;
                    --size;
                    return;
                }
                prevNode.next = tmp.next;
                --size;
                return;
            }
            prevNode = tmp;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Node tmp = start;
        for (int i = 0; i < size; ++i) {
            array[i] = tmp.transaction;
            tmp = tmp.next;
        }
        return array;
    }
}
