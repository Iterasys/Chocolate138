// 1 - Pacote
package webtest;
// 2 - Bibliotecas

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

// 3 - Classe
public class seleniumSimples {
    // 3.1 Atributos
    WebDriver driver; // objeto do Selenium WebDriver

    // 3.2 Funções e Métodos de Apoio
    // Não vamos criar

    // 3.3 Antes do Teste
    @BeforeMethod
    public void setUp(){
        // Instalar e configurar o driver do navegador/browser
        WebDriverManager.chromedriver().setup(); // download e instalação do Chrome Driver

        // Configurar as opções para o driver do navegador (a partir do Selenium 4.8.0)
        ChromeOptions options = new ChromeOptions(); // objeto de configuração para o Chrome Driver
        options.addArguments("--remote-allow-origins=*"); // permitir qualquer origem remota

        // Instanciar o Selenium como driver de um navegador especifico
        driver = new ChromeDriver(options); // Instancia o Selenium para o Chrome Driver com opções
        // Configura o tempo geral de espera de elementos em até 5 segundos
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize(); // maximiza a janela do navegador
    }

    // 3.4 Depois do Teste
    @AfterMethod
    public void tearDown(){
        driver.quit(); // destroi o objeto do Selenium WebDriver
    }

    // 3.5 Teste em Si


}
