package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    Database database;

    @Before
    public void prepare() throws OperationNotSupportedException {
        Person galin = new Person(377919, "Galin");
        Person pesho = new Person(377921, "Pesho");
        Person tosho = new Person(377922, "Tosho");
        this.database = new Database(galin, pesho, tosho);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsIfThereIsNoSuchName() throws OperationNotSupportedException {
        String neededName = "Gosho";
        database.findByUsername(neededName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsIfParameterIsNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

}