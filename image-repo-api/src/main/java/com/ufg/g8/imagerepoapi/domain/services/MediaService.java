package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.*;
import com.ufg.g8.imagerepoapi.domain.repositories.*;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import com.ufg.g8.imagerepoapi.presentation.services.ITagService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService implements IMediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public void create(MediaDto mediaDto) {
        Media media = new Media();
        media.setName(mediaDto.getName());
        media.setDescription(mediaDto.getDescription());
        media.setViews(0);
        media.setDownloads(0);
        User user = this.userRepository.findById(mediaDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        media.setAuthor(user);
        MediaFile mediaFile = this.mediaFileRepository.findById(mediaDto.getFileId())
                .orElseThrow(() -> new NotFoundException("Arquivo não encontrado"));
        media.setMediaFile(mediaFile);
        Media savedMedia = this.mediaRepository.save(media);
        mediaDto.getTagsId().forEach(tagId -> this.linkMediaToTag(savedMedia, tagId));
    }

    public MediaDto read(ObjectId id) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imagem não encontrada"));
        return this.mapModelToDto(media);
    }

    public void update(ObjectId id, MediaDto mediaDto) {
        Media media = new Media();
        media.setName(mediaDto.getName());
        media.setDescription(mediaDto.getDescription());
        ObjectId mediaFileId = mediaDto.getFileId();
        if(mediaFileId != null && media.getMediaFile().getId() != mediaFileId) {
            MediaFile mediaFile = this.mediaFileRepository.findById(mediaFileId)
                    .orElseThrow(() -> new NotFoundException("Arquivo não encontrado"));
            media.setMediaFile(mediaFile);
        }
        this.mediaRepository.save(media);
    }

    public void delete(ObjectId id) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imagem não encontrada"));
        this.categoryRepository.deleteAllByMedia(media);
        this.mediaRepository.deleteById(id);
    }

    private MediaDto mapModelToDto(Media media) {
        MediaDto mediaDto = new MediaDto();
        BeanUtils.copyProperties(media, mediaDto);
        return mediaDto;
    }

    private void linkMediaToTag(Media media, ObjectId tagId) {
        Tag tag = this.tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Tag não encontrada"));
        if(!this.categoryRepository.existsByMediaAndTag(media, tag)) {
            Category savedCategory = this.categoryRepository.save(new Category(media, tag));
            this.addCategoryToMedia(media, savedCategory);
            this.addCategoryToTag(tag, savedCategory);
        }
    }

    private void addCategoryToMedia(Media media, Category category) {
        List<Category> categories = media.getCategories();
        categories.add(category);
        media.setCategories(categories);
        this.mediaRepository.save(media);
    }

    private void addCategoryToTag(Tag tag, Category category) {
        List<Category> categories = tag.getCategories();
        categories.add(category);
        tag.setCategories(categories);
        this.tagRepository.save(tag);
    }

}