package steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class selectProduct {
    // Atributos
    WebDriver driver;

    @BeforeAll // Executa antes de todos os blocos de passos --> usar do Cucumber
    public void setUp(){
        ChromeOptions options = new ChromeOptions();      // instancia o objeto de Opções do ChromeDriver
        options.addArguments("--remote-allow-origins=*"); // Permite qualquer origem remota
        WebDriverManager.chromedriver().setup();          // baixar a versão mais atual do ChromeDriver
        driver = new ChromeDriver(options);               // instancia o objeto do Selenium como ChromeDriver

        // Estabelece uma espera implicita de 5 segundos para carregar qualquer elemento
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize();              // Maximiza a janela do navegador
    }

    @AfterAll // Executa após todos os blocos de passos --> usar do Cucumber
    public void tearDown(){
        driver.quit();                                    // Encerrar o objeto do Selenium WebDriver
    }



    @Given("I access SauceDemo store")
    public void i_access_sauce_demo_store() {

    }
    @When("I filled a user {string} and password {string}")
    public void i_filled_a_user_and_password(String string, String string2) {

    }
    @And("I click in Login")
    public void i_click_in_login() {

    }
    @Then("show page's title {string}")
    public void show_page_s_title(String string) {

    }
    @And("show cart's link")
    public void show_cart_s_link() {

    }
    @When("I click in product {string}")
    public void i_click_in_product(String string) {

    }
    @Then("I verify the product title {string}")
    public void i_verify_the_product_title(String string) {

    }
    @And("I verify the product price {string}")
    public void i_verify_the_product_price(String string) {

    }
    @And("I click in Add to Cart")
    public void i_click_in_add_to_cart() {

    }
    @And("I click in Cart icon")
    public void i_click_in_cart_icon() {

    }
    @Then("I verify the page's title {string}")
    public void i_verify_the_page_s_title(String string) {

    }
    @And("I verify the quantity is {string}")
    public void i_verify_the_quantity_is(String string) {

    }
}
