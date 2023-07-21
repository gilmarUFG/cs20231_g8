package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.controllers.MediaFileController;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaFileService;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaFileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired; // Add this import
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream; // Add this import

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MediaFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IMediaFileService fileServiceMock;

    @InjectMocks
    private MediaFileController mediaFileController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTest() throws Exception {
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", new FileInputStream("filepath"));

        MediaFileDto expectedResult = new MediaFileDto();
        when(fileServiceMock.create(file)).thenReturn(expectedResult);

        MockMultipartFile mockFile = new MockMultipartFile(
            file.getName(),
            file.getOriginalFilename(),
            file.getContentType(),
            file.getInputStream()
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/files")
                .file(mockFile))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expectedResult.toString()));

        verify(fileServiceMock, times(1)).create(file);
        verifyNoMoreInteractions(fileServiceMock);
    }
}