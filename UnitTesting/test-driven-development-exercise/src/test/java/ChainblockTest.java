import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class ChainblockTest {

    private Chainblock database;

    @Before
    public void setUp() {
        this.database = new ChainblockImpl();
    }

    @Test
    public void testAddCorrectTransaction(){
        Transaction transaction = addOneTransaction();

        Assert.assertEquals(1,database.getCount());
        Assert.assertTrue(database.contains(transaction));
    }

    @Test
    public void testAddExistingTransaction() {
        Transaction transaction = addOneTransaction();

        Assert.assertEquals(1,database.getCount());
        Assert.assertTrue(this.database.contains(transaction.getId()));

        database.add(transaction);

        Assert.assertEquals(1,database.getCount());
    }

    private Transaction addOneTransaction() {
        Assert.assertEquals(0,database.getCount());

        Transaction transaction = new
                TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Galin","Petq",10);

        database.add(transaction);
        return transaction;
    }

    @Test
    public void testChangeTransactionStatusChangesTheStatusSucssesfully() {
        addOneTransaction();

        database.changeTransactionStatus(1,TransactionStatus.ABORTED);

        Assert.assertEquals(TransactionStatus.ABORTED,database.getById(1).getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusThrowIfNoSuchTransactionExists() {
        addOneTransaction();

        database.changeTransactionStatus(database.getCount() + 1,TransactionStatus.ABORTED);
    }

    @Test
    public void testRemoveTransactionByIdShouldRemoveItSucssesfully() {
        addOneTransaction();

        Transaction transaction2 = new
                TransactionImpl(2,TransactionStatus.ABORTED,"Petq","Galin",10);

        database.add(transaction2);

        Assert.assertEquals(2, database.getCount());

        database.removeTransactionById(1);

        Assert.assertEquals(1,database.getCount());
        Assert.assertFalse(database.contains(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdShouldThrowIfThereIsNoSuchId() {
        Transaction transaction = new
                TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Galin","Petq",10);
        Transaction transaction2 = new
                TransactionImpl(2,TransactionStatus.ABORTED,"Petq","Galin",10);

        database.add(transaction);
        database.add(transaction2);

        database.removeTransactionById(database.getCount() + 1);

    }

    @Test
    public void testGetByIdReturnsTheTransactionWithTheGivenId() {
        Transaction transaction = addOneTransaction();

        Transaction returnedTransaction = database.getById(1);

        Assert.assertEquals(transaction,returnedTransaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdThrowsIfThereIsNoSuchTransaction() {
        Transaction transaction = new
                TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Galin","Petq",10);

        Transaction returnedTransaction = database.getById(1);

    }

    @Test
    public void testGetByTransactionStatusReturnsTransactionWithGivenStatus() {
        Transaction transaction = new
                TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Galin","Petq",10);
        Transaction transaction1 = new
                TransactionImpl(2,TransactionStatus.ABORTED,"Ganka","Petq",10);
        Transaction transaction2 = new
                TransactionImpl(3,TransactionStatus.SUCCESSFUL,"Ivan","Petq",100);

        database.add(transaction);
        database.add(transaction1);
        database.add(transaction2);

        Iterable<Transaction> result = database.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<Transaction> returnedTransactions = new ArrayList<>();
        result.forEach(returnedTransactions::add);

        Assert.assertEquals(2, returnedTransactions.size());
        returnedTransactions.forEach(tr -> Assert.assertEquals(TransactionStatus.SUCCESSFUL, tr.getStatus()));
        Assert.assertEquals(returnedTransactions.get(0),transaction2);
        Assert.assertEquals(returnedTransactions.get(1), transaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusThrowIfThereIsTransactionWithDifferentGivenStatus() {
        addThreeTransactions();

        Iterable<Transaction> result = database.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    private void addThreeTransactions() {
        Transaction transaction1 = new
                TransactionImpl(1,TransactionStatus.FAILED,"Galin","Petq",10);
        Transaction transaction2 = new
                TransactionImpl(2,TransactionStatus.ABORTED,"Ganka","Petq",10);
        Transaction transaction3 = new
                TransactionImpl(3,TransactionStatus.SUCCESSFUL,"Ivan","Petq",100);

        database.add(transaction1);
        database.add(transaction2);
        database.add(transaction3);
    }

    @Test
    public void testGetAllSendersWithTransactionStatusShouldReturnAllSendersWithTheGivenTransactionStatus() {
        addThreeTransactions();

        Transaction transaction4 = new
                TransactionImpl(4,TransactionStatus.FAILED,"Petq","Petq",100);
        database.add(transaction4);

        Iterable<String> result = database.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
        List<String> resultSenders = new ArrayList<>();
        result.forEach(resultSenders::add);

        Assert.assertEquals(2,resultSenders.size());
        Assert.assertEquals("Petq", resultSenders.get(0));
        Assert.assertEquals("Galin", resultSenders.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusShouldThrowIftHeGivenStatusIsInvalid() {
        addThreeTransactions();

        database.getAllSendersWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void testGetAllReceiversWithTransactionStatusShouldReturnAllReceiversWithTheGivenTransactionStatus() {
        addThreeTransactions();

        Transaction transaction4 = new
                TransactionImpl(4,TransactionStatus.ABORTED,"Petq","Ganka",5);
        database.add(transaction4);

        Iterable<String> result = database.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
        List<String> resultReceivers = new ArrayList<>();
        result.forEach(resultReceivers::add);

        Assert.assertEquals(2,resultReceivers.size());
        Assert.assertEquals("Petq",resultReceivers.get(0));
        Assert.assertEquals("Ganka",resultReceivers.get(1));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusShouldThrowIfThereIsNoReceiverWithTheGivenStatus() {
        addThreeTransactions();

        Iterable<String> result = database.getAllReceiversWithTransactionStatus(TransactionStatus.UNAUTHORIZED);

    }



    @Test
    public void testGetAllInAmountRangeShouldEmptyCollectionIfThereIsNoSuchAmountInThatRange() {
        Transaction transaction4 = new
                TransactionImpl(4,TransactionStatus.ABORTED,"Petq","Ganka",8);
        database.add(transaction4);

        List<Double> listOfAmount = new ArrayList<>();
        for (Transaction transaction : database) {
            if (transaction.getAmount() > 1 && transaction.getAmount() < 5) {
                listOfAmount.add(transaction.getAmount());
            }
        }
        if (listOfAmount.size() == 0) {
            return;
        }
    }

//    private Transaction addFourTransactions() {
//        Transaction transaction1 = new
//                TransactionImpl(1,TransactionStatus.FAILED,"Galin","Petq",10);
//        Transaction transaction2 = new
//                TransactionImpl(2,TransactionStatus.ABORTED,"Ganka","Petq",10);
//        Transaction transaction3 = new
//                TransactionImpl(3,TransactionStatus.SUCCESSFUL,"Ivan","Petq",100);
//        Transaction transaction4 = new
//                TransactionImpl(4,TransactionStatus.ABORTED,"Petq","Ganka",8);
//
//        database.add(transaction1);
//        database.add(transaction2);
//        database.add(transaction3);
//        database.add(transaction4);
//        return
//    }
}