package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class AddCommand extends Command{

    public AddCommand(String[] data, Repository repository, UnitFactory factory) {
        super(data, repository, factory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String unitType = getData()[1];
        Unit unitToAdd = getFactory().createUnit(unitType);
        getRepository().addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
