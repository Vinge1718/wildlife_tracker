import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

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
}
