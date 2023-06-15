package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.Tag;
import com.ufg.g8.imagerepoapi.domain.repositories.TagRepository;
import com.ufg.g8.imagerepoapi.infrastructure.enums.TagColors;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.InvalidValueException;
import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class TagService implements ITagService {

    @Autowired
    private TagRepository tagRepository;

    public TagDto create(String tag) {
        if(!StringUtils.hasText(tag))
            throw new InvalidValueException("O conteúdo da Tag está vazio");
        TagColors color = this.getRandomColor();
        Tag newTag = new Tag(tag, color);
        Tag savedTag = this.tagRepository.save(newTag);
        TagDto tagDto = new TagDto();
        BeanUtils.copyProperties(savedTag, tagDto);
        tagDto.setTagBackground(color.getBackgroundColor());
        tagDto.setTagTextColor(color.getTextColor());
        return tagDto;
    }

    private TagColors getRandomColor() {
        TagColors[] colors = TagColors.values();
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }

}
