package com.ufg.g8.imagerepoapi;

import com.ufg.g8.imagerepoapi.infrastructure.enums.ActionType;
import com.ufg.g8.imagerepoapi.presentation.dtos.AuditDto;
import com.ufg.g8.imagerepoapi.presentation.services.IAuditService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private AuditControllerTest auditController;

    private MockMvc mockMvc;

    @Test
    public void testAudit() throws Exception {
        String entity = "SampleEntity";
        ObjectId entityId = new ObjectId();
        ActionType action = ActionType.CREATE;
        String json = "{\"field1\":\"value1\", \"field2\":\"value2\"}";

        AuditDto auditDto = new AuditDto();
        auditDto.setEntity(entity);
        auditDto.setEntityId(entityId.toHexString());
        auditDto.setAction(action.name());
        auditDto.setJson(json);

        // Mocking the audit service
        doNothing().when(auditService).audit(any(), any(), any(), any());

        mockMvc = MockMvcBuilders.standaloneSetup(auditController).build();
        mockMvc.perform(post("/auditing")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"entity\":\"" + entity + "\",\"entityId\":\"" + entityId + "\",\"action\":\"" + action + "\",\"json\":" + json + "}"))
                .andExpect(status().isCreated());

        // Verify that the audit service method was called
        verify(auditService, times(1)).audit(entity, entityId, action, json);
        verifyNoMoreInteractions(auditService);
    }
}
