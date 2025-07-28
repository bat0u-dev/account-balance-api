package com.example.account.controller;

import com.example.account.dto.CreateBalanceRequest;
import com.example.account.dto.CreateTransactionRequest;
import com.example.account.entity.Currency;
import com.example.account.entity.TransactionType;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class BalanceControllerIT {

    @LocalServerPort
    int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void createBalance_and_GetBalance() {
        // Create balance
        given()
            .contentType(ContentType.JSON)
            .body(new CreateBalanceRequest("testapi"))
        .when()
            .post("/balances")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo("testapi"));

        // Get balance
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/balances/testapi")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("total", equalTo(0));
    }

    @Test
    void deposit_and_listTransactions() {
        String name = "testapi2";

        // Create balance
        given()
            .contentType(ContentType.JSON)
            .body(new CreateBalanceRequest(name))
        .when()
            .post("/balances")
        .then()
            .statusCode(HttpStatus.OK.value());

        // Add deposit
        given()
            .contentType(ContentType.JSON)
            .body(new CreateTransactionRequest(TransactionType.DEPOSIT, BigDecimal.valueOf(100), Currency.EUR, "test-ext-id-100"))
        .when()
            .post("/balances/" + name + "/transactions")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("type", equalTo("DEPOSIT"));

        // List transactions
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/balances/" + name + "/transactions")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("size()", greaterThan(0));
    }
}
