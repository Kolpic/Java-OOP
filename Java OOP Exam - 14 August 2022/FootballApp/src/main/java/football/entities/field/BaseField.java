package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;

import static football.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static football.common.ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY;

public abstract class BaseField implements Field{

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int sumEnergy() {
        return supplements.stream()
                .mapToInt(Supplement::getEnergy)
                .sum();
//        int sum = 0;
//        for (Supplement supplement : supplements) {
//            sum += supplement.getEnergy();
//        }
//        return sum;
    }

    @Override
    public void addPlayer(Player player) {
        if (players.size() < capacity) {
            players.add(player);
        } else {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement)  {
        supplements.add(supplement);
    }

    @Override
    public void drag() {
        for (Player currentPlayer : players) {
            currentPlayer.stimulation();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(" ").append("(")
                .append(getClass().getSimpleName())
                .append("):").append(System.lineSeparator());


        builder.append("Player: ");
        if (getPlayers().size() == 0) {
            builder.append("None");
        } else {
            for (Player currentPlayer : getPlayers()) {
                builder.append(currentPlayer.getName()).append(" ");
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(System.lineSeparator());
        builder.append("Supplement: ").append(supplements.size()).append(System.lineSeparator());
        builder.append("Energy: ").append(sumEnergy());

        return builder.toString().trim();
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public String getName() {
        return name;
    }
}
