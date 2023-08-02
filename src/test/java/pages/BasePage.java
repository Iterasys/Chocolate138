package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

    @FindBy(id = "shopping_cart_container")
    public WebElement imgCarrinho;

    @FindBy(css = "span.title")
    WebElement lblTituloPagina;

    @FindBy(css = "button.btn btn_primary btn_small btn_inventory")
    WebElement btnAdicionarNoCarrinho;

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // função para retornar o titulo escrito na guia do browser
    public String lerTituloAba(){
        return driver.getTitle();
    }

    public void clicarNoCarrinho(){
        imgCarrinho.click();    // Clica na imagem do carrinho
    }

    public String lerTituloDaPagina(){
        return lblTituloPagina.getText(); // retorna o que estiver escrito no elemento
    }

    // Esta função é apenas um exemplo, ela não vai ser usado no exercício
    public String lerTextoDoBotaoAdicionarRemoverDoCarrinho(){
        return btnAdicionarNoCarrinho.getText();
    }

    public void clicarNoBotaoAdicionarOuRemoverNoCarrinho(){
        btnAdicionarNoCarrinho.click();
    }
}
