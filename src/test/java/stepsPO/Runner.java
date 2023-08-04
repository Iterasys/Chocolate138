package stepsPO;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(features = {"src/test/resources/features/selectProductPO.feature"}, // Onde estão os cenarios em Gherkin
        glue = {"stepsPO"},                       // Onde estão as definições de passos
        dryRun = false,                               // Exibição de log
        monochrome = true                              // Detalhes do log)
)
@Test
public class Runner extends AbstractTestNGCucumberTests {

}
