package com.ufg.g8.imagerepoapi.domain.repositories;

import com.ufg.g8.imagerepoapi.domain.models.Media;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends MongoRepository<Media, ObjectId> {
}
