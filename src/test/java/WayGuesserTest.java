import com.ricka.princy.wayguesser.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WayGuesserTest {
    @Test
    void bjarni_de_hei_vers_esti(){
        var sekolintsika = new Lieu("Sekolintsika");
        var marais = new Lieu("Marais");
        var hei = new Lieu("HEI");
        var esti = new Lieu("ESTI");
        var pullman = new Lieu("Pullman");
        var boulevardDeLEurope = new Lieu("Boulveard De L'Europe");
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
            new Rue(balancoire, boulevardDeLEurope),
            new Rue(balancoire, esti),
            new Rue(boulevardDeLEurope, esti)
        )));
        
        var trajets = bjarni.marcher(hei, esti);

        assertEquals(trajets.getLast(), esti);
        assertTrue(trajets.contains(balancoire));
    }

    @Test
    void bjarni_de_esti_vers_marais(){
        var sekolintsika = new Lieu("Sekolintsika");
        var marais = new Lieu("Marais");
        var hei = new Lieu("HEI");
        var esti = new Lieu("ESTI");
        var pullman = new Lieu("Pullman");
        var boulevardDeLEurope = new Lieu("Boulveard De L'Europe");
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
            new Rue(balancoire, boulevardDeLEurope),
            new Rue(balancoire, esti),
            new Rue(boulevardDeLEurope, esti)
        )));

        var trajets = bjarni.marcher(esti, marais);
        assertEquals(trajets.getLast(), marais);
        assertTrue(trajets.containsAll(List.of(balancoire, hei, sekolintsika, marais)));
    }
}