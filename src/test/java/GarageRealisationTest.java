import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GarageRealisationTest {
    private static GarageImpl garage;
    private static ArrayList<Owner> testOwners;
    private static ArrayList<Car> testCars;

    @BeforeClass
    public static void setUp() throws Exception {
        garage = new GarageImpl();
        Owner owner1 = new Owner(0, "Alex", "Madov", 25);
        Owner owner2 = new Owner(1, "Andrey", "Gorov", 30);
        Owner owner3 = new Owner(2, "Dmitiy", "Krasnov", 35);
        testOwners = new ArrayList<>();
        testOwners.add(owner1);
        testOwners.add(owner2);
        testOwners.add(owner3);
        Car car1 = new Car(0, "BMW", "M5", 180, 600, 0);
        Car car2 = new Car(1, "BMW", "i8", 240, 720, 0);
        Car car3 = new Car(2, "Volvo", "s60", 150, 300, 1);
        Car car4 = new Car(3, "BMW", "X5", 220, 500, 1);
        Car car5 = new Car(4, "Volvo", "x32", 200, 520, 2);
        testCars = new ArrayList<>();
        testCars.add(car1);
        testCars.add(car2);
        testCars.add(car3);
        testCars.add(car4);
        testCars.add(car5);
    }

    @Test
    public void addNewCar() {
        garage.addNewCar(testCars.get(0), testOwners.get(0));
        garage.addNewCar(testCars.get(1), testOwners.get(0));
        garage.addNewCar(testCars.get(2), testOwners.get(1));
        garage.addNewCar(testCars.get(3), testOwners.get(1));
        garage.addNewCar(testCars.get(4), testOwners.get(2));
    }


    @Test
    public void allCarsOfBrand() {
        ArrayList<Car> expected = new ArrayList<>();
        expected.add(testCars.get(0));
        expected.add(testCars.get(1));
        expected.add(testCars.get(3));
        assertTrue(expected.containsAll(garage.allCarsOfBrand("BMW")));
    }

    @Test
    public void allCarsOfOwner() {
        Owner owner1 = new Owner(0, "Alex", "Madov", 25);
        ArrayList<Car> expected = new ArrayList<>();
        expected.add(testCars.get(0));
        expected.add(testCars.get(1));
        assertTrue(expected.containsAll(garage.allCarsOfOwner(owner1)));
    }

    @Test
    public void allCarsUniqueOwners() {
        assertTrue(testOwners.containsAll(garage.allCarsUniqueOwners()));
    }

    @Test
    public void carsWithPowerMoreThan() {
        ArrayList<Car> expected = new ArrayList<>();
        expected.add(testCars.get(0));
        expected.add(testCars.get(1));
        expected.add(testCars.get(4));
        assertTrue(expected.containsAll( garage.carsWithPowerMoreThan(500)));
    }

    @Test
    public void meanCarNumberForEachOwner() {
        assertEquals(5 / 3, garage.meanCarNumberForEachOwner());
    }

    @Test
    public void meanOwnersAgeOfCarBrand() {
        assertEquals(65/2, garage.meanOwnersAgeOfCarBrand("Volvo"));
    }

    @Test
    public void removeCar() {
        garage.removeCar(0);
    }

    @Test
    public void topThreeCarsByMaxVelocity() {
        ArrayList<Car> expected = new ArrayList<>();
        expected.add(testCars.get(1));
        expected.add(testCars.get(3));
        expected.add(testCars.get(4));
        assertTrue(expected.containsAll(garage.topThreeCarsByMaxVelocity()));
    }

}