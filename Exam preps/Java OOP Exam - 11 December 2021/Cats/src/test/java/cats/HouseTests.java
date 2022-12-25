package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    House house1;
    Cat cat1;
    Cat cat2;
    Cat cat3;

    @Before
    public void setUp() {
        house1 = new House("Kolpic's house",5);
        cat1 = new Cat("Pesho");
        cat2 = new Cat("Gosho");
        cat3 = new Cat("Tosho");
    }

    @Test(expected = NullPointerException.class)
    public void testHouseNameShouldThrowIfNameIsNull() {
        House house = new House(null,5);
    }

    @Test(expected = NullPointerException.class)
    public void testHouseNameShouldThrowIfNameIsEmpty() {
        House house = new House("  ",5);
    }

    @Test
    public void testGetCapacity() {
        Assert.assertEquals(5,house1.getCapacity());

        House house = new House("Test",1);

        Assert.assertEquals(1,house.getCapacity());
    }

    @Test
    public void getName() {
        Assert.assertEquals("Kolpic's house",house1.getName());
    }

    @Test(expected = IllegalArgumentException.class)
        public void testSetCapacityShouldThrowIfCapacityIsUnderZero() {

        House house = new House("Test", -1);
    }

    @Test
    public void testAddCat() {
        house1.addCat(cat1);
        Assert.assertEquals(1,house1.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatShouldThrowIfThereIsNotEnoughCapacity() {
        House house = new House("Test",1);

        house.addCat(cat1);
        house.addCat(cat2);
    }

    @Test
    public void testRemoveCat() {
        house1.addCat(cat1);

        house1.removeCat(cat1.getName());

        Assert.assertEquals(0,house1.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatShouldThrowIfThereIsNoSuchCat() {
        house1.addCat(cat1);

        house1.removeCat(cat2.getName());

    }

    @Test
    public void testCatForSale() {
        house1.addCat(cat1);

        house1.catForSale(cat1.getName());

        Assert.assertFalse(cat1.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleShouldThrowIfThereIsNoSuchCat() {
        house1.addCat(cat1);

        house1.catForSale(cat2.getName());

    }

    @Test
    public void testStatistics() {
        house1.addCat(cat1);
        house1.addCat(cat2);

        Assert.assertEquals
                (String.format("The cat Pesho, Gosho is in the house Kolpic's house!"),house1.statistics());
    }
}
