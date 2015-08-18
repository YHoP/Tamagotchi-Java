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
    model.put("template", "templates/home.vtl");
    model.put("petName", request.session().attribute("petName"));

    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/tamagotchi", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/tamagotchi.vtl");
    model.put("petName", request.session().attribute("petName"));

    // getting user input name and assign to a String
    String petName = request.queryParams("petName");

    // pass the pet name into our method
    Tamagotchi myTamagotchi = new Tamagotchi(petName);

    String actionBtn = request.queryParams("action");
    String message = "";

    if(actionBtn == "Feed"){
      myTamagotchi.feed();
      message = "Thank you for feeding " + petName + ".\n";
    } else if (actionBtn == "Play") {
      myTamagotchi.play();
      message = "Thank you for playing with " + petName + ".\n";
    } else if (actionBtn == "Sleep") {
      myTamagotchi.sleep();
      message = "Thank you for putting " + petName + " to sleep.\n";
    } else {
      message = "Your code is broken! + \n";
    }
    model.put("message", message);

    return new ModelAndView(model, layout);
  },  new VelocityTemplateEngine());
 }
}
