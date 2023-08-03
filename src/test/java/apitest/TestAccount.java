// 1 - Pacote
package apitest;

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.ITestContext; // Interface do TestNG para compartilhar variaveis

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// 2 - Bibliotecas
// 3 - Classe
public class TestAccount {

    // 3.1- ATRIBUTOS

    // 3.1.2 INSTANCIAR CLASSES EXTERNAS

    Gson gson = new Gson(); // Instancia o objeto de conversão classe para json

    // 3.2- MÉTODOS E FUNÇÕES
    //método #1 - Criar Usuário

    @Test
    public  void testeCreaterUser(){
        //Arrange- Configura
        AccountEntity account = new AccountEntity(); //Instancia a entidade usuario
        account.userName =  "Charlie225"; // entrada e saída (resultado esperado)
        account.password =  "P@ssw0rd12"; // entrada

        String jsonBody = gson.toJson(account); // Converte a entidade  usuario no formato Json


        //Act - Executa

        //Dado - Quando - Então
        //Given - When - Then
        given ()  //dado
                .contentType("application/json") //tipo do conteúdo
                .log() .all()                   // registre tudo na ida
                .body(jsonBody)               // corpo da mensagem que será enviada
                .when()  //quando
                .post("https://bookstore.toolsqa.com/Account/v1/User")

                //Assert- Valida
                .then() // Então
                .log() .all()  // registre tudo na volta
                .statusCode (201) // valide a comunicação
                .body("username", is (account.userName))  // valida o usuário

        ;// fim da linha do REST-assured


    }  // fim do método de criação de usuário
}