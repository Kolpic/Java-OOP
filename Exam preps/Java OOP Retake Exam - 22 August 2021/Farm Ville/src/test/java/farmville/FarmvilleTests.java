package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Farm
    private Animal animal;
    private Farm farm;

    @Before
    public void setUp() {
        farm = new Farm("Kolpic Farm",5);
        animal = new Animal("Lion",50);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals("Kolpic Farm",farm.getName());
        Assert.assertEquals(5,farm.getCapacity());
        Assert.assertEquals(0,farm.getCount());
    }

    @Test
    public void testAddShouldAddAnimalToTheFarm() {
        farm.add(animal);
        Assert.assertEquals(1,farm.getCount());
        Assert.assertEquals("Lion",animal.getType());
        Assert.assertEquals(50,animal.getEnergy(),0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowIfAnimalIsAlreadyInTheFarm() {
        farm.add(animal);
        farm.add(animal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowIfThereIsNoCapacityInTheFarm() {
        farm.add(animal);
        Animal animal1 = new Animal("Monkey",25);
        Animal animal2 = new Animal("Cat",2);
        Animal animal3 = new Animal("Dog",5);
        Animal animal4 = new Animal("Elephant",55);
        Animal animal5 = new Animal("Zebra",10);

        farm.add(animal1);
        farm.add(animal2);
        farm.add(animal3);
        farm.add(animal4);
        farm.add(animal5);
    }

    @Test
    public void testRemove() {
        farm.add(animal);
        farm.remove(animal.getType());
        Assert.assertEquals(0,farm.getCount());
    }

    @Test
    public void testRemoveShouldReturnNullIfThereIsNoSuchAnimal() {
        farm.remove(animal.getType());
        Assert.assertEquals(0,farm.getCount());
        farm.add(animal);
        Animal animal1 = new Animal("Monkey",25);
        farm.remove(animal1.getType());
        Assert.assertEquals(1,farm.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityShouldThrowIfItIsNegativeNumber() {
        Farm farm1 = new Farm("JackVile",-5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowIfNameIsNull() {
        Farm farm1 = new Farm(null,4);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowIfNameIsEmpty() {
        Farm farm1 = new Farm("",4);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowIfNameIsOnlyWithWhiteSpaces() {
        Farm farm1 = new Farm("       ",4);
    }
}
