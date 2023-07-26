package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // função para retornar o titulo escrito na guia do browser
    public String lerTituloAba(){
        return driver.getTitle();
    }
}
