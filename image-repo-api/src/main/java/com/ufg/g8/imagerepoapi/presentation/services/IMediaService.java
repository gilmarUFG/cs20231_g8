package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.presentation.dtos.MediaDto;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public interface IMediaService {

    void create(MediaDto mediaDto);

    MediaDto read(ObjectId id);

    void update(ObjectId id, MediaDto mediaDto);

    void delete(ObjectId id);

}
