package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage{
    // Elementos
    @FindBy(css = "div.inventory_item_name")
    WebElement lblTituloProduto;

    @FindBy(css = "div.inventory_item_price")
    WebElement lblPrecoProduto;

    @FindBy(css = "div.cart_quantity")
    WebElement lblQuantidade;

    @FindBy(css = "btn btn_secondary btn_small cart_button" )
    WebElement btnRemover;

    @FindAll(@FindBy(css = "div.inventory_item_name"))
    List<WebElement> lista;


    // Construtor
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Funções e Métodos
    public String lerTituloProdutoNoCarrinho() {
        return lblTituloProduto.getText();
    }

    public String lerPrecoProdutoNoCarrinho(){
        return lblPrecoProduto.getText();
    }

    public String lerQuantidadeDoProdutoNoCarrinho(){
        return lblQuantidade.getText();
    }

    public void clicarNoBotaoRemovernoCarrinho(){
        btnRemover.click();
    }

    public int contarProdutosNoCarrinho(){
        return lista.size();
    }
}
