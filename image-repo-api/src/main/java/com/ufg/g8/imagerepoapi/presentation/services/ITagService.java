package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import org.springframework.stereotype.Service;

@Service
public interface ITagService {

    TagDto create(String text);

}
