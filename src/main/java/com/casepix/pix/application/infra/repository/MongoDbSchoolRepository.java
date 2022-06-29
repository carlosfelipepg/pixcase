package com.casepix.pix.application.infra.repository;


import com.casepix.pix.application.domain.model.Key;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoDbSchoolRepository extends MongoRepository<Key, String> {
    List<Key> findAllByNumberAccount(String account);

    Key findKeyById(String id);

}
