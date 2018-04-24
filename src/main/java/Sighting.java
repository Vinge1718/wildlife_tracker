import org.sql2o.*;

public class Sighting{
    private String mName;
    private String mLocation;

    public Sighting(String name, String location){
        this.mName = name;
        this.mLocation = location;
    }

    public String getName(){
        return mName;
    }

    public String getLocation(){
        return mLocation;
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
            String sql = "INSERT INTO sightings (name, location) VALUES (:name, :location)";
            con.createQuery(sql).addParameter("name", this.mName).addParameter("location", this.mLocation).executeUpdate();
        }
    }
}
