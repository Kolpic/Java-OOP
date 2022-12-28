package archeologicalExcavations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExcavationTests {

    private Excavation excavation;
    private Excavation excavationSetUp;
    private Archaeologist archaeologist;

    @Before
    public void setUp() {
        excavationSetUp = new Excavation("Test_name",2);
    }

    @Test
    public void testConstructorSetNameShouldReturnCorrectName() {
        excavation = new Excavation("Test",2);
        assertEquals("Test",excavation.getName());
        assertEquals(2,excavation.getCapacity());
        assertEquals(0,excavation.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorSetNameShouldThrowIfNameIsNull() {
        excavation = new Excavation(null,2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorSetNameShouldThrowIfNameIsEmpty() {
        excavation = new Excavation("",2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorSetNameShouldThrowIfNameIsWithWhiteSpaces() {
        excavation = new Excavation("    ",2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorSetCapacityShouldThrowIfCapacityIsBelowZero() {
        excavation = new Excavation("Test", -1);
    }

    @Test
    public void testAddArchaeologist() {
        Archaeologist archaeologist1 = new Archaeologist("Galin",50);
        excavationSetUp.addArchaeologist(archaeologist1);
        assertEquals(1,excavationSetUp.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistShouldThrowIfThereIsNotEnoughCapacity() {
        Archaeologist archaeologist1 = new Archaeologist("Galin",50);
        Archaeologist archaeologist2 = new Archaeologist("Ivan",50);
        Archaeologist archaeologist3 = new Archaeologist("Pesho",50);
        excavationSetUp.addArchaeologist(archaeologist1);
        excavationSetUp.addArchaeologist(archaeologist2);
        excavationSetUp.addArchaeologist(archaeologist3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistShouldThrowIfThereIsAlreadyArcheologistWithThatName() {
        Archaeologist archaeologist1 = new Archaeologist("Galin",50);
        Archaeologist archaeologist2 = new Archaeologist("Galin",20);
        excavationSetUp.addArchaeologist(archaeologist1);
        excavationSetUp.addArchaeologist(archaeologist2);
    }

    @Test
    public void testRemoveArchaeologist() {
        Archaeologist archaeologist1 = new Archaeologist("Galin",50);
        excavationSetUp.addArchaeologist(archaeologist1);

        assertEquals(1,excavationSetUp.getCount());

        excavationSetUp.removeArchaeologist("Galin");

        assertEquals(0,excavationSetUp.getCount());
    }

    @Test
    public void testRemoveArchaeologistIfThereIsNoSuchArchaeologist() {
//        excavationSetUp.removeArchaeologist("Ivan");

        assertFalse(excavationSetUp.removeArchaeologist("Ivan"));
    }
}
