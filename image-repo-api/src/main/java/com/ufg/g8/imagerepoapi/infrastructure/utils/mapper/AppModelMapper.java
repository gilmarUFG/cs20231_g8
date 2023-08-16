package com.ufg.g8.imagerepoapi.infrastructure.utils.mapper;

import com.ufg.g8.imagerepoapi.domain.models.*;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.MediaFileDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.ReportDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.TagDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class AppModelMapper {

    public static MediaDto mapModelToDto(Media media) {
        MediaDto mediaDto = new MediaDto();
        BeanUtils.copyProperties(media, mediaDto);
        mediaDto.setId(media.getId().toString());
        mediaDto.setAuthorName(media.getAuthor().getName());
        mediaDto.setFile(AppModelMapper.mapModelToDto(media.getMediaFile()));
        List<TagDto> tags = media.getCategories().stream()
                .map(Category::getTag)
                .map(AppModelMapper::mapModelToDto).toList();
        mediaDto.setTags(tags);
        mediaDto.setReports(
                Optional.ofNullable(media.getReports())
                        .map(reports -> reports.stream().map(AppModelMapper::mapModelToDto).toList())
                        .orElse(new ArrayList<>())
        );
        return mediaDto;
    }

    public static MediaFileDto mapModelToDto(MediaFile mediaFile) {
        MediaFileDto mediaFileDto = new MediaFileDto();
        BeanUtils.copyProperties(mediaFile, mediaFileDto);
        mediaFileDto.setBase64(Base64.getEncoder().encodeToString(mediaFile.getData()));
        mediaFileDto.setId(mediaFile.getId().toString());
        return mediaFileDto;
    }

    public static TagDto mapModelToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        BeanUtils.copyProperties(tag, tagDto);
        tagDto.setId(tag.getId().toString());
        tagDto.setTagBackground(tag.getColor().getBackgroundColor());
        tagDto.setTagTextColor(tag.getColor().getTextColor());
        return tagDto;
    }

    public static ReportDto mapModelToDto(MediaReport report) {
        ReportDto reportDto = new ReportDto();
        reportDto.setReasons(report.getReasons());
        reportDto.setDescription(report.getDescription());
        return reportDto;
    }

}
