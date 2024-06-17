import com.ricka.princy.wayguesser.WayGuesser;
import com.ricka.princy.wayguesser.model.Carte;
import com.ricka.princy.wayguesser.model.Lieu;
import com.ricka.princy.wayguesser.model.Marcheur;
import com.ricka.princy.wayguesser.model.Rue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class WayGuesserTest {
    @Test
    void test(){
        var hei = new Lieu("HEI");
        var esti = new Lieu("ESTI");
        var test = new Lieu("Test");
        var bjarn = new Marcheur("Bjarn", hei, esti);
        var carte = new Carte(
            new Rue(hei, esti),
            new Rue("Pullman", test, hei)
        );

        assertEquals(List.of(), WayGuesser.trouverMarcheurChemin(bjarn, carte));
    }
}