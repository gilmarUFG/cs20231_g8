package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.repositories.MediaRepository;
import com.ufg.g8.imagerepoapi.presentation.services.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService implements IMediaService {

    @Autowired
    private MediaRepository mediaRepository;

}
