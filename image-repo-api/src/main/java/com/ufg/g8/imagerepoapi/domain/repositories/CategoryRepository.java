package com.ufg.g8.imagerepoapi.domain.repositories;

import com.ufg.g8.imagerepoapi.domain.models.Category;
import com.ufg.g8.imagerepoapi.domain.models.Media;
import com.ufg.g8.imagerepoapi.domain.models.Tag;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    Boolean existsByMediaAndTag(Media media, Tag tag);

    void deleteAllByMedia(Media media);

}
