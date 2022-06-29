package com.casepix.pix.application.rest.controller;


import com.casepix.pix.application.domain.service.PixService;
import com.casepix.pix.application.mapper.KeyDomainMapper;
import com.casepix.pix.application.rest.request.CreateKeyRequest;
import com.casepix.pix.application.rest.request.UpdateKeyRequest;
import com.casepix.pix.application.rest.response.CreateResponse;
import com.casepix.pix.application.rest.response.KeyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/pix")
@RequiredArgsConstructor
public class PixController {
    private final PixService pixService;
    private final KeyDomainMapper keyDomainMapper;

    @PostMapping
    public ResponseEntity<CreateResponse> createKey(
            @Valid @RequestBody CreateKeyRequest createKeyRequest)  {
        CreateResponse response = pixService.createKey(keyDomainMapper.toKey(createKeyRequest));
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping
    public ResponseEntity<Object> updateKey(
            @Valid @RequestBody UpdateKeyRequest updateKeyRequest)  {
        return pixService.updateKey(keyDomainMapper.toKey(updateKeyRequest));
    }

}
