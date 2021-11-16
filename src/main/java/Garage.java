import java.util.Collection;

public interface Garage {
    /**
     * Complexity should be less than O(n)
     */
    public Collection<Car> topThreeCarsByMaxVelocity();

    /**
     * Complexity should be O(1)
     */
    public Collection<Car> allCarsOfBrand(String brand);

    /**
     * Complexity should be less than O(n)
     */
    public Collection<Car> carsWithPowerMoreThan(int power);

    /**
     * Complexity should be O(1)
     */
    public Collection<Car> allCarsOfOwner(Owner owner);


    /**
     * @return mean value of owner age that has cars with given brand
     */
    public int meanOwnersAgeOfCarBrand(String brand);

    /**
     * @return mean value of cars for all owners
     */
    public int meanCarNumberForEachOwner();

    /**
     * Complexity should be less than O(n)
     * @return removed car
     */
    public Car removeCar(int carId);

    /**
     * Complexity should be less than O(n)
     */
    public void addNewCar(Car car, Owner owner);
}