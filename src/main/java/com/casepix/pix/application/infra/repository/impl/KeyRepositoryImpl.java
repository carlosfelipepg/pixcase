package com.casepix.pix.application.infra.repository.impl;

import com.casepix.pix.application.domain.filter.KeyFilter;
import com.casepix.pix.application.domain.model.Key;
import com.casepix.pix.application.domain.repository.KeyRepository;
import com.casepix.pix.application.infra.repository.MongoDbSchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class KeyRepositoryImpl implements KeyRepository {

    private final MongoDbSchoolRepository mongoDbSchoolRepository;
    private final MongoTemplate mongoTemplate;
    public static final String CREATED_AT = "createdAt";


    @Override
    public Key save(Key key) {
        return mongoDbSchoolRepository.save(key);
    }

    @Override
    public void delete(Key key) { mongoDbSchoolRepository.delete(key); }

    @Override
    public List<Key> findKeysByFilter(KeyFilter filter) {
        var query = new Query();
        Optional.ofNullable(filter.getTypeKey())
                .ifPresent(typeKey -> query.addCriteria(Criteria.where("typeKey").is(typeKey)));
        Optional.ofNullable(filter.getValueKey())
                .ifPresent(valueKey -> query.addCriteria(Criteria.where("valueKey").is(valueKey)));
        Optional.ofNullable(filter.getTypeAccount())
                .ifPresent(typeAccount -> query.addCriteria(Criteria.where("typeAccount").is(typeAccount)));
        Optional.ofNullable(filter.getNumberAccount())
                .ifPresent(numberAccount -> query.addCriteria(Criteria.where("numberAccount").is(numberAccount)));
        Optional.ofNullable(filter.getNumberAgency())
                .ifPresent(numberAgency -> query.addCriteria(Criteria.where("numberAgency").is(numberAgency)));
        Optional.ofNullable(filter.getAccountHolderName())
                .ifPresent(accountHolderName -> query.addCriteria(Criteria.where("accountHolderName").is(accountHolderName)));
        Optional.ofNullable(filter.getAccountHolderLastName())
                .ifPresent(accountHolderLastName -> query.addCriteria(Criteria.where("accountHolderLastName").is(accountHolderLastName)));
        Optional.ofNullable(filter.getTypePerson())
                .ifPresent(typePerson -> query.addCriteria(Criteria.where("typePerson").is(typePerson)));

        return mongoTemplate.find(query, Key.class);
    }

    @Override
    public Key findKeyById(String id) {
        return mongoDbSchoolRepository.findKeyById(id);
    }
}
