package com.ufg.g8.imagerepoapi;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufg.g8.imagerepoapi.presentation.controllers.TagController;
import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Date;
import static org.mockito.Mockito.*;

@WebMvcTest(TagController.class)
public class TagControllerTest {

    @Mock
    private ITagService tagService;

    @InjectMocks
    private TagController tagController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }

    @Test
    public void createTag_ReturnsTagDtoWithHttpStatusCreated() throws Exception {
        String text = "test tag";
        TagDto expectedTagDto = new TagDto();
        expectedTagDto.setTag(text);
        expectedTagDto.setId("1");
        expectedTagDto.setTagBackground("#FFFFFF");
        expectedTagDto.setTagTextColor("#000000");
        expectedTagDto.setUpdatedAt(new Date());
        expectedTagDto.setCreatedAt(new Date());

        // Mocka o comportamento do método tagService.create()
        when(tagService.create(text)).thenReturn(expectedTagDto);

        mockMvc.perform(post("/tags/{text}", text)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(expectedTagDto)));

        // Verifica se o método tagService.create() foi chamado com os parâmetros corretos
        verify(tagService, times(1)).create(text);
        verifyNoMoreInteractions(tagService);
    }

    // Método auxiliar para converter o objeto para uma string JSON
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}