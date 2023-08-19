package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.domain.services.filters.MediaFilter;
import com.ufg.g8.imagerepoapi.presentation.controllers.MediaController;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.ReportDto;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MediaControllerTest {

    // Mocks para o serviço e o controlador
    @Mock
    private IMediaService mediaService;

    @InjectMocks
    private MediaController mediaController;

    @BeforeEach
    void setUp() {
        // Inicialização do Mockito para os testes
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create_ShouldCallMediaServiceCreate() throws Exception {
        // Teste para verificar se o método "create" do controlador chama o método correspondente do serviço
        MediaDto mediaDto = new MediaDto();
        invokePrivateMethod("create", mediaDto);
        verify(mediaService, times(1)).create(mediaDto);
    }

    @Test
    void read_ShouldCallMediaServiceRead() throws Exception {
        // Teste para verificar se o método "read" do controlador chama o método correspondente do serviço
        ObjectId id = new ObjectId();
        invokePrivateMethod("read", id);
        verify(mediaService, times(1)).read(id);
    }

    @Test
    void readAll_ShouldCallMediaServiceReadAll() throws Exception {
        // Teste para verificar se o método "readAll" do controlador chama o método correspondente do serviço
        MediaFilter filter = new MediaFilter();
        invokePrivateMethod("readAll", filter);
        verify(mediaService, times(1)).readAll(filter);
    }


    @Test
    void update_ShouldCallMediaServiceUpdate() throws Exception {
        // Teste para verificar se o método "update" do controlador chama o método correspondente do serviço
        ObjectId id = new ObjectId();
        MediaDto mediaDto = new MediaDto();
        invokePrivateMethod("update", id, mediaDto);
        verify(mediaService, times(1)).update(id, mediaDto);
    }

    @Test
    void delete_ShouldCallMediaServiceDelete() throws Exception {
        // Teste para verificar se o método "delete" do controlador chama o método correspondente do serviço
        ObjectId id = new ObjectId();
        invokePrivateMethod("delete", id);
        verify(mediaService, times(1)).delete(id);
    }

    @Test
    void report_ShouldCallMediaServiceReport() throws Exception {
        // Teste para verificar se o método "report" do controlador chama o método correspondente do serviço
        ObjectId id = new ObjectId();
        ObjectId userId = new ObjectId();
        ReportDto reportDto = new ReportDto();
        invokePrivateMethod("report", id, userId, reportDto);
        verify(mediaService, times(1)).report(id, userId, reportDto);
    }

    // Método auxiliar para invocar métodos privados usando reflexão
    private void invokePrivateMethod(String methodName, Object... args) throws Exception {
        Class<?>[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        // Utiliza reflexão para invocar o método privado do controlador com os argumentos passados
        java.lang.reflect.Method method = MediaController.class.getDeclaredMethod(methodName, argTypes);
        method.setAccessible(true);
        method.invoke(mediaController, args);
    }
}