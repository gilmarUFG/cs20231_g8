package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.infrastructure.enums.ReportReason;
import com.ufg.g8.imagerepoapi.presentation.dtos.EnumDto;
import com.ufg.g8.imagerepoapi.presentation.services.IGenericEnumService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GenericEnumService implements IGenericEnumService {

    @Override
    public List<EnumDto> loadEnum(String enumType) {
        List<EnumDto> enums = new ArrayList<>();
        switch (enumType) {
            case "REPORT_REASONS":
                enums = Arrays.stream(ReportReason.values()).map(reason -> new EnumDto(reason.name(), reason.getDescription())).toList();
                break;
        }
        return enums;
    }

}
