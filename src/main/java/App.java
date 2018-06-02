import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args){
        ProcessBuilder process = new ProcessBuilder();
         Integer port;
         if (process.environment().get("PORT") != null) {
             port = Integer.parseInt(process.environment().get("PORT"));
         } else {
             port = 4567;
         }
        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("sightings", Sighting.all());
            model.put("AnimalClass", Animal.class);
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/unprotectedAnimalSighting", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            String animalName = request.queryParams("animalName");
            Animal newAnimal = new Animal(animalName);
            newAnimal.save();
            Sighting newSighting = new Sighting(rangerName, location, newAnimal.getId());
            newSighting.save();
            response.redirect("/");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/endangeredAnimalSighting", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            String animalName = request.queryParams("animalName");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            EndangeredAnimal newAnimal = new EndangeredAnimal(animalName, health, age);
            newAnimal.save();
            Sighting newSighting = new Sighting(rangerName, location, newAnimal.getId());
            newSighting.save();
            response.redirect("/");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}
