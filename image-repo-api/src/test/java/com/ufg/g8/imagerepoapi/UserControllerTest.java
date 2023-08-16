package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.controllers.UserController;
import com.ufg.g8.imagerepoapi.presentation.dtos.TokenDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.UserDto;
import com.ufg.g8.imagerepoapi.presentation.services.IUserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
@AutoConfigureDataMongo
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Test
    @WithMockUser
    public void testLogin() throws Exception {
        TokenDto tokenDto = new TokenDto("token");
        Mockito.when(userService.login(Mockito.any(Authentication.class))).thenReturn(tokenDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"username\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"));
    }

    @Test
    @WithMockUser
    public void testCreate() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setLogin("username");
        userDto.setPassword("password");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\":\"username\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(userService, times(1)).create(userDto);
    }

    @Test
    @WithMockUser
    public void testRead() throws Exception {
        ObjectId id = new ObjectId();
        UserDto userDto = new UserDto();
        userDto.setLogin("username");
        userDto.setPassword("password");

        Mockito.when(userService.read(Mockito.any(ObjectId.class))).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id.toHexString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("username"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"));
    }

    @Test
    @WithMockUser
    public void testUpdate() throws Exception {
        ObjectId id = new ObjectId();
        UserDto userDto = new UserDto();
        userDto.setLogin("newUsername");
        userDto.setPassword("newPassword");

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", id.toHexString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\":\"newUsername\",\"password\":\"newPassword\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService, times(1)).update(id, userDto);
    }

    @Test
    @WithMockUser
    public void testDelete() throws Exception {
        ObjectId id = new ObjectId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id.toHexString()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(userService, times(1)).delete(id);
    }
}