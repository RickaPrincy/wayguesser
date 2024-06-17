import com.ricka.princy.wayguesser.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

public class WayGuesserTest {
    @Test
    void bjarni_test(){
        var sekolintsika = new Lieu("Sekolintsika");
        var marais = new Lieu("Marais");
        var hei = new Lieu("HEI");
        var esti = new Lieu("ESTI");
        var pullman = new Lieu("Pullman");
        var boulveardDeLEurope = new Lieu("Boulveard De L'Europe");
        var nexta = new Lieu("Nexta");
        var balancoire = new Lieu("Balancoire");

        var bjarni = new Marcheur("Bjarni");
        var tana = new Carte(new HashSet<>(Set.of(
            new Rue(marais, sekolintsika),
            new Rue(sekolintsika, hei),
            new Rue("Rue Andriatsihorana", hei, pullman),
            new Rue(hei, balancoire),
            new Rue(pullman, nexta),
            new Rue(pullman, balancoire),
            new Rue(balancoire, boulveardDeLEurope),
            new Rue(balancoire, esti),
            new Rue(boulveardDeLEurope, esti)
        )));

        var result = bjarni.marcher(tana, hei, esti);
        var lastRue = result.getLast();
        assertTrue(lastRue.getLieuA().equals(esti) || lastRue.getLieuB().equals(esti));
    }
}