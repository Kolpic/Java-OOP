package rpg_lab;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class HeroTest {

    @Test
    public void testAttackWhenTargetDiesHeroGetsExp() {

        Target facade = mock(Target.class);

        int exp = 10;
        when(facade.isDead()).thenReturn(true);
        when(facade.giveExperience()).thenReturn(exp);

        Weapon weapon = mock(Weapon.class);

        Hero hero = new Hero("Pesho", weapon);

        hero.attack(facade);

        Assert.assertEquals(exp,hero.getExperience());
    }

}