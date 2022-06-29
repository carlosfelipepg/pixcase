package com.casepix.pix.application.mapper;

import com.casepix.pix.application.domain.dto.KeyDto;
import com.casepix.pix.application.domain.model.Key;
import com.casepix.pix.application.rest.request.CreateKeyRequest;
import com.casepix.pix.application.rest.request.UpdateKeyRequest;
import com.casepix.pix.application.rest.response.KeyResponse;
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

    public Key toKey(UpdateKeyRequest request) {
        return modelMapper.map(request, Key.class);
    }

    public void merge(Key keySource, Key keyDestination) {
        modelMapper.map(keySource, keyDestination);
    }

    public KeyResponse toKeyResponse(Key key) {
        return modelMapper.map(key, KeyResponse.class);
    }
}
