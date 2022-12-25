package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetStoreTests {

    private PetStore petStore;
    private List<Animal> animals;

    @Before
    public void setUp() {
        petStore = new PetStore();
        animals = new ArrayList<>();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetAnimalsShouldReturnUnmodifiableList() {
        animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals(0,petStore.getCount());
    }

    @Test
    public void testAddAnimalShouldAddCorrect() {
        Animal animal1 = new Animal("Mammal",50,100);
        petStore.addAnimal(animal1);
       Assert.assertEquals(1,petStore.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalShouldThrowIfAnimalIsNull() {
        Animal animal1 = null;
        petStore.addAnimal(animal1);
    }

    @Test
    public void testFindAllAnimalsWithMaxKilograms() {
        Animal animal1 = new Animal("Mammal",10,100);
        petStore.addAnimal(animal1);
        Animal animal2 = new Animal("Reptile",24,10);
        petStore.addAnimal(animal2);

        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(animal2);

        Assert.assertEquals(expectedAnimals,petStore.findAllAnimalsWithMaxKilograms(20));
    }

    @Test
    public void testGetAnimalsReturnsCollectionOfAnimals() {
        Animal animal1 = new Animal("Mammal",50,100);
        petStore.addAnimal(animal1);
        Animal animal2 = new Animal("Reptile",20,10);
        petStore.addAnimal(animal2);

        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(animal1);
        expectedAnimals.add(animal2);

        Assert.assertEquals(expectedAnimals,petStore.getAnimals());
    }

    @Test
    public void testGetTheMostExpensiveAnimal() {
        Animal animal1 = new Animal("Mammal",50,100);
        petStore.addAnimal(animal1);
        Animal animal2 = new Animal("Reptile",20,10);
        petStore.addAnimal(animal2);

        Assert.assertEquals(animal1,petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void testGetTheMostExpensiveAnimalShouldReturnNullWhenIsEmpty() {
        Animal animal = petStore.getTheMostExpensiveAnimal();
        Assert.assertNull(animal);
    }

    @Test
    public void testFindAllAnimalBySpecie() {
        Animal animal1 = new Animal("Mammal",50,100);
        petStore.addAnimal(animal1);
        Animal animal2 = new Animal("Reptile",20,10);
        petStore.addAnimal(animal2);

        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(animal1);

        Assert.assertEquals(expectedAnimals,petStore.findAllAnimalBySpecie("Mammal"));

        Animal animal3 = new Animal("Mammal",28,200);
        petStore.addAnimal(animal3);
        expectedAnimals.add(animal3);

        Assert.assertEquals(expectedAnimals,petStore.findAllAnimalBySpecie("Mammal"));

    }


}

