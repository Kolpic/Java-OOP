package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ToyStoryTest {

    ToyStore toyStore;
    Toy toy1;
    Toy toy2;

    Map<String,Toy> expectedMap;

    @Before
    public void setUp() {
        toyStore = new ToyStore();
        toy1 = new Toy("Atra","1");
        toy2 = new Toy("Ataro","2");
        expectedMap = new LinkedHashMap<>();
        this.expectedMap.put("A", null);
        this.expectedMap.put("B", null);
        this.expectedMap.put("C", null);
        this.expectedMap.put("D", null);
        this.expectedMap.put("E", null);
        this.expectedMap.put("F", null);
        this.expectedMap.put("G", null);
    }

    @Test
    public void testAddToyAddsSuccessfully() throws OperationNotSupportedException {
        toyStore.addToy("A",toy1);
        expectedMap.put("A",toy1);
        Assert.assertEquals(expectedMap,toyStore.getToyShelf());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowIfShelfDoNotExists() throws OperationNotSupportedException {
        toyStore.addToy("m",toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowIfShelfAlreadyHasToy() throws OperationNotSupportedException {
        toyStore.addToy("A",toy1);
        toyStore.addToy("A",toy2);
    }

    @Test
    public void testAddToyShouldThrowIfShelfAlreadyHasToys() throws OperationNotSupportedException {
        toyStore.addToy("A",toy1);
        Assert.assertTrue(toyStore.getToyShelf().containsValue(toy1));
        Assert.assertFalse(toyStore.getToyShelf().containsValue(toy2));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddToyThrowsIfToyIsAlreadyInShelf() throws OperationNotSupportedException {
        toyStore.addToy("A",toy1);
        toyStore.addToy("B",toy1);
    }

    @Test
    public void testRemoveToy() throws OperationNotSupportedException {
        toyStore.addToy("A",toy1);
        toyStore.addToy("B",toy2);

        toyStore.removeToy("B",toy2);

        expectedMap.put("A",toy1);

        Assert.assertEquals(expectedMap,toyStore.getToyShelf());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyThrowsIfShelfDoNotExists() throws OperationNotSupportedException {
        toyStore.addToy("A",toy1);
        toyStore.addToy("B",toy2);

        toyStore.removeToy("O",toy2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyThrowsIfToyInThatShelfDoNotExists() throws OperationNotSupportedException {
        toyStore.addToy("A",toy1);
        toyStore.addToy("B",toy2);

        toyStore.removeToy("A",toy2);
    }
}