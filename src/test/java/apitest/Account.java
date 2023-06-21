// 1 - Pacote
package apitest;

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// 2 - Bibliotecas
// 3 - Classe
public class Account {
    // 3.1 - Atributos
    String userId;
    String ct = "application/json"; // contentType da API
    String jsonBody; // guardar o json que será enviado
    String uri = "https://bookstore.toolsqa.com/Account/v1/"; // Endereço Base
    Response resposta; // guardar o retorno da API
    static String token; // guardar o token - autenticação do usuário

    // 3.1.2 Instanciar Classes Externas
    Gson gson = new Gson(); // Instancia o objeto de conversão classe para json
    AccountEntity account = new AccountEntity(); // Instancia a entidade usuario
    // 3.2 - Métodos e Funções

    // Método #1 - Criar Usuário
    @Test(priority = 1)
    public void testCreateUser(){
        // Arrange - Configura

        account.userName = "charlie190"; // entrada e saida (resultado esperado)
        account.password = "P@ss0rd1"; // entrada

        jsonBody = gson.toJson(account);  // Converte a entidade usuario no formato json

        // Act - Executa

        // Dado - Quando - Então
        // Given - When - Then
        resposta = (Response) given()      // dado
                .contentType(ct)    // tipo do conteudo
                .log().all()                        // registre tudo na ida
                .body(jsonBody)    // corpo da mensagem que será enviada
        .when() // quando
                .post(uri + "User")
        // Assert - Valida
        .then() // então
                .log().all()        // registre tudo na volta
                .statusCode(201) // valide a comunicação
                .body("username", is(account.userName)) // valida o usuario
                .extract()
        ; // fim da linha do REST-assured

        // extrair o userID (identificação do usuário)

        userId = resposta.jsonPath().getString("userID");
        System.out.println("UserID extraido: " + userId);


    } // fim do método de criação de usuário

    @Test(priority = 2)
    public void testGenerateToken(){
        // Configura
        // --> Dados de Entrada são fornecidos pela AccountEntity
        // --> Resultado Esperado é que ele receba um token

        // Executa
        resposta = (Response) given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "GenerateToken")
        .then()
                .log().all()
                .statusCode(200) // valida a comunicação
                .body("status", is("Success")) // Status = Sucesso
                .body("result", is("User authorized successfully."))
        .extract()
        ;

        // Extração do Token
        token = resposta.jsonPath().getString("token");
        System.out.println("token: " + token);

        // Valida
        Assert.assertTrue(token.length() != 0);

    } // fim do método de geração de token de identificação do usuário

    @Test(priority = 3)
    public void testAuthorized(){
        // Configura
        // Dados de Entrada
        // --> Fornecidos pele AccountEntity através do método testCreateUser - priority = 1

        // Dados de Saída / Resultado Esperado
        // StatusCode = 200
        // Response Body = true

        // Executa
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri + "Authorized")
        // Valida
        .then()
                .log().all()
                .statusCode(200)
                // .body(true) // ToDo: como validar o retorno do body apenas como true
        ;

    }

    @Test(priority = 4)
    public void testResearchUserNotAuthorized(){
        // Configura
        // Dados de Entrada
        // userId foi extraido no método testCreateUser e está na memória
        // Dados de Saída / Resultado Esperado
        // Status Code = 401, Code = 1200 e Message = User not authorized!

        // Executa
        given()                                     // Dado // Comandos do REST-assured
                .contentType(ct)                    // Formato da mensagem
                .log().all()                        // Exibir tudo que acontece na ida
        .when()                                     // Quando
                .get(uri + "User/" + userId)   // Consulta o usuário pelo userId
        // Valida
        .then()                                     // Então
                .log().all()                        // Exibir tudo que acontece na volta
                .statusCode(401)     // Valida se não está autorizado
                .body("code", is("1200")) // Valida o código de mensagem "não autorizado"
                .body("message", is("User not authorized!"))
        ;                                           // Conclui o bloco do REST-assured
    }

    @Test(priority = 5)
    public void testResearchUser(){
        // Configura
        // Dados de Entrada
        // userId foi extraido no método testCreateUser e está na memória
        // Dados de Saída / Resultado Esperado
        // userName virá da classe Account e o status code deve ser 200

        // Executa
        given()                                     // Dado // Comandos do REST-assured
                .contentType(ct)                    // Formato da mensagem
                .log().all()                        // Exibir tudo que acontece na ida
                .header("Authorization", "Bearer " + token)
        .when()                                     // Quando
                .get(uri + "User/" + userId)   // Consulta o usuário pelo userId
                // Valida
        .then()                                     // Então
                .log().all()                        // Exibir tudo que acontece na volta
                .statusCode(200)     // Valida se a conexão teve sucesso
                .body("userId", is(userId))
                .body("username", is(account.userName)) // Valida o nome do usuário
        ;                                           // Conclui o bloco do REST-assured
    }
    @Test(priority = 20)
    public void testDeleteUser(){
        // Configura
        // Dados de entrada vem do método de teste da criação do usuário (userId)
        // Resultado esperado é o código e mensagem de sucesso na exclusão do usuário

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + token)
        .when()
                .delete(uri + "User/" + userId)
        // Valida
        .then()
                .log().all()
                .statusCode(204)
        ;

    }

}
