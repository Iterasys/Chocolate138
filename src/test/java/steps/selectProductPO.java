package steps;


import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import pages.BasePage;
import pages.CartPage;
import pages.HomePage;
import pages.InventoryItemPage;
import pages.InventoryPage;

public class selectProductPO {
    // Atributos
    final WebDriver driver; // este é objeto final do Selenium (Uma única bola em campo)

    // Definindo objetos para receber os mapeamentos de páginas já importados
    private CartPage cartPage;
    private HomePage homePage;
    private InventoryItemPage inventoryItemPage;
    private InventoryPage inventoryPage;

    private static BasePage basePage;

    // Construtor
    public selectProductPO(BasePage basePage){
        this.driver = basePage.driver;  // passagem de bola = integração Selenium dentro e fora
    }

    @BeforeAll // Executa antes de todos os blocos de passos --> usar do Cucumber
    public void before_all(){
        ChromeOptions options = new ChromeOptions();      // instancia o objeto de Opções do ChromeDriver
        options.addArguments("--remote-allow-origins=*"); // Permite qualquer origem remota
        WebDriverManager.chromedriver().setup();          // baixa a versão mais atual do ChromeDriver

        basePage = new BasePage(this.driver);               // Instancia Base com o Driver
        homePage = new HomePage(this.driver);               // Instancia a HomePage

        basePage.driver = new ChromeDriver(options);               // instancia o objeto do Selenium como ChromeDriver

        // Estabelece uma espera implicita de 5 segundos para carregar qualquer elemento
        basePage.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        basePage.driver.manage().window().maximize();              // Maximiza a janela do navegador
    }

    @AfterAll // Executa após todos os blocos de passos --> usar do Cucumber
    public static void after_all() throws InterruptedException {
        basePage.driver.quit();                                    // Encerrar o objeto do Selenium WebDriver
    }

    @Given("I access SauceDemo store PO")
    public void i_access_sauce_demo_store() {
        driver.get("https://www.saucedemo.com");
    }

    @When("I filled a user {string} and password {string} PO")
    public void i_filled_a_user_and_password(String user, String password) {
        homePage.logar(user, password);
    }
    @And("I click in Login PO")
    public void i_click_in_login() {
        // ToDo: Remover a linha da feature ou separar a ação do clique
    }
    // @Then("show page's title {string}")
    @Then("I verify the page's title {string} PO")
    public void show_page_s_title(String pageTitle) {
        // Verifica se o titulo da página coincide com o informado na feature
        assertEquals(driver.findElement(By.cssSelector("span.title")).getText(), pageTitle);
    }
    @And("show cart's link PO")
    public void show_cart_s_link() {
        // Verifica se o elemento do carrinho de compras está visível
        assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());
    }
    @When("I click in product {string} PO")
    public void i_click_in_product(String productId) {
        // Clica no elemento correspondente ao código do produto informado na feature
        driver.findElement(By.id("item_" + productId + "_title_link")).click();
    }
    @Then("I verify the product title {string} PO")
    public void i_verify_the_product_title(String productTitle) {
        // Verifica se o titulo do produto corresponde ao informado na feature
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_name.large_size")).getText(),
                productTitle);
    }
    @And("I verify the product price {string} PO")
    public void i_verify_the_product_price(String productPrice) {
        // Verifica se o preço do produto corresponde ao informado na feature
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_price")).getText(), productPrice);
    }
    @And("I click in Add to Cart PO")
    public void i_click_in_add_to_cart() {
        // Clica no botão de adicionar no carrinho
        // driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory")).click();
    }
    @And("I click in Cart icon PO")
    public void i_click_in_cart_icon() {
        // Clica no icone do carrinho de compras
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    @And("I verify the quantity is {string} PO")
    public void i_verify_the_quantity_is(String quantity) {
        // Verifica se a quantidade corresponde a feature
        assertEquals(driver.findElement(By.cssSelector("div.cart_quantity")).getText(), quantity);
    }

    @Then("I verify the product title {string} in cart PO")
    public void i_verify_the_product_title_in_cart(String productTitle) throws InterruptedException {

        List<WebElement> lista = driver.findElements(By.cssSelector("div.inventory_item_name"));

        for (int i = 1; i < lista.size(); i++) {
            driver.findElement(By.cssSelector("button.btn.btn_secondary.btn_small.cart_button")).click();
        }

        assertEquals(driver.findElement(By.cssSelector("div.inventory_item_name")).getText(),
                productTitle);
        Thread.sleep(2000);
    }

    @Then("I verify the product price {string} in cart PO")
    public void i_verify_the_product_price_in_cart(String productPrice) {
        assertEquals(driver.findElement(By.cssSelector("div.inventory_item_price")).getText(), productPrice);
    }

}
