package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.controllers.TagController;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;
import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ITagService tagServiceMock;

    @InjectMocks
    private TagController tagController;

    @BeforeEach
    public void setup() {
        // Inicialização dos mocks antes de cada teste
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTagTest() throws Exception {
        // Criação de um objeto TagDto para simular o resultado esperado do serviço
        TagDto expectedResult = new TagDto();
        expectedResult.setTag("exampleTag");

        when(tagServiceMock.create(anyString())).thenReturn(expectedResult);

        // Execução do teste para verificar se o endpoint "/tags" cria a tag corretamente
        mockMvc.perform(MockMvcRequestBuilders.post("/tags/exampleTag")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tag").value("exampleTag"))
                .andDo(print());

        // Verificação se o método tagServiceMock.create foi chamado exatamente uma vez com o texto da tag correto como argumento
        verify(tagServiceMock, times(1)).create("exampleTag");

        // Verificação de que não há mais interações com o serviço tagServiceMock após o teste
        verifyNoMoreInteractions(tagServiceMock);
    }
}