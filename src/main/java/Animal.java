import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Animal{
    public String name;
    public int id;

    public Animal(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Animal)){
            return false;
        }else{
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }


    public static List<Animal> all(){
        String sql = "SELECT * FROM animals;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Animal.class);
        }
    }

    public static Animal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql).addParameter("id", id).throwOnMappingFailure(false).executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name).throwOnMappingFailure(false).executeUpdate().getKey();
        }
    }

    public int getId(){
        return id;
    }

    public List<Sighting> getSightings(){

        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings where animalId =:id";
            return con.createQuery(sql).addParameter("id", this.id).executeAndFetch(Sighting.class);
        }
    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql).addParameter("id", this.id).executeUpdate();
        }
    }
}
