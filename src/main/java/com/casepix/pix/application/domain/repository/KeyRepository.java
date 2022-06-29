package com.casepix.pix.application.domain.repository;


import com.casepix.pix.application.domain.filter.KeyFilter;
import com.casepix.pix.application.domain.model.Key;

import java.util.List;
import java.util.Optional;

public interface KeyRepository {

    Key save(Key key);

    void delete(Key key);

    List<Key> findKeysByFilter(KeyFilter filter);

    Key findKeyById(String id);

}
