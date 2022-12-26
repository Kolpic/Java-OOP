import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock{

    Map<Integer,Transaction> transactionMap;

    public ChainblockImpl() {
        this.transactionMap = new HashMap<>();
    }

    public int getCount() {
        return this.transactionMap.size();
    }

    public void add(Transaction transaction) {
//        if (!transactionMap.containsValue(transaction)) {
//            transactionMap.put(getCount() + 1,transaction);
//        }

        if (!contains(transaction)) {
            this.transactionMap.put(transaction.getId(), transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return this.transactionMap.containsValue(transaction);
    }

    public boolean contains(int id) {
        return this.transactionMap.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!contains(id)) {
            throw new IllegalArgumentException("Invalid id");
        } else {
            Transaction transactionForChange = transactionMap.get(id);
            transactionForChange.setStatus(newStatus);
        }
    }

    public void removeTransactionById(int id) {
        if (!contains(id)) {
            throw new IllegalArgumentException("Invalid id");
        } else {
            transactionMap.remove(id);
        }
    }

    public Transaction getById(int id) {
        if (!contains(id)) {
            throw new IllegalArgumentException("Invalid id");
        }
        return transactionMap.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransaction = new ArrayList<>();

        for (Transaction transaction : transactionMap.values()) {
            if (transaction.getStatus() == status) {
                filteredTransaction.add(transaction);
            }
        }

        if (filteredTransaction.size() == 0) {
            throw new IllegalArgumentException("There is no transactions with the give status!");
        } else {
            filteredTransaction.sort(Comparator.comparing(Transaction::getAmount).reversed());
            return filteredTransaction;
        }
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
       Iterable<Transaction> filteredTransactions = getByTransactionStatus(status);
       List<String> senders = new ArrayList<>();
       filteredTransactions.forEach(tr -> senders.add(tr.sentFrom()));
       return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        Iterable<Transaction> filteredTransactions = getByTransactionStatus(status);
        List<String> receivers = new ArrayList<>();
        filteredTransactions.forEach(tr -> receivers.add(tr.sentTo()));
        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return null;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
       return transactionMap.values()
                .stream()
                .filter(e -> e.getAmount() > lo && e.getAmount() < hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
