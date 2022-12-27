package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission{

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        Collection<String> stateExhibits = state.getExhibits(); // добавяме в колекция Exhibits

        for (Explorer currentExplorer : explorers) { // минаваме през всеки Explorer
            while (currentExplorer.canSearch() && stateExhibits.iterator().hasNext()) { // докато експлорерите могат да търсят
                currentExplorer.search();     // търси                                  // и има следваш Exhibit
                String currentExhibit = stateExhibits.iterator().next(); // сегашния Exhibit
                currentExplorer.getSuitcase().getExhibits().add(currentExhibit); // добавяне в куфарчето на изследователя
                stateExhibits.remove(currentExhibit); // махане на Exhibit от колекцията
            }
        }
    }
}
