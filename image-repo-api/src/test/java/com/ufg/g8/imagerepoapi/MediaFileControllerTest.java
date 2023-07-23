package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.controllers.MediaFileController;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaFileService;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaFileDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileInputStream;
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
        // Inicialização dos mocks antes de cada teste
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTest() throws Exception {
        // Criação de um objeto MultipartFile para simular o arquivo enviado na requisição
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", new FileInputStream("filepath"));

        // Criação de um objeto MediaFileDto para simular o resultado esperado do serviço
        MediaFileDto expectedResult = new MediaFileDto();
        when(fileServiceMock.create(file)).thenReturn(expectedResult);

        // Criação do objeto MockMultipartFile para representar o arquivo enviado na requisição
        MockMultipartFile mockFile = new MockMultipartFile(
            file.getName(),
            file.getOriginalFilename(),
            file.getContentType(),
            file.getInputStream()
        );

        // Execução do teste para verificar se o endpoint "/files" cria o arquivo corretamente
        mockMvc.perform(MockMvcRequestBuilders.multipart("/files")
                .file(mockFile))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(expectedResult.toString()));

        // Verificação se o método fileServiceMock.create foi chamado exatamente uma vez com o arquivo correto como argumento
        verify(fileServiceMock, times(1)).create(file);

        // Verificação de que não há mais interações com o serviço fileServiceMock após o teste
        verifyNoMoreInteractions(fileServiceMock);
    }
}