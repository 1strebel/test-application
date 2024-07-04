package com.example.testapplication;

import com.example.testapplication.config.SecurityConfig;
import com.example.testapplication.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(AdminController.class)
@Import(SecurityConfig.class)
public class AdminControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testFrontendByAdminOk() {
        webTestClient.get().uri("/fe")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("You are frontend developer, admin");
    }

    @Test
    @WithMockUser(username = "user1", roles = {"FRONTEND"})
    public void testFrontendByFrontendOk() {
        webTestClient.get().uri("/fe")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("You are frontend developer, user1");
    }

    @Test
    @WithMockUser(username = "user3", roles = {"BACKEND"})
    public void testFrontendByBackendForbidden() {
        webTestClient.get().uri("/fe")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testBackendByAdminOk() {
        webTestClient.get().uri("/be")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("You are backend developer, admin");
    }

    @Test
    @WithMockUser(username = "user3", roles = {"BACKEND"})
    public void testBackendByBackendOk() {
        webTestClient.get().uri("/be")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("You are backend developer, user3");
    }

    @Test
    @WithMockUser(username = "user1", roles = {"FRONTEND"})
    public void testBackendByFrontendForbidden() {
        webTestClient.get().uri("/be")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @WithMockUser(username = "user2", roles = {"FRONTEND", "BACKEND"})
    public void testBackendAndFrontendUser() {
        webTestClient.get().uri("/be")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("You are backend developer, user2");

        webTestClient.get().uri("/fe")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("You are frontend developer, user2");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdmin() {
        webTestClient.get().uri("/admin")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("You are admin, admin");
    }

    @Test
    @WithMockUser(username = "user2", roles = {"FRONTEND", "BACKEND"})
    public void testAdminForbidden() {
        webTestClient.get().uri("/admin")
                .exchange()
                .expectStatus().isForbidden();
    }
}
