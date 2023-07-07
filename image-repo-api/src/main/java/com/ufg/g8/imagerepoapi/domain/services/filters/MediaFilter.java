package com.ufg.g8.imagerepoapi.domain.services.filters;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class MediaFilter {
    private String name;
    private String description;
    private List<String> tags;
}
