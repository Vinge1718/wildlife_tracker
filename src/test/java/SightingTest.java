import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule(); 

    @Test
    public void sighting_instantiatesCorrectly_true(){
        Sighting testSighting = new Sighting("john", "North Forest");
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void getName_personInstantiatesWithName_John(){
        Sighting testSighting = new Sighting("John", "North Forest");
        assertEquals("John", testSighting.getName());
    }

    @Test
    public void getLocation_sightingInstatiatesWithLocation_String(){
        Sighting testSighting = new Sighting("John", "North Forest");
        assertEquals("North Forest", testSighting.getLocation());
    }

    @Test
    public void equals_returnsTrueIfNameAndLocationAreSame_true(){
        Sighting testSighting = new Sighting("John", "North Forest");
        Sighting secondSighting  = new Sighting("John", "North Forest");
        assertTrue(testSighting.equals(secondSighting));
    }
}
