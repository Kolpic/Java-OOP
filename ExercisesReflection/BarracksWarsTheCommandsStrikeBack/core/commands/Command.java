package barracksWars.core.commands;

import barracksWars.interfaces.Executable;
import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public abstract class Command implements Executable {

    private String[] data;
    private Repository repository;
    private UnitFactory factory;

    public Command(String[] data, Repository repository, UnitFactory factory) {
        this.data = data;
        this.repository = repository;
        this.factory = factory;
    }

    public String[] getData() {
        return data;
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getFactory() {
        return factory;
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        return null;
    }
}
