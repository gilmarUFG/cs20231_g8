package com.ufg.g8.imagerepoapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufg.g8.imagerepoapi.presentation.controllers.TagController;
import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest
public class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITagService tagService;

    @InjectMocks
    private TagController tagController;

@Test
public void createTag_ReturnsTagDtoWithHttpStatusCreated() throws Exception {
    String text = "test tag";
    TagDto expectedTagDto = new TagDto();
    expectedTagDto.setTag(text);
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/tags/{text}", text))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();
    String responseContent = mvcResult.getResponse().getContentAsString();
    ObjectMapper objectMapper = new ObjectMapper();
    TagDto actualTagDto = objectMapper.readValue(responseContent, TagDto.class);
    assertEquals(expectedTagDto, actualTagDto);
}

}
