package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.controllers.UserController;
import com.ufg.g8.imagerepoapi.presentation.dtos.TokenDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.UserDto;
import com.ufg.g8.imagerepoapi.presentation.services.IUserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IUserService userService;

    @Test
    @WithMockUser
    public void testLogin() throws Exception {
        // Criar um objeto TokenDto simulando o token retornado pelo serviço
        TokenDto tokenDto = new TokenDto("token");

        // Simular o comportamento do método login() do serviço userService
        Mockito.when(userService.login(Mockito.any(Authentication.class))).thenReturn(tokenDto);

        // Executar o teste para verificar se o endpoint "/users/login" retorna o token corretamente
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"username\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"));
    }

    @Test
    @WithMockUser
    public void testCreate() throws Exception {
        // Criar um objeto UserDto para simular o usuário enviado na requisição
        UserDto userDto = new UserDto();
        userDto.setLogin("username");
        userDto.setPassword("password");

        // Executar o teste para verificar se o endpoint "/users" cria o usuário corretamente
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"login\":\"username\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // Verificar se o método create() do serviço userService foi chamado exatamente uma vez com o UserDto correto como argumento
        Mockito.verify(userService, Mockito.times(1)).create(userDto);
    }

    @Test
    @WithMockUser
    public void testRead() throws Exception {
        // Criar um ObjectId simulando o ID do usuário solicitado
        ObjectId id = new ObjectId();
        // Criar um objeto UserDto simulando o usuário retornado pelo serviço
        UserDto userDto = new UserDto();
        userDto.setLogin("username");
        userDto.setPassword("password");

        // Simular o comportamento do método read() do serviço userService
        Mockito.when(userService.read(Mockito.any(ObjectId.class))).thenReturn(userDto);

        // Executar o teste para verificar se o endpoint "/users/{id}" retorna o usuário correto
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id.toHexString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("username"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"));
    }

    @Test
    @WithMockUser
    public void testUpdate() throws Exception {
        // Criar um ObjectId simulando o ID do usuário a ser atualizado
        ObjectId id = new ObjectId();
        // Criar um objeto UserDto simulando os dados atualizados do usuário
        UserDto userDto = new UserDto();
        userDto.setLogin("newUsername");
        userDto.setPassword("newPassword");

        // Executar o teste para verificar se o endpoint "/users/{id}" atualiza o usuário corretamente
        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", id.toHexString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"login\":\"newUsername\",\"password\":\"newPassword\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verificar se o método update() do serviço userService foi chamado exatamente uma vez com o ObjectId e o UserDto corretos como argumentos
        Mockito.verify(userService, Mockito.times(1)).update(id, userDto);
    }

    @Test
    @WithMockUser
    public void testDelete() throws Exception {
        // Criar um ObjectId simulando o ID do usuário a ser deletado
        ObjectId id = new ObjectId();

        // Executar o teste para verificar se o endpoint "/users/{id}" deleta o usuário corretamente
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id.toHexString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verificar se o método delete() do serviço userService foi chamado exatamente uma vez com o ObjectId correto como argumento
        Mockito.verify(userService, Mockito.times(1)).delete(id);
    }
}