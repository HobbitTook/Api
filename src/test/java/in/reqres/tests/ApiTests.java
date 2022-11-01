package in.reqres.tests;


import in.reqres.models.AuthorizeRequestModelLombok;
import in.reqres.models.AuthorizeResponseModelLombok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static in.reqres.specs.AuthorizeSpec.*;
import static in.reqres.specs.RegistrationSpec.*;
import static in.reqres.specs.UsersSpec.successGetUserListResponse;
import static in.reqres.specs.UsersSpec.successgetUserListRequest;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


public class ApiTests {

    @Test
    @DisplayName("Регистрация")
    public void userRegistrationTest() {
        AuthorizeRequestModelLombok body = new AuthorizeRequestModelLombok();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");
        AuthorizeResponseModelLombok response = given()
                .spec(successRequestRegistration)
                .body(body)
                .when()
                .post()
                .then()
                .spec(successResponseRegistration)
                .extract()
                .as(AuthorizeResponseModelLombok.class);


    }

    @Test
    @DisplayName("Неуспешная регистрация")
    public void failedRegistration() {
        AuthorizeRequestModelLombok body = new AuthorizeRequestModelLombok();
        body.setEmail("eve.holt@reqres.in");
        given()
                .spec(failedRequestRegistration)
                .body(body)
                .when()
                .post()
                .then()
                .spec(failedResponseRegistration)
                .extract();
    }

    @Test
    @DisplayName("Успешная авторизация")
    void successAuthorization() {
        AuthorizeRequestModelLombok body = new AuthorizeRequestModelLombok();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");
        AuthorizeResponseModelLombok response = given()
                .spec(successRequestAuthorization)
                .body(body)
                .when()
                .post()
                .then()
                .spec(successResponseAuthorization)
                .extract()
                .as(AuthorizeResponseModelLombok.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Неуспешная авторизация")
    public void failedAuthorization() {
        AuthorizeRequestModelLombok body = new AuthorizeRequestModelLombok();
        body.setEmail("eve.holt@reqres.in");
        given()
                .spec(failedRequestAuthorization)
                .body(body)
                .when()
                .post()
                .then()
                .spec(failedResponseAuthorization)
                .extract();
    }

    @Test
    @DisplayName("Получение списка пользователей")
    public void getUsersList() {
        given()
                .spec(successgetUserListRequest)
                .when()
                .get()
                .then()
                .spec(successGetUserListResponse)
                .extract();
    }
}
