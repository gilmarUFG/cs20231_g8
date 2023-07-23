package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.infrastructure.enums.ActionType;
import com.ufg.g8.imagerepoapi.presentation.controllers.AuditController;
import com.ufg.g8.imagerepoapi.presentation.dtos.AuditDto;
import com.ufg.g8.imagerepoapi.presentation.services.IAuditService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuditControllerTest {

    @Mock
    private IAuditService auditService;

    @InjectMocks
    private AuditController auditController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Inicialização dos mocks
        MockitoAnnotations.openMocks(this);
        // Configuração do MockMvc com o controlador a ser testado
        mockMvc = MockMvcBuilders.standaloneSetup(auditController).build();
    }

    @Test
    public void testAudit_CriarAcao() throws Exception {
        String entity = "EntidadeExemplo";
        ObjectId entityId = new ObjectId();
        ActionType action = ActionType.CREATE;
        String json = "{\"campo1\":\"valor1\", \"campo2\":\"valor2\"}";

        AuditDto auditDto = new AuditDto();
        auditDto.setEntity(entity);
        auditDto.setEntityId(entityId.toHexString());
        auditDto.setAction(action.name());
        auditDto.setJson(json);

        // Mock do serviço de auditoria
        doNothing().when(auditService).audit(any(), any(), any(), any());

        mockMvc.perform(post("/auditing")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"entity\":\"" + entity + "\",\"entityId\":\"" + entityId + "\",\"action\":\"" + action + "\",\"json\":" + json + "}"))
                .andExpect(status().isCreated());

        // Verifica se o método do serviço de auditoria foi chamado corretamente
        verify(auditService, times(1)).audit(entity, entityId, action, json);
        verifyNoMoreInteractions(auditService);
    }

    @Test
    public void testAudit_JsonInvalido() throws Exception {
        String entity = "EntidadeExemplo";
        ObjectId entityId = new ObjectId();
        ActionType action = ActionType.UPDATE;
        String jsonInvalido = "{\"campo1\":\"valor1\", \"campo2\":\"valor2\""; // Falta o fechamento da chave

        mockMvc.perform(post("/auditing")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"entity\":\"" + entity + "\",\"entityId\":\"" + entityId + "\",\"action\":\"" + action + "\",\"json\":" + jsonInvalido + "}"))
                .andExpect(status().isBadRequest());

        // Verifica que o método do serviço de auditoria não foi chamado
        verifyNoInteractions(auditService);
    }
}