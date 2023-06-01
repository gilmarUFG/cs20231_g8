package com.ufg.g8.imagerepoapi.domain.repositories;

import com.ufg.g8.imagerepoapi.domain.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByLogin(String login);

}
