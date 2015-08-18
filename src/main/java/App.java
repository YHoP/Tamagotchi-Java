import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  model.put("placesList", request.session().attribute("placesList"));

  model.put("template", "templates/index.vtl");
  return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/places", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    ArrayList<Places> placesList = request.session().attribute("placesList");

    if (placesList == null) {
      placesList = new ArrayList<Places>();
      request.session().attribute("placesList", placesList);
    }

    // getting user input info and assign to a String
    String description = request.queryParams("description");

    // pass the string value into our method
    Places myPlaces = new Places(description);

    //adding the value of each "description" into the array
    placesList.add(myPlaces);

    model.put("template", "templates/success.vtl");
    return new ModelAndView(model, layout);
  },  new VelocityTemplateEngine());
 }
}
