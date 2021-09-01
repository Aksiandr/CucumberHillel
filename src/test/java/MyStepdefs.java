import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class MyStepdefs {

    WebDriver driver;

    @io.cucumber.java.en.Given("User opens site to order computer")
    public void userOpensSiteToOrderComputer() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/build-your-own-computer");
    }

    @io.cucumber.java.en.When("User builds his own computer")
    public void userBuildsHisOwnComputer() {
        Select processor = new Select(driver.findElement(By.cssSelector("select[id=\"product_attribute_1\"]")));
        processor.selectByValue("2");

        Select ram = new Select(driver.findElement(By.cssSelector("select[id=\"product_attribute_2\"]")));
        ram.selectByValue("5");

        driver.findElement(By.cssSelector("label[for=\"product_attribute_3_6\"]")).click();

        driver.findElement(By.cssSelector("label[for=\"product_attribute_4_8\"]")).click();

    }

    @io.cucumber.java.en.Then("User adds built computer to cart")
    public void userAddsBuiltComputerToCart() {
        driver.findElement(By.cssSelector("button#add-to-cart-button-1")).click();
    }

    @io.cucumber.java.en.Then("User navigates to Cart")
    public void userNavigatesToCart() {
        driver.findElement(By.cssSelector(".ico-cart")).click();
    }

    @io.cucumber.java.en.And("User checks if order is correct")
    public void userChecksIfOrderIsCorrect() {
        String attributes = driver.findElement(By.cssSelector("td.product .attributes")).getText();

        SoftAssertions assertionsCust = new SoftAssertions();

        assertionsCust.assertThat(attributes)
                .containsIgnoringCase("HDD: 320 GB")
                .containsIgnoringCase("RAM: 8 GB");
    }
}
