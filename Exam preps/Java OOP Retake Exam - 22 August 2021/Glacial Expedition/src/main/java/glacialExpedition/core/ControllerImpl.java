package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;
import jdk.dynalink.linker.LinkerServices;

import java.util.Collection;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;

    private int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        exploredStates = 0;
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;

            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;

            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;

            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
       State state = new StateImpl(stateName);
        for (String exhibitToBeAdded : exhibits) {
            state.getExhibits().add(exhibitToBeAdded);
        }
//Collections.addAll(state.getExhibits(), exhibits)
        stateRepository.add(state);
        return String.format(STATE_ADDED,stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer ToBeRemoved = explorerRepository.byName(explorerName);
        if (ToBeRemoved != null) {
            explorerRepository.remove(ToBeRemoved);
            return String.format(EXPLORER_RETIRED, explorerName);
        } else {
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST,explorerName));
        }

    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> validExplorers = explorerRepository
                .getCollection()
                .stream()
                .filter(e -> e.getEnergy() > 50)
                .collect(Collectors.toList());

        if (validExplorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State stateToExplore = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(stateToExplore,validExplorers);

        long countOfTiredExplorers = validExplorers.stream().filter(e -> e.getEnergy() == 0).count();
        exploredStates++;
        return String.format(STATE_EXPLORER,stateName,countOfTiredExplorers);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_STATE_EXPLORED,exploredStates));
        sb.append(System.lineSeparator());
        sb.append(FINAL_EXPLORER_INFO);
        sb.append(System.lineSeparator());

        sb.append(explorerRepository.toString());
        return sb.toString().trim();
    }
}
