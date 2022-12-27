package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer>{

    private Map<String, Explorer> explorersCollection;

    public ExplorerRepository() {
        this.explorersCollection = new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableMap(explorersCollection).values();
    }

    @Override
    public void add(Explorer entity) {
        explorersCollection.put(entity.getName(), entity);
    }

    @Override
    public boolean remove(Explorer entity) {

        return explorersCollection.remove(entity.getName()) != null;

//        if (explorersCollection.containsKey(entity.getName())) {
//            explorersCollection.remove(entity.getName());
//            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public Explorer byName(String name) {
       return explorersCollection.get(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        explorersCollection.values().forEach(explorer -> sb.append(explorer).append(System.lineSeparator()));
        return sb.toString();
    }
}
