package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegionImpl implements Region{
    @Override
    public String start(Collection<Magician> magicians) {

        List<Magician> wizards = new ArrayList<>();
        List<Magician> blackWidows = new ArrayList<>();

        for (Magician magician : magicians) {
            String name = magician.getClass().getSimpleName();
            if (magician.getClass().getSimpleName().equals("Wizard")) {
                wizards.add(magician);
            } else if (magician.getClass().getSimpleName().equals("BlackWidow")) {
                blackWidows.add(magician);
            }
        }

        while ((wizards.iterator().next().isAlive() || blackWidows.iterator().next().isAlive())
        && (wizards.iterator().next().getMagic().getBulletsCount() > 0
                || blackWidows.iterator().next().getMagic().getBulletsCount() > 0)) {
            Magician wizard = wizards.get(0);
            Magician blackWidow = blackWidows.get(0);

            int firedShotByWizard = wizard.getMagic().fire();

            for (Magician widow : blackWidows) {
                widow.takeDamage(firedShotByWizard);
            }

            int firedShotByBlackWidow = blackWidow.getMagic().fire();

            for (Magician currentWizard : wizards) {
                currentWizard.takeDamage(firedShotByBlackWidow);
            }

            for (Magician magician : wizards) {

                if (!magician.isAlive()) {
                    wizards.remove(magician);
                }

            }

            for (Magician widow : blackWidows) {

                if (!widow.isAlive()) {
                    blackWidows.remove(widow);
                }

            }
        }

        if (wizards.size() > blackWidows.size()) {
            return "Wizards win!";
        } else {
            return "Black widows win!";
        }
    }
}
