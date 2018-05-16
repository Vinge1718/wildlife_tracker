import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class EndangeredAnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void object_instantiatesAsAnAnimal_true(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        assertTrue(testEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void animal_instantiatesWithName_String(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        assertEquals(testEndangeredAnimal.getName(), "Lion");
    }

    @Test
    public void animal_instantiatesWithHealth_String(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        assertEquals(testEndangeredAnimal.getHealth(), "healthy");
    }

    @Test
    public void animal_instantiatesWithAge_String(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        assertEquals(testEndangeredAnimal.getAge(), "young");
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        assertTrue(testEndangeredAnimal.equals(secondEndangeredAnimal));
    }

    @Test
    public void save_returnsTrueIfDesccriptionIsSame(){
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
    }

    @Test
    public void save_InstantiatesObjectWithId_int(){
        Animal testEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        testEndangeredAnimal.save();
        Animal anotherEndangeredAnimal = EndangeredAnimal.all().get(0);
        assertEquals(testEndangeredAnimal.getId(), anotherEndangeredAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_list(){
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Rhino", "ill", "old");
        secondEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
    }

    @Test
    public void find_returnsEndangeredAnimalObjectBasedOnId_object(){
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Lion", "healthy", "young");
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Boar", "okay", "adult");
        secondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
    }
}
