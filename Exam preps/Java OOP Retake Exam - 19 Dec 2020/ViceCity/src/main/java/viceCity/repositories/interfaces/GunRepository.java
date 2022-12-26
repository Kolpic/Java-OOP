package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.Collection;

public class GunRepository implements Repository<Gun>{

    private Collection<Gun> modes;

    @Override
    public Collection<Gun> getModels() {
        return null;
    }

    @Override
    public void add(Gun model) {

    }

    @Override
    public boolean remove(Gun model) {
        return false;
    }

    @Override
    public Gun find(String name) {
        return null;
    }
}
