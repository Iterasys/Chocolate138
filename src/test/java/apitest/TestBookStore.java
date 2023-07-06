package apitest;

import com.google.gson.Gson;
import entities.LoanEntity;
import org.testng.annotations.*;
import org.testng.ITestContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestBookStore {

    String uri = "https://bookstore.toolsqa.com/BookStore/v1/"; // Endereço Base
    String ct = "application/json";

    TestAccount account = new TestAccount(); // Instancia a classe Account
    LoanEntity isbn = new LoanEntity(); // Instancia para a lista de livros
    Gson gson = new Gson();

    @BeforeClass // Antes da Classe e de todos os testes dentro da classe
    // @BeforeMethod // Antes de cada @Test
    public void setUp(ITestContext context){

        account.testCreateUser(context);    // cria um novo usuário
        account.testGenerateToken(context); // gera um novo token
    }

    @AfterClass // Depois da classe
    //@AfterMethod // Depois de cada @Teste
    public void tearDown() throws InterruptedException {
        //account.testResearchUser();
        Thread.sleep(3000);
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

    @Test(priority = 2)
    public void testLoanBooks(ITestContext context){ // Emprestar Livros
        // Configura
        // Dados de Entrada
        // userId virá pelo context
        isbn.userId = context.getAttribute("userID").toString(); // código do usuário
        isbn.collectionOfIsbns = new LoanEntity.ISBN[] {
                new LoanEntity.ISBN("9781449325862")
        };
        // isbn.isbn = "9781449325862"; // código do livro

        // Dados de Saida
        // statusCode = 201
        // Retorne o isbn do livro emprestado (echo)

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(isbn))
        .when()
                .post(uri + "Books")
        // Valida
        .then()
                .log().all()
                .statusCode(201)
                .body("isbn", is(isbn.isbn))
        ;

    }
    @Test(priority = 3)
    public void testUpdateLoan(ITestContext context){ // Atualizar quem está com qual livro
        // Configura
        // Dados de Entrada
        // userId é extraido no BeforeMethod
        String isbnAntigo = "9781449325862"; // Livro a substituir
        String isbnNovo = "9781449331818"; // novo livro a ser emprestado

        // alimentar a classe LoanEntity apenas com o código do usuário e o isbn
        isbn = new LoanEntity(); // reiniciando o objeto da classe LoanEntity
        isbn.userId = context.getAttribute("userID").toString(); // código do usuário
        isbn.isbn = isbnNovo; // código do livro que estava com o usuário


        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(isbn))
        .when()
                .put(uri + "Books/" + isbnAntigo)
        // Valida
        .then()
                .log().all()
                .statusCode(200)
                .body("books[0].isbn",is(isbnNovo))
        ;
    }
    @Test(priority = 4)
    public void testDeleteLoans(ITestContext context){
        // Configura
        // userId vem do BeforeClass
        // statusCode = 204

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .delete(uri + "Books?UserId=" + context.getAttribute("userID").toString())
        // Valida
        .then()
                .log().all()
                .statusCode(204)
        ;
    }

}
