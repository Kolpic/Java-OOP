package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation{

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {

        for (Discoverer currentDiscoverer : discoverers) {
            while (currentDiscoverer.canDig() && spot.getExhibits().iterator().hasNext()) {

                String currentExhibit = spot.getExhibits().iterator().next();

                    currentDiscoverer.dig();

//                    currentDiscoverer.getMuseum().getExhibits().add(currentExhibit);
                    Museum museum1 = currentDiscoverer.getMuseum();
                    museum1.getExhibits().add(currentExhibit);

                    spot.getExhibits().remove(currentExhibit);

            }
        }
    }
}
