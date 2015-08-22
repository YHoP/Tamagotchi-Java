import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  private static String petName, message;
  private static Tamagotchi myTamagotchi = new Tamagotchi(petName);

  public static void main(String[] args) {
  staticFileLocation("/public");
  String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    petName = request.queryParams("petName");
    myTamagotchi = new Tamagotchi(petName);
    request.session().attribute("petName", petName);
    request.session().attribute("myTamagotchi", myTamagotchi);

    model.put("myTamagotchi", myTamagotchi);
    model.put("template", "templates/home.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("/tamagotchi", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    if (petName == null){
      petName = request.queryParams("petName");
      myTamagotchi = new Tamagotchi(petName);
      request.session().attribute("petName", petName);
      request.session().attribute("myTamagotchi", myTamagotchi);
    }

    String actionBtn = request.queryParams("action");
    if("feed".equals(actionBtn)){
      myTamagotchi.feed();
      message = "Thank you for feeding " + petName + ".\n";
    } else if ("play".equals(actionBtn)) {
      myTamagotchi.play();
      message = "Thank you for playing with " + petName + ".\n";
    } else if ("sleep".equals(actionBtn)) {
      myTamagotchi.sleep();
      message = "Thank you for putting " + petName + " to sleep.\n";
    } else {
      message = petName + " needs your attention!";
    }

    model.put("petName", petName);
    model.put("message", message);
    model.put("myTamagotchi", myTamagotchi);
    model.put("template", "templates/tamagotchi.vtl");
    return new ModelAndView(model, layout);
  },  new VelocityTemplateEngine());

 }
}
