package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {

    private FootballTeam footballTeam;

    @Before
    public void setUp() {
        footballTeam = new FootballTeam("Levski", 4);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals("Levski",footballTeam.getName());
        Assert.assertEquals(4,footballTeam.getVacantPositions());
        Assert.assertEquals(0,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetVacantPositionsShouldThrowIfVacantPositionsAreLessThan0() {
        FootballTeam footballTeam1 = new FootballTeam("CSKA",-1);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowIfNameIsNull() {
        FootballTeam footballTeam1 = new FootballTeam(null,2);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowIfNameIsWithWhiteSpaces() {
        FootballTeam footballTeam1 = new FootballTeam("    ",1);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowIfNameIsEmpty() {
        FootballTeam footballTeam1 = new FootballTeam("",1);
    }

    @Test
    public void testAdd() {
        footballTeam.addFootballer(new Footballer("Ivan"));
        Assert.assertEquals(1,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowIfThereIsNotEnoughVacantPositions() {
        footballTeam.addFootballer(new Footballer("Ivan"));
        Assert.assertEquals(1,footballTeam.getCount());

        footballTeam.addFootballer(new Footballer("Galin"));
        Assert.assertEquals(2,footballTeam.getCount());

        footballTeam.addFootballer(new Footballer("Georgi"));
        Assert.assertEquals(3,footballTeam.getCount());

        footballTeam.addFootballer(new Footballer("Misho"));


        footballTeam.addFootballer(new Footballer("Neno"));

    }

    @Test
    public void testRemove() {
        footballTeam.addFootballer(new Footballer("Galin"));
        footballTeam.removeFootballer("Galin");
        Assert.assertEquals(0,footballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowIfPlayerIsNotInTheTeam() {
        footballTeam.addFootballer(new Footballer("Galin"));
        footballTeam.addFootballer(new Footballer("Ivan"));
        footballTeam.removeFootballer("Gosho");
    }

    @Test
    public void testFootballerForSale() {
        Footballer footballer = new Footballer("Galin");
        Footballer footballer2 = new Footballer("Ivan");

        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer2);

        footballTeam.footballerForSale("Galin");
        Assert.assertFalse(footballer.isActive());
        Assert.assertTrue(footballer2.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleShouldThrowIfThereIsNoSuchPlayerInTheTeam() {
        footballTeam.footballerForSale("Galin");
    }

    @Test
    public void testGetStatistics() {
        Footballer footballer = new Footballer("Galin");
        Footballer footballer2 = new Footballer("Ivan");

        footballTeam.addFootballer(footballer);
        footballTeam.addFootballer(footballer2);

        Assert.assertEquals
                ("The footballer Galin, Ivan is in the team Levski."
                        ,footballTeam.getStatistics());

    }

}
