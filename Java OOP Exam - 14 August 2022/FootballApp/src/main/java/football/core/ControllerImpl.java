package football.core;


import football.entities.field.ArtificialTurf;
import football.entities.field.BaseField;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Man;
import football.entities.player.Player;
import football.entities.player.Woman;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement;
    private Map<String,Field> fields;

    private Map<String, Player> playerMap;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new LinkedHashMap<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field;
        switch (fieldType) {
            case "ArtificialTurf":
                field = new ArtificialTurf(fieldName);
                 fields.put(fieldName,field);
                return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE,fieldType);

            case "NaturalGrass":
                field = new NaturalGrass(fieldName);
                fields.put(fieldName,field);
                return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE,fieldType);

            default:
                throw new NullPointerException(INVALID_FIELD_TYPE);
        }
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement supp;
        switch (type) {
            case "Powdered":
                supp = new Powdered();
                supplement.add(supp);
                return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,type);

            case "Liquid":
                supp = new Liquid();
                supplement.add(supp);
                return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE,type);

            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {

        Supplement s = supplement.findByType(supplementType);

        if (s == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND,supplementType));
        }

        Field field = fields.get(fieldName);
        field.addSupplement(s);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD,supplementType,fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Player p;

        if (playerType.equals("Women")) {
            p = new Woman(playerName, nationality, strength);
        } else if (playerType.equals("Men")) {
            p = new Man(playerName, nationality, strength);
        } else {
            throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }

        Field field = fields.get(fieldName);

        String output;

        if (!playerCanPlayOnFieldType(playerType, field)) {
            output = FIELD_NOT_SUITABLE;
        } else {
            field.addPlayer(p);
            output = String.format(SUCCESSFULLY_ADDED_PLAYER_IN_FIELD,playerType,fieldName);
        }

        return output;
    }

    private boolean playerCanPlayOnFieldType(String playerType, Field field) {

        String fieldType = field.getClass().getSimpleName();

        boolean canPlay = playerType.equals("Women") && fieldType.equals("ArtificialTurf");

        if (!canPlay) {
            canPlay = playerType.equals("Men") && fieldType.equals("NaturalGrass");
        }

        return canPlay;
    }

    @Override
    public String dragPlayer(String fieldName) {
       Field fieldToDrag = fields.get(fieldName);

       fieldToDrag.drag();

       return String.format(PLAYER_DRAG, fieldToDrag.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = fields.get(fieldName);
//        int sumEnergy = field.sumEnergy();
        int sumEnergy = field.getPlayers()
                .stream().mapToInt(Player::getStrength)
                .sum();
        return String.format(STRENGTH_FIELD,fieldName,sumEnergy);

    }

    @Override
    public String getStatistics() {
       return fields.values().stream()
                .map(Field::getInfo)
                .collect(Collectors.joining(System.lineSeparator()));

    }
}
