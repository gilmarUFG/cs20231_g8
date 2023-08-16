package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.controllers.MediaFileController;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaFileService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(MediaFileController.class)
@ContextConfiguration(classes = MediaFileControllerTest.TestConfig.class)
public class MediaFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IMediaFileService fileServiceMock;

    @Test
    public void createTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", getClass().getResourceAsStream("/test.png"));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/files")
                        .file("file", file.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(fileServiceMock, times(1)).create(file);
        verifyNoMoreInteractions(fileServiceMock);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public IMediaFileService mediaFileService() {
            return Mockito.mock(IMediaFileService.class);
        }
    }
}