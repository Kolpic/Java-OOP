package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ListIteratorTest {

    private ListIterator listIterator;
    private String[] names = {"Galin", "Tosho", "Gosho"};

    @Before
    public void prepare() throws OperationNotSupportedException {
        listIterator = new ListIterator(names);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWithNull() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testMoveReturnsTrueIfThereIsNextElement() {
        Assert.assertEquals(true,listIterator.move());
    }

    @Test
    public void testMoveReturnsFalseIfThereIsNoNextElement() throws OperationNotSupportedException {
        listIterator = new ListIterator("Galin");
        Assert.assertEquals(false,listIterator.move());
    }

    @Test
    public void testPrintShouldPrintTheResult() {
        Assert.assertEquals(names[0], listIterator.print());
        listIterator.move();
        Assert.assertEquals(names[1], listIterator.print());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintThrowsIfListIsEmpty() throws OperationNotSupportedException {
        ListIterator emptyIterator = new ListIterator();
        emptyIterator.print();
    }
}