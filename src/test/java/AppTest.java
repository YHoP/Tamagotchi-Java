// import org.fluentlenium.adapter.FluentTest;
// import org.junit.ClassRule;
// import org.junit.Test;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//
// import static org.fluentlenium.core.filter.FilterConstructor.*;
// import java.util.ArrayList;
//
// import static org.assertj.core.api.Assertions.assertThat;
//
// public class AppTest extends FluentTest {
//   public WebDriver webDriver = new HtmlUnitDriver();
//
//   @Override
//   public WebDriver getDefaultDriver() {
//       return webDriver;
//   }
//
//   @ClassRule
//   public static ServerRule server = new ServerRule();
//
//   @Test
//   public void rootTest() {
//     goTo("http://localhost:4567/");
//     assertThat(pageSource()).contains("Places");
//   }
//
//   @Test
//     public void placeIsCreatedandSaved() {
//       goTo("http://localhost:4567/");
//       fill("#description").with("Portland");
//       submit(".btn");
//       assertThat(pageSource()).contains("saved");
//   }
//
//   @Test
//   public void placeIsDisplayedPlace() {
//     goTo("http://localhost:4567/");
//     fill("#description").with("Spokane");
//     submit(".btn");
//     click("a", withText("Go Back"));
//     assertThat(pageSource()).contains("Spokane");
//   }
//
//   @Test
//   public void multiplePlacesAreDisplayedPlacesList() {
//     goTo("http://localhost:4567/");
//     fill("#description").with("Spokane");
//     submit(".btn");
//     click("a", withText("Go Back"));
//     fill("#description").with("Portland");
//     submit(".btn");
//     click("a", withText("Go Back"));
//     assertThat(pageSource()).contains("Spokane");
//     assertThat(pageSource()).contains("Portland");
//   }
// }
