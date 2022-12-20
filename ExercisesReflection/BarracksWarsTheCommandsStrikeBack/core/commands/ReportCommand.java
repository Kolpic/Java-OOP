package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class ReportCommand extends Command{

    public ReportCommand(String[] data, Repository repository, UnitFactory factory) {
        super(data, repository, factory);
    }

    @Override
    public String execute() {
        return this.getRepository().getStatistics();
    }
}
