package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.Tag;
import com.ufg.g8.imagerepoapi.domain.repositories.TagRepository;
import com.ufg.g8.imagerepoapi.infrastructure.enums.TagColor;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.InvalidValueException;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
import com.ufg.g8.imagerepoapi.infrastructure.utils.mapper.AppModelMapper;
import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;
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
        if(this.tagRepository.existsByTag(tag))
            return this.getTagByText(tag);
        TagColor color = this.getRandomColor();
        Tag newTag = new Tag(tag, color);
        Tag savedTag = this.tagRepository.save(newTag);
        return AppModelMapper.mapModelToDto(savedTag);
    }

    private TagDto getTagByText(String tag) {
        Tag savedTag = this.tagRepository.findByTag(tag)
                .orElseThrow(() -> new NotFoundException("Tag não encontrada"));
        return AppModelMapper.mapModelToDto(savedTag);
    }

    private TagColor getRandomColor() {
        TagColor[] colors = TagColor.values();
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }

}
