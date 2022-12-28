package christmasPastryShop.repositories;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.repositories.interfaces.CocktailRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {

    private Collection<Cocktail> models;

    public CocktailRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Cocktail> getAll() {
        return Collections.unmodifiableCollection(models);
    }

    @Override
    public void add(Cocktail cocktail) {
        models.add(cocktail);
    }

    @Override
    public Cocktail getByName(String name) {
        return models.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
