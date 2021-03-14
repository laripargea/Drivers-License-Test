package lab5Tests;

import edu.ubb.repository.DateiLesen;
import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DateiLesenTest {

    @Test
    public void testDurchgehenDateiPositive() throws FileNotFoundException {
        assertEquals(40, DateiLesen.durchgehenDatei().size());
    }

    @Test
    public void testDurchgehenDateiNegative() throws FileNotFoundException {
        assertNotEquals(100, DateiLesen.durchgehenDatei().size());
    }

    @Test
    public void testLesenAntwortenPositive() throws FileNotFoundException {
        assertEquals(2, DateiLesen.lesenAntworten().size());
    }

    @Test
    public void testLesenAntwortenNegative() throws FileNotFoundException {
        assertNotEquals(100, DateiLesen.lesenAntworten().size());
    }
}