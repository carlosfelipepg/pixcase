package com.casepix.pix.application.rest.controller;


import com.casepix.pix.application.domain.service.PixService;
import com.casepix.pix.application.mapper.KeyDomainMapper;
import com.casepix.pix.application.rest.request.CreateKeyRequest;
import com.casepix.pix.application.rest.response.CreateResponse;
import com.casepix.pix.application.rest.response.PixResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

//    @GetMapping
//    public String findOrganizations(@Valid String teste, @RequestHeader HttpHeaders headers) {
//
//        return "true";
//
////        return ResponseEntity
////                .ok()
////                .header(HttpHeadersValue.CONTENT_RANGE.getName(), String.valueOf(pagedOrganizations.getTotal()))
////                .header(HttpHeadersValue.ACCEPT_RANGES.getName(), acceptableRange)
////                .header(HttpHeadersValue.TOTAL_PAGES.getName(), String.valueOf(pagedOrganizations.getTotalPages()))
////                .body(organizationMapper.toOrganizationDtoResponses(pagedOrganizations.getContent()));
//    }
}
