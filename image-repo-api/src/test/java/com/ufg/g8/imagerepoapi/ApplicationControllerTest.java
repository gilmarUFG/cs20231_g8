package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.controllers.ApplicationController;
import com.ufg.g8.imagerepoapi.presentation.dtos.EnumDto;
import com.ufg.g8.imagerepoapi.presentation.services.IGenericEnumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

public class ApplicationControllerTest {

    @Mock
    private IGenericEnumService genericEnumService;

    @InjectMocks
    private ApplicationController applicationController;

    @BeforeEach
    public void setup() {
        // Inicialização dos mocks antes de cada teste
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHealth() {
        // Teste para verificar se a rota /app retorna a mensagem de saúde esperada
        String expectedResult = "Pixel Port API Running!";
        String actualResult = applicationController.health();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testLoadEnum_ValidEnumType() {
        // Teste para verificar se o método loadEnum funciona corretamente com um enumType válido

        String enumType = "ExampleEnum";

        List<EnumDto> expectedList = new ArrayList<>();
        expectedList.add(new EnumDto("value1", "description1"));
        expectedList.add(new EnumDto("value2", "description2"));

        // Definindo o comportamento esperado para o mock do serviço genericEnumService.loadEnum()
        when(genericEnumService.loadEnum(enumType)).thenReturn(expectedList);

        List<EnumDto> actualList = applicationController.loadEnum(enumType);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testLoadEnum_EmptyEnumType() {
        // Teste para verificar se o método loadEnum funciona corretamente com um enumType vazio

        String enumType = "";

        List<EnumDto> expectedList = new ArrayList<>();

        when(genericEnumService.loadEnum(enumType)).thenReturn(expectedList);

        List<EnumDto> actualList = applicationController.loadEnum(enumType);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testLoadEnum_NullEnumType() {
        // Teste para verificar se o método loadEnum funciona corretamente com um enumType nulo

        String enumType = null;

        List<EnumDto> expectedList = new ArrayList<>();

        when(genericEnumService.loadEnum(enumType)).thenReturn(expectedList);

        List<EnumDto> actualList = applicationController.loadEnum(enumType);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testLoadEnum_InvalidEnumType() {
        // Teste para verificar se o método loadEnum trata corretamente um enumType inválido

        String enumType = "InvalidEnum";

        when(genericEnumService.loadEnum(enumType)).thenReturn(null);

        List<EnumDto> actualList = applicationController.loadEnum(enumType);
        assertNotNull(actualList);
        assertTrue(actualList.isEmpty());
    }

    @Test
    public void testLoadEnum_ServiceFailure() {
        // Teste para verificar se o método loadEnum trata corretamente uma falha no serviço

        String enumType = "ServiceFailureEnum";

        when(genericEnumService.loadEnum(enumType)).thenThrow(new RuntimeException("Falha no serviço"));

        List<EnumDto> actualList = applicationController.loadEnum(enumType);
        assertNotNull(actualList);
        assertTrue(actualList.isEmpty());
    }
}