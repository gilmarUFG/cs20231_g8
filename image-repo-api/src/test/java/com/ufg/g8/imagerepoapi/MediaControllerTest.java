package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MediaControllerTest {

    @Mock
    private IMediaService mediaService;

    @InjectMocks
    private MediaControllerTest mediaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Inicialização do MockMvc para realizar os testes
        mockMvc = MockMvcBuilders.standaloneSetup(mediaController).build();
    }

    @Test
    public void testCreateMedia() throws Exception {
        // Criação de um objeto MediaDto para simular os dados da imagem enviados na requisição
        MediaDto mediaDto = new MediaDto();
        mediaDto.setName("Sample Image");
        mediaDto.setDescription("A sample image");
        mediaDto.setViews(0);
        mediaDto.setDownloads(0);
        mediaDto.setAuthorName("Test User");
        mediaDto.setAuthorId(new ObjectId());
        mediaDto.setTagsId(Collections.emptyList());
        mediaDto.setTags(Collections.emptyList());
        mediaDto.setFile(null);
        mediaDto.setFileId(new ObjectId());
        mediaDto.setReports(Collections.emptyList());
        mediaDto.setUpdatedAt(new Date());
        mediaDto.setCreatedAt(new Date());

        // Execução do teste para verificar se o endpoint "/images" cria a imagem corretamente
        mockMvc.perform(post("/images")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Sample Image\",\"description\":\"A sample image\",\"views\":0,\"downloads\":0,\"authorName\":\"Test User\",\"authorId\":\"id\",\"tagsId\":[],\"tags\":[],\"file\":null,\"fileId\":\"id\",\"reports\":[],\"updatedAt\":\"2023-07-21T00:00:00.000Z\",\"createdAt\":\"2023-07-21T00:00:00.000Z\"}"))
                .andExpect(status().isCreated());

        // Verificação se o método mediaService.create foi chamado exatamente uma vez com qualquer objeto MediaDto como argumento
        verify(mediaService, times(1)).create(any(MediaDto.class));

        // Verificação de que não há mais interações com o serviço mediaService após o teste
        verifyNoMoreInteractions(mediaService);
    }
}