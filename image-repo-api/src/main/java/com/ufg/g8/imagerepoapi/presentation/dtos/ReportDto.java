package com.ufg.g8.imagerepoapi.presentation.dtos;

import com.ufg.g8.imagerepoapi.infrastructure.enums.ReportReason;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportDto {

    @NotNull
    private List<ReportReason> reasons;

    @NotNull
    private String description;

}
