package petStore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet";


    public String lerJason(String caminhoJson) throws IOException {
        return  new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 1)
    public void incluirPet() throws IOException {
        String jsonBody = lerJason("db/pet1.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Toff"))
                .body("status", is("available"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("sta"))
        ;
    }
    @Test(priority = 2)
    public void consultarPet(){
        String petId = "9874563210";
        String token =
        given()
                .contentType("application/jason")
                .log().all()
        .when()
                .get(uri + "/" + petId)
        .then()
                .statusCode(200)
                .body("name", is("Toff"))
                .body("category.name", is("dog"))
                .body("status", is("available"))
        .extract()
                .path("category.name")
        ;

        System.out.println("O Token �: " + token);
    }
}
