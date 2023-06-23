package apitest;

import com.google.gson.Gson;
import entities.BookStoreEntity;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class BookStore {

    static String uri = "https://bookstore.toolsqa.com/BookStore/v1/";
    static String ct = "application/json";

    static String isbnGit;
    static String isbnJS;
    static String isbnASP;
    static String isbnSpeakingJS;

    Gson gson = new Gson();

    @Test(priority = 1)
    public void testResearchBooks(ITestContext context) {
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

    @Test(priority = 2)
    public void testResearchBook(ITestContext context) {
        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .get(uri + "Book?ISBN=" + isbnGit)
        .then()
                .log().all()
                .statusCode(200)
                .body("isbn", is(isbnGit))
                .body("title", is("Git Pocket Guide"))
        ;
    }

    @Test(priority = 3)
    public void testRegisterBooks(ITestContext context) {
        BookStoreEntity book = new BookStoreEntity();

        book.userId = (String) context.getAttribute("userId");

        book.collectionOfIsbns = new BookStoreEntity.ISBN[]{
                new BookStoreEntity.ISBN(isbnGit),
                new BookStoreEntity.ISBN(isbnJS),
                new BookStoreEntity.ISBN(isbnASP)
        };

        String jsonBody = gson.toJson(book);

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(jsonBody)
        .when()
                .post(uri + "Books")
        .then()
                .log().all()
                .statusCode(201)
                .body("books[0].isbn", is(isbnGit))
                .body("books[1].isbn", is(isbnJS))
                .body("books[2].isbn", is(isbnASP))
        ;
    }

    @Test(priority = 4)
    public void testDeleteBook(ITestContext context) {
        BookStoreEntity book = new BookStoreEntity();
        book.userId = (String) context.getAttribute("userId");
        book.isbn = isbnASP;

        String jsonBody = gson.toJson(book);

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(jsonBody)
        .when()
                .delete(uri + "Book")
        .then()
                .log().all()
                .statusCode(204)
        ;
    }

    @Test(priority = 5)
    public void testModifyBook(ITestContext context) {
        BookStoreEntity book = new BookStoreEntity();
        book.userId = (String) context.getAttribute("userId");
        book.isbn = isbnSpeakingJS;

        String jsonBody = gson.toJson(book);

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(jsonBody)
        .when()
                .put(uri + "Books/" + isbnJS)
        .then()
                .log().all()
                .statusCode(200)
                .body("userId", is(context.getAttribute("userId")))
                .body("username", is(context.getAttribute("username")))
        ;
    }

    @Test(priority = 6)
    public void testDeleteBooks(ITestContext context) {

        given()
                .contentType(ct)
                .log().all()
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .delete(uri + "Books?UserId=" + context.getAttribute("userId"))
        .then()
                .log().all()
                .statusCode(204)
        ;
    }

}
