package apitest;

import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookStore {

    static String uri = "https://bookstore.toolsqa.com/BookStore/v1/";
    static String ct = "application/json";

    static String isbnGit;
    static String isbnJS;
    static String isbnASP;
    static String isbnSpeakingJS;

    @Test
    public void researchBooks(ITestContext context) {
        Response resp = (Response) given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .get(uri + "Books")
        .then()
                .log().all()
                .statusCode(200)
        .extract();

        isbnGit = resp.jsonPath().getString("books[0].isbn");
        isbnJS = resp.jsonPath().getString("books[1].isbn");
        isbnASP = resp.jsonPath().getString("books[2].isbn");
        isbnSpeakingJS = resp.jsonPath().getString("books[3].isbn");
    }

}
