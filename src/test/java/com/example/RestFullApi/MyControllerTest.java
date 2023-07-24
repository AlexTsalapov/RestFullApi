package com.example.RestFullApi;

import com.example.RestFullApi.dto.AuthRequest;
import com.example.RestFullApi.dto.JwtResponse;
import com.example.RestFullApi.dto.ToDoDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyControllerTest {

    @LocalServerPort
    private int port;

    private String authToken;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        // Фиктивный пользователь и его учетные данные для теста
        String username = "testuser";//замените на новый
        String password = "testpassword";//замените на новый

        // Аутентификация фиктивного пользователя для получения JWT токена
        AuthRequest authRequest = new AuthRequest(username, password);

        // Выполняем POST запрос для аутентификации и получения токена
        JwtResponse response = given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post("/authenticate")
                .then()
                .statusCode(200) // Ожидаем статус 200 OK
                .extract()
                .as(JwtResponse.class);

        // Получаем JWT токен из ответа
        authToken = response.getToken();
    }
    @Test
    public void testGetAllTodos() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken) // Добавляем JWT токен в заголовок "Authorization"
                .when()
                .get("/todos/getAllToDoUser")
                .then()
                .statusCode(200); // Ожидаем статус 200 OK
    }
    @Test
    public void testCreateTodo() {
        ToDoDTO todo = new ToDoDTO();
        todo.setTitle("Название задачи");
        todo.setDescription("Описание задачи");
        todo.setCompleted(false);
        todo.setCreatedAt(LocalDateTime.parse("2023-07-21T12:34:56"));

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken) // Добавляем JWT токен в заголовок "Authorization"
                .body(todo)
                .when()
                .post("/todos/create")
                .then()
                .statusCode(200) // Ожидаем статус 200
                .body("title", equalTo("Название задачи"))
                .body("description", equalTo("Описание задачи"));

    }
    @Test
    public void testUpdateTodo() {
        ToDoDTO todo = new ToDoDTO();
        todo.setCompleted(false);
        todo.setCreatedAt(LocalDateTime.parse("2023-07-21T12:34:56"));
        todo.setTitle("Test Todo");
        todo.setDescription("Test Description");

        // Создаем задачу
         Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken) // Добавляем JWT токен в заголовок "Authorization"
                .contentType(ContentType.JSON)
                .body(todo)
                .when()
                .post("/todos/create")
                .then()
                .statusCode(200)
                .extract().response();

        // Получаем значение поля "id" из JSON-ответа
        Integer id = response.path("id");

        // Обновляем задачу
        todo.setId(Long.valueOf(id));
        todo.setTitle("Updated Todo");
        todo.setDescription("Updated Description");


        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken) // Добавляем JWT токен в заголовок "Authorization"
                .contentType(ContentType.JSON)
                .body(todo)
                .when()
                .put("/todos/update")
                .then()
                .statusCode(200) // Ожидаем статус 200 OK
                .body("title", equalTo("Updated Todo"))
                .body("description", equalTo("Updated Description"));

    }
    @Test
    public void testDeleteTodo() {
        ToDoDTO todo = new ToDoDTO();
        todo.setCompleted(false);
        todo.setCreatedAt(LocalDateTime.parse("2023-07-21T12:34:56"));
        todo.setTitle("Test Todo");
        todo.setDescription("Test Description");

        // Создаем задачу
        Response response=given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken) // Добавляем JWT токен в заголовок "Authorization"
                .contentType(ContentType.JSON)
                .body(todo)
                .when()
                .post("/todos/create")
                .then()
                .statusCode(200)
                .extract().response();

        // Получаем значение поля "id" из JSON-ответа
        Integer id = response.path("id");; // Ожидаем статус 200 OK
        // Удаляем задачу
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken) // Добавляем JWT токен в заголовок "Authorization"
                .when()
                .delete("/todos/delete/{id}", id)
                .then()
                .statusCode(200); // Ожидаем статус 200 OK

        // Проверяем, что задачи больше нет
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authToken) // Добавляем JWT токен в заголовок "Authorization"
                .when()
                .get("/todos/{id}", id)
                .then()
                .statusCode(404); // Ожидаем статус 404 Not Found
    }
    @Test
    public void testRegistration() {
        AuthRequest request = new AuthRequest();
        request.setUsername("testuser1");//замените на новый
        request.setPassword("testpassword1");//замените на новый

        // Отправляем POST-запрос на регистрацию пользователя
        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/registration")
                .then()
                .statusCode(201); // Ожидаем успешный статус 201 OK
    }

}
