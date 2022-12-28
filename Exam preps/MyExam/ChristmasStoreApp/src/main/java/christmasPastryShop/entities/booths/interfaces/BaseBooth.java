package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.BoothRepositoryImpl;
import christmasPastryShop.repositories.CocktailRepositoryImpl;
import christmasPastryShop.repositories.DelicacyRepositoryImpl;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.ArrayList;
import java.util.Collection;

import static christmasPastryShop.common.ExceptionMessages.*;

public abstract class BaseBooth implements Booth {

    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    public BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.boothNumber = boothNumber;
        this.setCapacity(capacity);
        this.setNumberOfPeople(capacity);
        this.pricePerPerson = pricePerPerson;

        this.delicacyOrders = new ArrayList<>();
        this.cocktailOrders = new ArrayList<>();
        this.isReserved = false;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    private void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    @Override
    public int getBoothNumber() {
        return boothNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        isReserved = true;
        setNumberOfPeople(numberOfPeople);
        price = numberOfPeople * pricePerPerson;
    }

    @Override
    public double getBill() {

        int sumDelicacies = 0;
        for (Delicacy currentDel : delicacyOrders) {
            sumDelicacies += currentDel.getPrice();
        }

        int sumCocktails = 0;
        for (Cocktail currentCoc : cocktailOrders) {
            sumCocktails += currentCoc.getPrice();
        }

        return price + sumDelicacies + sumCocktails;
    }

    @Override
    public void clear() {
        delicacyOrders.clear();
        cocktailOrders.clear();
        price = 0;
        isReserved = false;
        numberOfPeople = 0;
    }
}
