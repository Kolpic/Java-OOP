package christmasPastryShop.core;

import christmasPastryShop.common.OutputMessages;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static christmasPastryShop.common.ExceptionMessages.*;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;

    private double finalBoothSum;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
        finalBoothSum = 0;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = null;

        if (delicacyRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST,type,name));
        }

        if (type.equals("Gingerbread")) {
            delicacy = new Gingerbread(name,price);
        } else if (type.equals("Stolen")) {
            delicacy = new Stolen(name,price);
        }

        delicacyRepository.add(delicacy);
        return String.format(DELICACY_ADDED,name,type);

    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = null;

        if (cocktailRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST,type,name));
        }

        if (type.equals("Hibernation")) {
            cocktail = new Hibernation(name,size, brand);
        } else if (type.equals("MulledWine")) {
            cocktail = new MulledWine(name, size, brand);
        }

        cocktailRepository.add(cocktail);
        return String.format(COCKTAIL_ADDED,name,brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = null;

        if (boothRepository.getByNumber(boothNumber) != null) {
            throw new IllegalArgumentException(String.format(BOOTH_EXIST,boothNumber));
        }

        if (type.equals("OpenBooth")) {
            booth = new OpenBooth(boothNumber,capacity);

        } else if (type.equals("PrivateBooth")) {
            booth = new PrivateBooth(boothNumber,capacity);
        }

        boothRepository.add(booth);

        return String.format(BOOTH_ADDED,boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {

//        Optional<Booth> optionalBooth = boothRepository.getAll().stream()
//                .filter(booth -> !booth.isReserved() && booth.getCapacity() >= numberOfPeople)
//                .findFirst();
//
//        if (optionalBooth.isEmpty()) {
//            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
//        }
//
//        Booth booth = optionalBooth.get();
//        booth.reserve(numberOfPeople);
//
//        return String.format(OutputMessages.BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);

       Collection<Booth> allBooths = boothRepository.getAll();
        for (Booth currentBooth : allBooths) {
            if (allBooths.iterator().hasNext() && !currentBooth.isReserved()) {
                if (currentBooth.isReserved()) {
                    break;
                }
                currentBooth.reserve(numberOfPeople);
                currentBooth.isReserved();
                return String.format(BOOTH_RESERVED,currentBooth.getBoothNumber(),numberOfPeople);
            }
        }
        return String.format(RESERVATION_NOT_POSSIBLE,numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {

//        Booth booth = boothRepository.getByNumber(boothNumber);
//
//        final double bill = booth.getBill();
//        this.finalBoothSum += bill;
//
//        booth.clear();
//
//        return String.format(OutputMessages.BILL, boothNumber, bill);

        for (Booth currentBooth : boothRepository.getAll()) {
            if (currentBooth.getBoothNumber() == boothNumber) {

                final double currentBill = currentBooth.getBill();
               finalBoothSum += currentBooth.getBill();
               currentBooth.clear();

               return String.format(BILL,boothNumber,currentBill);
            }
        }
        return null;
    }

    @Override
    public String getIncome() {
        return String.format(TOTAL_INCOME,finalBoothSum);
    }
}
