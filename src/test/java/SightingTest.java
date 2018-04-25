import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

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

    @Test
    public void save_insertsObjectIntoDatabase_Sighting(){
        Sighting testSighting = new Sighting("John", "North Forest");
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfASighting(){
        Sighting firstSighting = new Sighting("John", "North Forest");
        firstSighting.save();
        Sighting secondSighting = new Sighting("Beth", "SE Forest");
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void save_assignesIdToSavedObjects(){
        Sighting testSighting = new Sighting("John", "North Forest");
        testSighting.save();
        Sighting anotherSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(), anotherSighting.getId());
    }

    @Test
    public void find_findObjectWithId(){
        Sighting testSighting =new Sighting("John", "North Forest");
        testSighting.save();
        Sighting secondSighting = new Sighting("Jane", "SE Forest");
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }
}
