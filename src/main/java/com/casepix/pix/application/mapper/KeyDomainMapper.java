package com.casepix.pix.application.mapper;

import com.casepix.pix.application.domain.dto.KeyDto;
import com.casepix.pix.application.domain.model.Key;
import com.casepix.pix.application.rest.request.CreateKeyRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
;

@RequiredArgsConstructor
public class KeyDomainMapper {

    private final ModelMapper modelMapper;

    public KeyDto toKeyDto(Key key) {
        return modelMapper.map(key, KeyDto.class);
    }

    public Key toKey(CreateKeyRequest request) {
        return modelMapper.map(request, Key.class);
    }

}
