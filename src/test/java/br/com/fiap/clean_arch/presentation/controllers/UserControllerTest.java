package br.com.fiap.clean_arch.presentation.controllers;

import br.com.fiap.clean_arch.application.usecases.*;
import br.com.fiap.clean_arch.domain.entities.EProfile;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.domain.entities.UserCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private FindUserUseCase findUserUseCase;

    @MockBean
    private UpdateUserUseCase updateUserUseCase;

    @MockBean
    private DeleteUserUseCase deleteUserUseCase;

    @Test
    void shouldGetUserById() throws Exception {
        UserCredentials credentials = UserCredentials.create(1L, "user1", "pass123", ZonedDateTime.now());
        User user = User.create(1L, "John", "123", "john@test.com", "Address", credentials, EProfile.CLIENT, ZonedDateTime.now());

        when(findUserUseCase.execute(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserCredentials credentials = UserCredentials.create(1L, "user1", "pass123", ZonedDateTime.now());
        User user = User.create(1L, "John", "123", "john@test.com", "Address", credentials, EProfile.CLIENT, ZonedDateTime.now());

        when(createUserUseCase.execute(anyString(), anyString(), anyString(), anyString(), 
            anyString(), anyString(), anyString())).thenReturn(user);

        String json = "{\"name\":\"John\",\"userIdentification\":\"123\",\"email\":\"john@test.com\",\"address\":\"Address\",\"profile\":\"client\",\"username\":\"user1\",\"password\":\"pass123\"}";

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));
    }
}
