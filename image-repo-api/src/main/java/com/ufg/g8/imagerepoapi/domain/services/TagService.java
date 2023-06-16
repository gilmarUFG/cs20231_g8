package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.Tag;
import com.ufg.g8.imagerepoapi.domain.repositories.TagRepository;
import com.ufg.g8.imagerepoapi.infrastructure.enums.TagColors;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.InvalidValueException;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
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
        if(this.tagRepository.existsByTag(tag))
            return this.getTagByText(tag);
        TagColors color = this.getRandomColor();
        Tag newTag = new Tag(tag, color);
        Tag savedTag = this.tagRepository.save(newTag);
        return this.mapModelToDto(savedTag);
    }

    private TagDto getTagByText(String tag) {
        Tag savedTag = this.tagRepository.findByTag(tag)
                .orElseThrow(() -> new NotFoundException("Tag não encontrada"));
        return this.mapModelToDto(savedTag);
    }

    private TagDto mapModelToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        BeanUtils.copyProperties(tag, tagDto);
        tagDto.setTagBackground(tag.getColor().getBackgroundColor());
        tagDto.setTagTextColor(tag.getColor().getTextColor());
        return tagDto;
    }

    private TagColors getRandomColor() {
        TagColors[] colors = TagColors.values();
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }

}
