import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Animal {
    public String health;
    public String age;
    public static final String HEALTH_1 = "healthy";
    public static final String HEALTH_2 = "okay";
    public static final String HEALTH_3 = "ill";
    public static final String AGE_1 = "newborn";
    public static final String AGE_2 = "young";
    public static final String AGE_3 = "adult";

    public EndangeredAnimal(String name, String health, String age){
        super(name);
        this.health = health;
        this.age = age;
    }

    public String getHealth(){
        return health;
    }

    public String getAge(){
        return age;
    }

    public static EndangeredAnimal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals where id=:id";
            EndangeredAnimal animal = con.createQuery(sql).addParameter("id", id).throwOnMappingFailure(false).executeAndFetchFirst(EndangeredAnimal.class);
            return animal;
        }
    }

    @Override
    public void save(){
        if ((!health.equals(EndangeredAnimal.HEALTH_1)
            && !health.equals(EndangeredAnimal.HEALTH_2)
            && !health.equals(EndangeredAnimal.HEALTH_3))
            || (!age.equals(EndangeredAnimal.AGE_1)
            && !age.equals(EndangeredAnimal.AGE_2)
            && !age.equals(EndangeredAnimal.AGE_3))) {
      throw new IllegalArgumentException("Please select from ill, okay, healthy or newborn, young, adult");
  }
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, health, age) VALUES (:name, :health, :age)";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name).addParameter("health", this.health
            ).addParameter("age", this.age).throwOnMappingFailure(false).executeUpdate().getKey();
        } catch (IllegalArgumentException exception){}
    }
}
