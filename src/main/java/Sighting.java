import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class Sighting{
    private String name;
    private String location;
    private int id;
    private int animalId;
    private Timestamp lastSeen;

    public Sighting(String name, String location, int animalId){
        this.name = name;
        this.location = location;
        this.animalId = animalId;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public int getAnimalId(){
        return animalId;
    }

    @Override
    public boolean equals(Object testSighting){
        if(!(testSighting instanceof Sighting)){
            return false;
        }else{
            Sighting otherSighting = (Sighting) testSighting;
            return this.getName().equals(otherSighting.getName()) && this.getLocation().equals(otherSighting.getLocation());
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (name, location, animalId, lastseen) VALUES (:name, :location, :animalId, now())";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name).addParameter("location", this.location).addParameter("animalId", this.animalId).throwOnMappingFailure(false).executeUpdate().getKey();
        }
    }

    public int getId(){
        return id;
    }

    public static List<Sighting> all(){
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings where id = :id";
            Sighting sighting = con.createQuery(sql).addParameter("id", id).throwOnMappingFailure(false).executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

    public Timestamp getLastSeen(){
        return lastSeen;
    }

    public void delete(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql).addParameter("id", this.id).executeUpdate();
        }
    }
}
