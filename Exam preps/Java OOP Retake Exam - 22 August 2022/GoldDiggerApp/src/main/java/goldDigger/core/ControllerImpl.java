package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    DiscovererRepository discovererRepository;
    SpotRepository spotRepository;

    private int inspectedSpotCount = 0;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;

        if (kind.equals("Geologist")) {
            discoverer = new Geologist(discovererName);
        } else if (kind.equals("Archaeologist")) {
            discoverer = new Archaeologist(discovererName);
        } else if (kind.equals("Anthropologist")) {
            discoverer = new Anthropologist(discovererName);
        } else {
            throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED,kind,discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        for (String currentExhibit : exhibits) {
            spot.getExhibits().add(currentExhibit);
        }
        spotRepository.add(spot);
        return String.format(SPOT_ADDED,spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.getCollection().stream()
                .filter(e -> e.getName().equals(discovererName))
                .findFirst()
                .orElse(null);

        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST,discovererName));
        }

        discovererRepository.remove(discoverer);

        return String.format(DISCOVERER_EXCLUDE,discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {

        Spot spot = spotRepository.byName(spotName);

        List<Discoverer> suitableDiscoverers =
                discovererRepository.getCollection()
                        .stream()
                        .filter(discoverer -> discoverer.getEnergy() > 45)
                        .collect(Collectors.toList());

        if (suitableDiscoverers.isEmpty()) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Operation operation = new OperationImpl();
        operation.startOperation(spot,suitableDiscoverers);

        long tiredDiscoverers = suitableDiscoverers.stream()
                .filter(discoverer -> discoverer.getEnergy() == 0)
                .count();
        inspectedSpotCount++;
        return String.format(INSPECT_SPOT,spotName,tiredDiscoverers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(inspectedSpotCount).append(" spots were inspected.").append(System.lineSeparator());

        sb.append("Information for the discoverers:");

        for (Discoverer currentDiscoverer : discovererRepository.getCollection()) {
            sb.append(System.lineSeparator());
            sb.append("Name: ").append(currentDiscoverer.getName()).append(System.lineSeparator());

            sb.append("Energy: ").append(String.format("%.0f",currentDiscoverer.getEnergy())).append(System.lineSeparator());

            if (currentDiscoverer.getMuseum().getExhibits().isEmpty()) {
                sb.append("Museum exhibits: ").append("None");
            } else {
                sb.append("Museum exhibits: ").append(String.join(", ",currentDiscoverer.getMuseum().getExhibits()));
            }

        }

        return sb.toString();
    }
}
