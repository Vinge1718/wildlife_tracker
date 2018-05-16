import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void object_instantiatesAsAnAnimal_true(){
        Animal testAnimal = new Animal("Lion");
        assertTrue(testAnimal instanceof Animal);
    }

    @Test
    public void animal_instantiatesWithName_String(){
        Animal testAnimal = new Animal("Lion");
        assertEquals(testAnimal.getName(), "Lion");
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Animal testAnimal = new Animal("Lion");
        Animal secondAnimal = new Animal("Lion");
        assertTrue(testAnimal.equals(secondAnimal));
    }

    @Test
    public void save_returnsTrueIfDescriptionIsSame(){
        Animal testAnimal = new Animal("Lion");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void save_InstantiatesObjectWithId_int(){
        Animal testAnimal = new Animal("Lion");
        testAnimal.save();
        Animal anotherAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), anotherAnimal.getId());
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_list(){
        Animal firstAnimal = new Animal("Lion");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Rhino");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalObjectBasedOnId_object(){
        Animal firstAnimal = new Animal("Lion");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Boar");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }


    // @Test
    // public void animals_sightingCanReturnAllAnimalsAssociated_list(){
    //    Animal testAnimal = new Animal("Lion");
    //    Sighting firstSighting = new Sighting("John", "North Forest", testAnimal.getId());
    //    firstSighting.save();
    //    Sighting secondSighting = new Sighting("Jane", "SE Forest", testAnimal.getId());
    //    secondSighting.save();
    //    Object[] sightings = new Object[] {firstSighting, secondSighting};
    //    assertTrue(testAnimal.getSightings().containsAll(Arrays.asList(sightings)));
    // }
}
