package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class RetireCommand extends Command{

    public RetireCommand(String[] data, Repository repository, UnitFactory factory) {
        super(data, repository, factory);
    }

    @Override
    public String execute() {
        try {

            String unitType = getData()[1];
            getRepository().removeUnit(unitType);
            return String.format("%s retired!",unitType);

        } catch (IllegalArgumentException e) {

           return e.getMessage();

        }
    }
}
