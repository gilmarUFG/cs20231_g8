package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.presentation.dtos.EnumDto;
import com.ufg.g8.imagerepoapi.presentation.services.IGenericEnumService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ApplicationControllerTest {
    @Mock
    private IGenericEnumService genericEnumService;

    @Test
    public void testHealth() {
        String expectedResult = "Pixel Port API Running!";
        String actualResult = health();
        assertEquals(expectedResult, actualResult);
    }

    private String health() {
        return "Pixel Port API Running!";
    }

    @Test
    public void testLoadEnum() {
        String enumType = "ExampleEnum";

        List<EnumDto> expectedList = new ArrayList<>();
        expectedList.add(new EnumDto("value1", "description1"));
        expectedList.add(new EnumDto("value2", "description2"));

        when(genericEnumService.loadEnum(enumType)).thenReturn(expectedList);

        List<EnumDto> actualList = loadEnum(enumType); // Call the loadEnum() method directly
        assertEquals(expectedList, actualList);
    }

    private List<EnumDto> loadEnum(String enumType) {
        return genericEnumService.loadEnum(enumType);
    }
}