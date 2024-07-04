package com.example.testapplication;

import com.example.testapplication.config.SecurityConfig;
import com.example.testapplication.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(HelloController.class)
@Import(SecurityConfig.class)
public class HelloControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithMockUser(username="user1")
    public void testUserDefaultPage() {
        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, user1");
    }

    @Test
    @WithMockUser(username="admin", roles = {"ADMIN"})
    public void testAdminDefaultPage() {
        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, admin");
    }

    @Test
    @WithMockUser(username="admin", roles = {"ADMIN"})
    public void testAdminHelloWithAdminRole() {
        webTestClient.get().uri("/hello/{userName}", "admin")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("This is your personal page, admin");
    }

    @Test
    @WithMockUser(username="user3", roles = {"BACKEND"})
    public void testBackendForbidden() {
        webTestClient.get().uri("/hello/{userName}", "admin")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @WithMockUser(username="user1", roles = {"FRONTEND"})
    public void testFrontendForbidden() {
        webTestClient.get().uri("/hello/{userName}", "admin")
                .exchange()
                .expectStatus().isForbidden();
    }
}
