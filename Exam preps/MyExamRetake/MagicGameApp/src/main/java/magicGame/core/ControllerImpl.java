package magicGame.core;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.MagicianImpl;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.*;
import java.util.stream.Collectors;

import static magicGame.common.ExceptionMessages.*;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGIC;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN;

public class ControllerImpl implements Controller{

    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Collection<Magician> magiciansCollection;
    private Region region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.magiciansCollection = new ArrayList<>();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;

        if (type.equals("RedMagic")) {
            magic = new RedMagic(name,bulletsCount);
        } else if (type.equals("BlackMagic")) {
            magic = new BlackMagic(name, bulletsCount);
        } else {
            magic = null;
        }

        if (magic == null) {
            throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }

        magics.addMagic(magic);
        return String.format(SUCCESSFULLY_ADDED_MAGIC,name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magician magician;
        Magic magic = magics.findByName(magicName);

        if (magic == null) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }

        if (type.equals("Wizard")) {
            magician = new Wizard(username,health,protection,magic);
        } else if (type.equals("BlackWidow")) {
            magician = new BlackWidow(username,health,protection,magic);
        } else {
            magician = null;
        }

        if (magician == null) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }

        magicians.addMagician(magician);
        magiciansCollection.add(magician);
        return String.format(SUCCESSFULLY_ADDED_MAGICIAN,username);
    }

    @Override
    public String startGame() {
        return region.start(magiciansCollection);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
//        "{magician type}: {magician username}
//        Health: {magician health}
//        Protection: {magician protection}
//        Magic: {magician magic name}"

        List<Magician> output = new ArrayList<>();
        output = (List<Magician>) magiciansCollection;

        Collections.sort(output,
                Comparator.comparing(Magician::getHealth)
                        .thenComparing(Magician::getUsername));


//        output = magiciansCollection.stream()
//                .(Magician::getHealth)
//                .sorted()
//                .collect(Collectors.toList());

        for (Magician current : output){
            sb.append(String.format("%s: %s%n" +
                    "Health: %d%n" +
                    "Protection: %d%n" +
                            "Magic: %s", current.getClass().getSimpleName(), current.getUsername()
                    ,current.getHealth(), current.getProtection(),current.getMagic().getName()));
        }

        return sb.toString();
    }
}
