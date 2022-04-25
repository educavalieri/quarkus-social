package com.educavalieri.rest;

import com.educavalieri.dtos.CreateUserDto;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class UserResourceTest {

    @TestHTTPResource("/users")
    URL apiURL;

//    @BeforeEach
//    public void setUP(){
//        var userDto = new CreateUserDto();
//        userDto.setName("Fulano");
//        userDto.setAge(20);
//        userService.save(userDto);
//    }


    @Test
    @DisplayName("Shoud return new user")
    public void createUser() {

        CreateUserDto userDto = new CreateUserDto("Fulano", 20);

        var response =
        given()
                .contentType(ContentType.JSON)
                .body(userDto)
                .when()
                .post(apiURL)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    @DisplayName("should list all users")
    public void listAllUsersTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .statusCode(200)
                .body("size()", Matchers.is(0));
    }

    @Test
    @DisplayName("should return error when json is not valid")
    public void createUserValidationErrorTest() {
        CreateUserDto userDto = new CreateUserDto(null, null);

        given()
                .contentType(ContentType.JSON)
                .body(userDto)
                .when()
                .post(apiURL)
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("should return Null Pointer Exception when User Null")
    public void createNullUserValidationErrorTest() {
        CreateUserDto userDto = new CreateUserDto(null, null);

        var response =
        given()
                .contentType(ContentType.JSON)
                .body(userDto)
                .when()
                .post(apiURL)
                .then()
                .extract()
                .response();


        assertEquals(response.statusCode(), 500);

//        assertEquals("Validation Error", response.jsonPath().getString("message"));
//
//        List<Map<String, String>> errors = response.jsonPath().getList("errors");
//        assertNotNull(errors.get(0).get("message"));
//        assertNotNull(errors.get(1).get("message"));
    }
}