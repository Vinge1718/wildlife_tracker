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
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, health, age) VALUES (:name, :health, :age)";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name).addParameter("health", this.health
            ).addParameter("age", this.age).throwOnMappingFailure(false).executeUpdate().getKey();
        }
    }
}
