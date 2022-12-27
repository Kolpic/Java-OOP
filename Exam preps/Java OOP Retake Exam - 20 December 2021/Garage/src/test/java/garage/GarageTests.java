package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GarageTests {

    private Garage garage;
    private Car car1;
    private Car car2;

    @Before
    public void setUp() {
        garage = new Garage();
        car1 = new Car("BMW",300,80000);
        car2 = new Car("Opel",220,4000);
    }

    @Test
    public void testAddCarShouldAddCorrectCar() {
        assertEquals(0,garage.getCount());
        garage.addCar(car1);
        assertEquals(1,garage.getCount());
        garage.addCar(car2);
        assertEquals(2,garage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarShouldThrowIfCarIsNull() {
        Car car = null;
        garage.addCar(car);
    }

    @Test
    public void testFindAllCarsByBrand() {
        garage.addCar(car1);
        garage.addCar(car2);
        List<Car> expectedCarsAreBMW = new ArrayList<>();
        expectedCarsAreBMW.add(car1);
        assertEquals(expectedCarsAreBMW,garage.findAllCarsByBrand("BMW"));

        Car car = new Car("BMW",290,77777);
        garage.addCar(car);
        expectedCarsAreBMW.add(car);
        assertEquals(expectedCarsAreBMW,garage.findAllCarsByBrand("BMW"));
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        garage.addCar(car1);
        garage.addCar(car2);

        assertEquals(car1,garage.getTheMostExpensiveCar());
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        garage.addCar(car1);
        garage.addCar(car2);

        List<Car> expectedCars = new ArrayList<>();
        expectedCars.add(car1);

        assertEquals(expectedCars,garage.findAllCarsWithMaxSpeedAbove(220));

        Car car = new Car("Audi",270,50000);
        garage.addCar(car);
        expectedCars.add(car);

        assertEquals(expectedCars,garage.findAllCarsWithMaxSpeedAbove(220));
    }

    @Test
    public void testGetCarsShouldReturnUnmodifiableList() {
        garage.addCar(car1);
        garage.addCar(car2);

        List<Car> expectedCars = new ArrayList<>();
        expectedCars.add(car1);
        expectedCars.add(car2);

        assertEquals(expectedCars,garage.getCars());
    }
}