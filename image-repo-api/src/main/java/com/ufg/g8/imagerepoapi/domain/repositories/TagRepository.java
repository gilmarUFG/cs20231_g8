package com.ufg.g8.imagerepoapi.domain.repositories;

import com.ufg.g8.imagerepoapi.domain.models.Tag;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<Tag, ObjectId> {

    Optional<Tag> findByTag(String tag);

    Boolean existsByTag(String tag);

}
