package magicGame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MagicianTests {

    private Magician magician1;
    private Magic magic1;
    private Magic magic2;

    private Magic magic3;

    List<Magic> expectedMagics;

    @Before
    public void setUp() {
        magician1 = new Magician("Kolpic",10);

        magic1 = new Magic("Magic_1",10);
        magic2 = new Magic("Magic_2",5);
        magic3 = new Magic("Magic_3",12);

        expectedMagics = new ArrayList<>();
    }

    @Test(expected = NullPointerException.class)
    public void testSetUsernameShouldThrowIfNull() {
        Magician magician = new Magician(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testSetUsernameShouldThrowIfNameIsEmpty() {
        Magician magician = new Magician("  ", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHealthShouldThrowIfHealthIsBelowZero() {
        Magician magician = new Magician("Ivan", -1);
    }

    @Test
    public void testTakeDamage() {
        Assert.assertEquals("Kolpic",magician1.getUsername());
        Assert.assertEquals(10,magician1.getHealth());

        magician1.takeDamage(9);

        Assert.assertEquals(1,magician1.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeDamageShouldThrowIfMagicianIsAlreadyDead() {
        magician1.takeDamage(10);

        magician1.takeDamage(1);
    }

    @Test
    public void testAddMagic() {
        magician1.addMagic(magic1);

        expectedMagics.add(magic1);

        Assert.assertEquals(expectedMagics,magician1.getMagics());

        magician1.addMagic(magic2);

        expectedMagics.add(magic2);

        Assert.assertEquals(expectedMagics,magician1.getMagics());
    }

    @Test(expected = NullPointerException.class)
    public void testAddMagicShouldThrowIfMagicIsNull() {
        magician1.addMagic(null);
    }

    @Test
    public void testRemoveMagic() {
        magician1.addMagic(magic1);
        magician1.addMagic(magic2);
        magician1.addMagic(magic3);

        magician1.removeMagic(magic1);
        magician1.removeMagic(magic2);

        expectedMagics.add(magic3);

        Assert.assertEquals(expectedMagics,magician1.getMagics());
    }

    @Test
    public void getMagic() {
        magician1.addMagic(magic1);
        magician1.addMagic(magic2);
        magician1.addMagic(magic3);

        Assert.assertEquals(magic1,magician1.getMagic("Magic_1"));
        Assert.assertEquals(magic2,magician1.getMagic("Magic_2"));
        Assert.assertEquals(magic3,magician1.getMagic("Magic_3"));
    }

}
