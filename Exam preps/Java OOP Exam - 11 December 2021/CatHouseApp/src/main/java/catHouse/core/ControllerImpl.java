package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.houses = new ArrayList<>();
        this.toys = new ToyRepository();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;

        if (type.equals("ShortHouse")) {
            house = new ShortHouse(name);
        } else if (type.equals("LongHouse")) {
            house = new LongHouse(name);
        } else {
            house = null;
        }

        if (house == null) {
            throw new NullPointerException(INVALID_HOUSE_TYPE);
        } else {
            houses.add(house);
            return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
        }

    }

    @Override
    public String buyToy(String type) {
        Toy toy;

        if (type.equals("Ball")) {
            toy = new Ball();
        } else if (type.equals("Mouse")) {
            toy = new Mouse();
        } else {
            toy = null;
        }

        if (toy == null) {
            throw new IllegalArgumentException(INVALID_TOY_TYPE);
        } else {
            toys.buyToy(toy);
            return String.format(SUCCESSFULLY_ADDED_TOY_TYPE,type);
        }
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = toys.findFirst(toyType);

        if (toy == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND,toyType));
        }

        House house = houses.stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .orElse(null);

        house.buyToy(toy);
        toys.removeToy(toy);
        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        House house = houses.stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .orElse(null);

        if (catType.equals("ShorthairCat")){
            cat = new ShorthairCat(catName,catBreed,price);
        } else if (catType.equals("LonghairCat")) {
            cat = new LonghairCat(catName,catBreed,price);
        } else {
            cat = null;
        }

        if (cat == null) {
            throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

//        houseName.equals("ShortHouse")
//        houseName.equals("LongHouse")


        if (catType.equals("ShorthairCat") && house.getClass().getSimpleName().equals("ShortHouse")) {
            house.addCat(cat);
        } else if (catType.equals("LonghairCat") && house.getClass().getSimpleName().equals("LongHouse")) {
            house.addCat(cat);
        } else {
            return String.format(UNSUITABLE_HOUSE);
        }

        return String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);

    }

    @Override
    public String feedingCat(String houseName) {
        House house = houses.stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .orElse(null);

        house.getCats().stream().forEach(Cat::eating);
        return String.format(FEEDING_CAT,house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.stream()
                .filter(e -> e.getName().equals(houseName))
                .findFirst()
                .orElse(null);

        int sumCatPrice = 0;
        int sumToyPrice = 0;

        for (Cat cat : house.getCats()) {
            sumCatPrice += cat.getPrice();
        }

        for (Toy toy : house.getToys()) {
            sumToyPrice += toy.getPrice();
        }

        double finalSum = sumCatPrice + sumToyPrice;

        return String.format(VALUE_HOUSE,houseName,finalSum);
    }

    @Override
    public String getStatistics() {
      StringBuilder sb = new StringBuilder();

      for (House house : houses) {
          sb.append(house.getStatistics()).append(System.lineSeparator());
      }

      return sb.toString();
    }
}