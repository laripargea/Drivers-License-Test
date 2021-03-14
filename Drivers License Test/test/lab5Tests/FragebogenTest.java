package lab5Tests;

import edu.ubb.model.Frage;
import edu.ubb.model.Fragebogen;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FragebogenTest {
    @Test
    public void testRandomFragenPositive() throws FileNotFoundException {
        List<Frage>fragen= new ArrayList<>();
        Fragebogen fb = new Fragebogen(0, fragen);
        assertEquals(26, fb.getFragenListe().size());
    }

    @Test
    public void testRandomFragenNegative() throws FileNotFoundException {
        List<Frage>fragen= new ArrayList<>();
        Fragebogen fb = new Fragebogen(0, fragen);
        assertNotEquals(77, fb.getFragenListe().size());
    }
}