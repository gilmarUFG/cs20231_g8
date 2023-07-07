package com.ufg.g8.imagerepoapi.domain.models;

import com.ufg.g8.imagerepoapi.infrastructure.base.BaseEntity;
import com.ufg.g8.imagerepoapi.infrastructure.enums.ReportReason;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "reports")
public class MediaReport extends BaseEntity {

    @NotNull
    private List<ReportReason> reasons;

    @NotNull
    private String description;

    @DBRef
    private Media mediaReported;

    @DBRef
    private User reporter;

}
