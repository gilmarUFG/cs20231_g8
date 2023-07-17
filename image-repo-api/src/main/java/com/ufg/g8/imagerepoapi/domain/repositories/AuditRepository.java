package com.ufg.g8.imagerepoapi.domain.repositories;

import com.ufg.g8.imagerepoapi.domain.models.Audit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AuditRepository extends MongoRepository<Audit, ObjectId> {
}