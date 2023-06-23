package apitest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;

public class BookStore {

    String uri = "https://bookstore.toolsqa.com/BookStore/v1/"; // Endereço Base
    String ct = "application/json";

    Account account = new Account(); // Instancia a classe Account


    @BeforeMethod // Antes de cada @Test
    public void setUp(ITestContext context){

        account.testCreateUser();           // cria um novo usuário
        account.testGenerateToken(context); // gera um novo token
    }

    @AfterMethod // Depois de cada @Teste
    public void tearDown(){
        account.testDeleteUser(); // excluir o usuário
    }

    @Test(priority = 1)
    public void testResearchBooks(ITestContext context){
        // Configura
        // Dados de entrada não são requeridos - basta chamar o endpoint
        // Resultado esperado é apenas o status code 200 e um json com a lista de livros

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authentication", "Bearer " + context.getAttribute("token"))
        .when()
                .get(uri + "Books") // consultar a lista com todos os livros
    // Valida
        .then()
                .log().all()
                .statusCode(200)
        ;


    }

}
