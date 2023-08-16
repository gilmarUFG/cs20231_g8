package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.*;
import com.ufg.g8.imagerepoapi.domain.repositories.*;
import com.ufg.g8.imagerepoapi.domain.services.filters.MediaFilter;
import com.ufg.g8.imagerepoapi.infrastructure.enums.ActionType;
import com.ufg.g8.imagerepoapi.infrastructure.events.AuditEvent;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.InvalidValueException;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
import com.ufg.g8.imagerepoapi.infrastructure.utils.mapper.AppModelMapper;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.ReportDto;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService implements IMediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaReportRepository mediaReportRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void create(MediaDto mediaDto) {
        Media media = new Media();
        media.setName(mediaDto.getName());
        media.setDescription(mediaDto.getDescription());
        media.setViews(0);
        media.setDownloads(0);
        if(mediaDto.getAuthorId() == null)
            throw new InvalidValueException("Autor não foi vinculado à imagem");
        User user = this.userRepository.findById(mediaDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        media.setAuthor(user);
        if(mediaDto.getFileId() == null)
            throw new InvalidValueException("É necessário inserir um arquivo de imagem");
        MediaFile mediaFile = this.mediaFileRepository.findById(mediaDto.getFileId())
                .orElseThrow(() -> new NotFoundException("Arquivo não encontrado"));
        media.setMediaFile(mediaFile);
        Media savedMedia = this.mediaRepository.save(media);
        if(mediaDto.getTagsId() != null)
            mediaDto.getTagsId().forEach(tagId -> this.linkMediaToTag(savedMedia, tagId));
        this.publisher.publishEvent(
                new AuditEvent(
                        this,
                        savedMedia,
                        "MEDIA",
                        savedMedia.getId(),
                        ActionType.CREATE
                )
        );
    }

    @Override
    public MediaDto read(ObjectId id) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imagem não encontrada"));
        return AppModelMapper.mapModelToDto(media);
    }

    @Override
    public List<MediaDto> readAll(MediaFilter filter) {
        Query query = new Query();
        if (filter.getName() != null) {
            query.addCriteria(Criteria.where("name").regex(filter.getName()));
        }
        if (filter.getDescription() != null) {
            query.addCriteria(Criteria.where("description").regex(filter.getDescription()));
        }
        if (filter.getTags() != null && !filter.getTags().isEmpty()) {
            Criteria tagsCriteria = new Criteria();
            for (String tag : filter.getTags()) {
                tagsCriteria.orOperator(Criteria.where("categories.tag.tag").regex(tag, "i"));
            }
            query.addCriteria(tagsCriteria);
        }

        return Optional.ofNullable(mongoTemplate.find(query, Media.class))
                .map(medias -> medias.stream().map(AppModelMapper::mapModelToDto).toList())
                .orElse(new ArrayList<>());
    }

    @Override
    public void update(ObjectId id, MediaDto mediaDto) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imagem não encontrada"));
        media.setName(mediaDto.getName());
        media.setDescription(mediaDto.getDescription());
        ObjectId mediaFileId = mediaDto.getFileId();
        if(mediaFileId != null && !media.getMediaFile().getId().equals(mediaFileId)) {
            MediaFile mediaFile = this.mediaFileRepository.findById(mediaFileId)
                    .orElseThrow(() -> new NotFoundException("Arquivo não encontrado"));
            ObjectId toBeDeletedId = media.getMediaFile().getId();
            media.setMediaFile(mediaFile);
            this.mediaFileRepository.deleteById(toBeDeletedId);
        }
        media.getCategories()
                .stream()
                .map(Category::getTag)
                .map(Tag::getId)
                .filter(tagId -> mediaDto.getTagsId().contains(tagId))
                .forEach(tagId -> this.linkMediaToTag(media, tagId));
        Media savedMedia = this.mediaRepository.save(media);
        this.publisher.publishEvent(
                new AuditEvent(
                        this,
                        savedMedia,
                        "MEDIA",
                        savedMedia.getId(),
                        ActionType.CREATE
                )
        );
    }

    @Override
    public void delete(ObjectId id) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imagem não encontrada"));
        this.categoryRepository.deleteAllByMedia(media);
        this.mediaRepository.deleteById(id);
        this.publisher.publishEvent(
                new AuditEvent(
                        this,
                        "MEDIA",
                        id,
                        ActionType.CREATE
                )
        );
    }

    @Override
    public void report(ObjectId id, ObjectId userId, ReportDto reportDto) {
        Media media = this.mediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imagem não encontrada"));

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        MediaReport report = new MediaReport();
        report.setReporter(user);
        report.setMediaReported(media);
        report.setDescription(reportDto.getDescription());
        report.setReasons(reportDto.getReasons());

        MediaReport savedReport = this.mediaReportRepository.save(report);

        addReportToUser(user, savedReport);
        addReportToMedia(media, savedReport);
    }

    private void addReportToUser(User user, MediaReport report) {
        user.getReports().add(report);
        this.userRepository.save(user);
    }

    private void addReportToMedia(Media media, MediaReport report) {
        media.getReports().add(report);
        this.mediaRepository.save(media);
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
