package christmasPastryShop.repositories;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DelicacyRepositoryImpl implements DelicacyRepository<Delicacy> {

    private Collection<Delicacy> models;

    public DelicacyRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Delicacy> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Delicacy delicacy) {
        models.add(delicacy);

    }

    @Override
    public Delicacy getByName(String name) {
        return models.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
