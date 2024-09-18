package com.example.tests;
//Aqui fica as importações do pom.xml

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Inicio do teste
public class UserApiTest {

    // Define a URL base do endpoint
    private static final String BASE_URL = "https://dummyjson.com/users";

    @Test
    public void testGetUsersDataValidation() {
        // Configura a base URI para o Rest Assured
        RestAssured.baseURI = BASE_URL;

        // Envia a requisição GET para o endpoint
        Response response =
                given()
                        .when()
                        .get()
                        .then()
                        .statusCode(200) // Verifica se o status code é 200
                        .body("users.size()", lessThanOrEqualTo(30)) // Verifica se há no máximo 30 usuários por página
                        .body("users.id", everyItem(notNullValue())) // Verifica se o campo id não é nulo
                        .body("users.firstName", everyItem(notNullValue())) // Verifica se o campo firstName não é nulo
                        .body("users.lastName", everyItem(notNullValue())) // Verifica se o campo lastName não é nulo
                        .body("users.age", everyItem(notNullValue())) // Verifica se o campo age não é nulo
                        .body("users.gender", everyItem(notNullValue())) // Verifica se o campo gender não é nulo
                        .body("users.email", everyItem(notNullValue())) // Verifica se o campo email não é nulo
                        .body("users.username", everyItem(notNullValue())) // Verifica se o campo username não é nulo
                        .body("users.birthDate", everyItem(notNullValue())) // Verifica se o campo birthDate não é nulo
                        .body("users.role", everyItem(notNullValue())) // Verifica se o campo role não é nulo
                        .extract()
                        .response();

        // Verificação do status
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        assertEquals(statusCode, 200, "Status code should be 200");

        // Verificação da paginação
        int numberOfUsers = response.jsonPath().getList("users").size();
        System.out.println("Número de usuários retornados: " + numberOfUsers);

        // Verifica se o número de usuários retornados é menor ou igual a 30
        assertThat(numberOfUsers, lessThanOrEqualTo(30));

        // Opcional: Exibe a resposta completa para inspeção mas nao sei se gosto
        System.out.println(response.asPrettyString());
    }
}


//Quando reinincio o intelj o comando do teste nao fica habilitado e preciso adicionar manualmente
// aviso INFO : Esse arquivo é gerado automaticamente pelo IntelliJ IDEA para gerenciar a execução dos testes.
//Try Catch :Tratamento de exeções para caso ocorra um erro a execução nao seja interrompido (o site roda sem quebrar)
