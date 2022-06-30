package com.casepix.pix.application.domain.service;

import com.casepix.pix.application.domain.enumaration.TypeKeyEnum;
import com.casepix.pix.application.domain.enumaration.TypePersonEnum;
import com.casepix.pix.application.domain.filter.KeyFilter;
import com.casepix.pix.application.domain.model.Key;
import com.casepix.pix.application.domain.repository.KeyRepository;
import com.casepix.pix.application.mapper.KeyDomainMapper;
import com.casepix.pix.application.rest.response.CreateResponse;
import com.casepix.pix.application.rest.response.FindResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
@Service
public class PixService {
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private KeyDomainMapper keyDomainMapper;

    private CreateResponse response = new CreateResponse();

    private final String now = LocalDate.now().toString();

    /**
     * Create pix key for the informed account holder
     *
     * @param key - Information object sent by the user
     * @return - Key creation status
     */
    public CreateResponse createKey(Key key) {
        response = new CreateResponse();
        KeyFilter keyFilter = new KeyFilter();
        keyFilter.setNumberAccount(key.getNumberAccount());
        List<Key> keys = keyRepository.findKeysByFilter(keyFilter);

        // Check if the account holder has reached the limit of keys
        var quantityKeys =  (long) keys.size();
        if((key.getTypePerson() == TypePersonEnum.F && quantityKeys >= 5) ||
                (key.getTypePerson()) == TypePersonEnum.J && quantityKeys >= 20) {
            response.setMessage("Número de chaves para essa conta atingido");
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            return response;
        }

        validateTypeKey(key);
        if (response.getStatus() != HttpStatus.OK) {
            return response;
        }

        UUID uniqueId = UUID.randomUUID();
        key.setId(uniqueId.toString());
        key.setCreatedAt(now);
        try {
            keyRepository.save(key);
        } catch (DuplicateKeyException e) {
            response.setMessage("Chave já existe na base de dados");
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            return response;
        }
        keyDomainMapper.toKeyDto(key);
        response.setMessage("Chave criada com sucesso");
        response.setId(uniqueId);
        return response;
    }

    /**
     * Update pix key for the informed account holder
     *
     * @param key - Information object sent by the user
     * @return - Key creation status
     */
    public ResponseEntity<Object> updateKey(Key key) {
        response = new CreateResponse();
        var savedKey = keyRepository.findKeyById(key.getId());
        if (savedKey == null) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Chave não encontrada");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        keyDomainMapper.merge(key, savedKey);

        validateTypeKey(savedKey);
        if (response.getStatus() != HttpStatus.OK) {
            return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        keyRepository.save(savedKey);

        return new ResponseEntity<>(savedKey, HttpStatus.OK);
    }

    public ResponseEntity<Object> findKeys(KeyFilter filter) {
        FindResponse findResponse = new FindResponse();

        var verifyFieldsNull = Stream.of(
                        filter.getValueKey(),
                        filter.getTypeKey(),
                        filter.getNumberAgency(),
                        filter.getNumberAccount(),
                        filter.getAccountHolderName(),
                        filter.getCreatedAt())
                .allMatch(Objects::isNull);

        if (filter.getId() != null && !verifyFieldsNull) {
            findResponse.setMessage("Filtros inválidos");
            return new ResponseEntity<>(findResponse, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<Key> listFields = keyRepository.findKeysByFilter(filter);
        if ((long) listFields.size() == 0){
            findResponse.setMessage("Chave não encontrada");
            return new ResponseEntity<>(findResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listFields, HttpStatus.OK);
    }

    private void validateTypeKey(Key key) {
        // Validate keys format
        if (key.getTypeKey() == TypeKeyEnum.CELULAR){
            Boolean valid = validatePhoneRegex(key.getValueKey());
            if (Boolean.FALSE.equals(valid)) {
                response.setMessage("Número celular inválido");
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else if (key.getTypeKey() == TypeKeyEnum.EMAIL) {
            Boolean valid = validateEmailRegex(key.getValueKey());
            if (Boolean.FALSE.equals(valid)) {
                response.setMessage("E-mail inválido");
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } else if (key.getTypeKey() == TypeKeyEnum.CPF){
            Boolean valid = validateCpfRegex(key.getValueKey());
            if (Boolean.FALSE.equals(valid)) {
                response.setMessage("CPF inválido");
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                key.setValueKey(key.getValueKey().replaceAll("[.-]", ""));
            }
        }
    }

    private Boolean validatePhoneRegex(String value) {
        Pattern pattern = Pattern.compile("\\+([0-9]{2})([0-9]{2})([0-9]{9})");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private Boolean validateEmailRegex(String value) {
        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private Boolean validateCpfRegex(String value) {
        CPFValidator validator = new CPFValidator();
        validator.initialize(null);
        return validator.isValid(value, null);
    }
}
