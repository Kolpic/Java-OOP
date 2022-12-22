package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database database;
    private Integer[] numbers = {5, 7, 3, 6};

    @Before
    public void prepare() throws OperationNotSupportedException {
        this.database = new Database(numbers);
    }

    @Test
    public void testConstructorCreatesValidDB() throws OperationNotSupportedException {

        Integer[] dbElements = database.getElements();

//        for (int i = 0; i < dbElements.length; i++) {
//            Assert.assertEquals(dbElements[i],numbers[i]);
//        }

        Assert.assertArrayEquals(numbers,dbElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWithMoreThan16Elements() throws OperationNotSupportedException {
        Integer[] bigArray = new Integer[17];

        new Database(bigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowWithLessThan1Elements() throws OperationNotSupportedException {
        Integer[] emptyArray = new Integer[0];

        new Database(emptyArray);
    }

    @Test
    public void testAddShouldAddElementAtTheNextFreeCell() throws OperationNotSupportedException {
        int numberToAdd = 5;

        database.add(5);

//        Assert.assertTrue(database.getElements()[numbers.length] == numberToAdd);
        Assert.assertEquals(numberToAdd,(int)database.getElements()[numbers.length]);
        Assert.assertEquals(numbers.length + 1,database.getElements().length);

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowWhenGivenElementIsNull() throws OperationNotSupportedException {

        database.add(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveElementFromEmptyDBShouldThrow() throws OperationNotSupportedException {
        for (int i = 0; i < numbers.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        Integer[] elementsBeforeRemove = database.getElements();
        database.remove();
        Integer[] elementsAfterRemove = database.getElements();

        Assert.assertEquals(elementsBeforeRemove.length - 1, elementsAfterRemove.length);

        int previousSecondToLastElement = elementsBeforeRemove[elementsBeforeRemove.length - 2];
        int currentLastElement = elementsAfterRemove[elementsAfterRemove.length - 1];

        Assert.assertEquals(previousSecondToLastElement, currentLastElement);
    }
}