package christmasRaces.core;

import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.CarRepository;
import christmasRaces.repositories.DriverRepository;
import christmasRaces.repositories.RaceRepository;
import christmasRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository, Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        if (driverRepository.getByName(driver) == null) {

            Driver driverToBeCreated = new DriverImpl(driver);
            driverRepository.add(driverToBeCreated);
            return String.format(DRIVER_CREATED,driver);

        } else {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS,driver));
        }
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car;

        if(type.equals("Muscle")) {
            car = new MuscleCar(model,horsePower);

            for (Car currentCar : carRepository.getAll()) {
                if (currentCar == car) {
                    throw new IllegalArgumentException(String.format(CAR_EXISTS,model));
                }
            }

            carRepository.add(car);
            return String.format(CAR_CREATED,"MuscleCar",model);

        } else if (type.equals("Sports")) {
            car = new SportsCar(model,horsePower);

            for (Car currentCar : carRepository.getAll()) {
                if (currentCar == car) {
                    throw new IllegalArgumentException(String.format(CAR_EXISTS,model));
                }
            }

            carRepository.add(car);
            return String.format(CAR_CREATED,"SportsCar",model);
        } else return null;
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        Car car = carRepository.getByName(carModel);

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND,driverName));
        }

        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND,carModel));
        }

        driver.addCar(car);
        return String.format(CAR_ADDED,driverName,carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        Driver driver = driverRepository.getByName(driverName);

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND,raceName));
        }

        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND,driverName));
        }

        race.addDriver(driver);
        return String.format(DRIVER_ADDED,driverName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);

        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND,raceName));
        }

        if (race.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID,raceName,3));
        }

        int laps = race.getLaps();

        Collection<Driver> drivers = race.getDrivers();

        List<Driver> winners = drivers.stream()
                        .sorted((firstDriver, secondDriver) ->
                           (int)(secondDriver.getCar().calculateRacePoints(laps) -
                                   firstDriver.getCar().calculateRacePoints(laps)))
                .limit(3).collect(Collectors.toList());

        raceRepository.remove(race);

        StringBuilder sb = new StringBuilder();

        sb.append(String.format(DRIVER_FIRST_POSITION,winners.get(0).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION,winners.get(1).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION,winners.get(2).getName(),raceName));

        return sb.toString();

    }

    @Override
    public String createRace(String name, int laps) {
        Race race = raceRepository.getByName(name);

        if (race == null) {

            race = new RaceImpl(name,laps);
            raceRepository.add(race);
            return String.format(RACE_CREATED,name);

        } else {
            throw new IllegalArgumentException(String.format(RACE_EXISTS,name));
        }
    }
}
