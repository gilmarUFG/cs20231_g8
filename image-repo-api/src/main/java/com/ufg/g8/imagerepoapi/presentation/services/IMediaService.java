package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.ReportDto;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public interface IMediaService {

    void create(MediaDto mediaDto);

    MediaDto read(ObjectId id);

    void update(ObjectId id, MediaDto mediaDto);

    void delete(ObjectId id);

    void report(ObjectId id, ObjectId userId, ReportDto reportDto);

}
