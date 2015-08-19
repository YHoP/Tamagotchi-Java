import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  private static String petName, message;
  static Tamagotchi myTamagotchi;

  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/home.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/tamagotchi", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    if(petName == null){
    // getting user input name and assign to a String
    petName = request.queryParams("petName");
    myTamagotchi = new Tamagotchi(petName);
    }

    model.put("myTamagotchi", myTamagotchi);
    // set attribute value to session
    request.session().attribute("myTamagotchi", myTamagotchi);

    String actionBtn = request.queryParams("action");

    if(actionBtn == "feed"){
      myTamagotchi.feed();
      message = "Thank you for feeding " + petName + ".\n";
    } else if (actionBtn == "play") {
      myTamagotchi.play();
      message = "Thank you for playing with " + petName + ".\n";
    } else if (actionBtn == "sleep") {
      myTamagotchi.sleep();
      message = "Thank you for putting " + petName + " to sleep.\n";
    } else {
      message = "";
    }

    model.put("template", "templates/tamagotchi.vtl");
    return new ModelAndView(model, layout);
  },  new VelocityTemplateEngine());

  get("/status", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    model.put("myTamagotchi", myTamagotchi);
    // set attribute value to session
    request.session().attribute("myTamagotchi", myTamagotchi);

    model.put("message", message);

    model.put("template", "templates/status.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());
 }
}
