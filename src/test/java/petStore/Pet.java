package petStore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Pet {
    String uri = "petstore.swagger.io/v2/pet";


    public String lerJason(String caminhoJson) throws IOException {
        return  new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
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
        ;
    }

}
