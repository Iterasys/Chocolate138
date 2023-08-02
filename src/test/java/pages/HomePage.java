package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    // O mapeamento cria ou reflete uma DSL
    // Domain Structured Language
    // Linguagem Estruturada de Dominio
    // "Como falamos aqui na empresa"

    // Elementos Web / Web Elements --> Os que serão mapeados
    @FindBy(id = "user-name") // mapeia o elemento pelo seletor
    WebElement txtUsuario;

    @FindBy(id = "password")
    WebElement txtSenha;

    @FindBy(id = "login-button")
    WebElement btnLogin;

    // Construtor / Constructor

    public HomePage(WebDriver driver) {
        super(driver);        // instancia a classe mãe como superclasse
        PageFactory.initElements(driver, this); // conecta o objeto interno e o externo do Selenium
    }

    // Funções e Métodos
    public void logar(String user, String password){
        txtUsuario.sendKeys(user);
        txtSenha.sendKeys(password);

    }

    public void clicarNoBotaoLogin(){
        btnLogin.click();
    }

}
