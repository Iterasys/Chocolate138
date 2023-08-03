package stepsPO;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Base;

import java.time.Duration;

public class Hooks {
    Base base;

    public Hooks (Base base) {
        this.base = base;
    }

    @Before // Executa antes de todos os blocos de passos --> usar do Cucumber
    public void before_all(){
        ChromeOptions options = new ChromeOptions();      // instancia o objeto de Opções do ChromeDriver
        options.addArguments("--remote-allow-origins=*"); // Permite qualquer origem remota
        WebDriverManager.chromedriver().setup();          // baixa a versão mais atual do ChromeDriver

        base.driver = new ChromeDriver(options);               // instancia o objeto do Selenium como ChromeDriver

        // Estabelece uma espera implicita de 5 segundos para carregar qualquer elemento
        base.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        base.driver.manage().window().maximize();              // Maximiza a janela do navegador
    }

    @After // Executa após todos os blocos de passos --> usar do Cucumber
    public void after_all()  {
        base.driver.quit();                                    // Encerrar o objeto do Selenium WebDriver
    }


}
