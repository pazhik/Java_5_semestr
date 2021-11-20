package garage;

import java.util.*;

public class GarageImpl implements Garage {
    public static final ArrayList<Car> allCars = new ArrayList<>();
    private PriorityQueue<Car> velocityCars;
    public HashMap<Owner, ArrayList<Car>> ownersCars;
    private static final  HashMap<String, ArrayList<Car>> brandsCars = new HashMap<>();;

    public GarageImpl() {
            velocityCars = new PriorityQueue<>(Car.compareByMaxVelocity);
            ownersCars = new HashMap<>();
    }

    private Owner getOwnerWithId(int ownerId) {
        ArrayList<Owner> uniqueOwners = new ArrayList<>(allCarsUniqueOwners());
        for (Owner owner : uniqueOwners)
            if (owner.getId() == ownerId)
                return owner;

        return null;
    }

    public Collection<Owner> allCarsUniqueOwners()
    {
        return ownersCars.keySet();
    }

    /**
     * Complexity should be less than O(n)
     */
    public Collection<Car> topThreeCarsByMaxVelocity() {
        ArrayList<Car> threeCars = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            threeCars.add(velocityCars.poll());
        }

        for (int i = 0; i < 3; ++i) {
            velocityCars.add(threeCars.get(i));
        }
        return threeCars;
    }

    /**
     * Complexity should be O(1)
     */
    public Collection<Car> allCarsOfBrand(String brand)
    {
        return brandsCars.get(brand);
    }

    /**
     * Complexity should be less than O(n)
     */
    public Collection<Car> carsWithPowerMoreThan(int power) {
        ArrayList<Car> carsWithMorePower = new ArrayList<>();
        for (Car car : allCars)
            if (car.getPower() > power)
                carsWithMorePower.add(car);

        return carsWithMorePower;
    }

    /**
     * Complexity should be O(1)
     */
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return ownersCars.get(owner);
    }


    /**
     * @return mean value of owner age that has allCars with given brand
     */
    public int meanOwnersAgeOfCarBrand(String brand) {
        ArrayList<Car> carsOfBrand = (ArrayList<Car>) allCarsOfBrand(brand);
        HashSet<Owner> ownersOfBrand = new HashSet<>();
        for (Car car : carsOfBrand)
            ownersOfBrand.add(getOwnerWithId(car.getOwnerId()));

        int ageSum = 0;
        for (Owner owner : ownersOfBrand)
            ageSum += owner.getAge();

        return ageSum / ownersOfBrand.size();
    }

    /**
     * @return mean value of allCars for all ownersCars
     */
    public int meanCarNumberForEachOwner() {
        return allCars.size() / ownersCars.size();
    }

    /**
     * Complexity should be less than O(n)
     * @return removed car
     */
    public Car removeCar(int carId) {
        for (Car car : allCars)
            if (car.getCarId() == carId) {
                allCars.remove(car);

                velocityCars.remove(car);


                Owner ownerOfCar = getOwnerWithId(car.getOwnerId());
                ArrayList<Car> changedCarsOfOwner = ownersCars.getOrDefault(ownerOfCar, new ArrayList<Car>());

                if (changedCarsOfOwner != null) {
                    changedCarsOfOwner.remove(car);
                    ownersCars.put(ownerOfCar, changedCarsOfOwner);
                }

                String brand = car.getBrand();
                ArrayList<Car> changedCarsOfBrand = brandsCars.get(brand);
                if (changedCarsOfBrand != null)
                    changedCarsOfBrand.removeIf(carBrand -> carBrand.getCarId() == carId);


                brandsCars.put(brand, changedCarsOfBrand);

                return car;
            }

        return null;
    }

    /**
     * Complexity should be less than O(n)
     */
    public void addNewCar(Car car, Owner owner) {
        allCars.add(car);
        velocityCars.add(car);

        ArrayList<Car> changedCarsOfOwner = new ArrayList<>();
        if (ownersCars.containsKey(owner))
            changedCarsOfOwner = ownersCars.get(owner);

        changedCarsOfOwner.add(car);
        ownersCars.put(owner, changedCarsOfOwner);

        ArrayList<Car> changedCarsOfBrand = new ArrayList<>();
        String brand = car.getBrand();
        if (brandsCars.containsKey(brand))
            changedCarsOfBrand = brandsCars.get(brand);

        changedCarsOfBrand.add(car);
        brandsCars.put(brand, changedCarsOfBrand);
    }
}