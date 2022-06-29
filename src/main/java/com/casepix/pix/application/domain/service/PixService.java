package com.casepix.pix.application.domain.service;

import com.casepix.pix.application.domain.dto.KeyDto;
import com.casepix.pix.application.domain.enumaration.TypeKeyEnum;
import com.casepix.pix.application.domain.enumaration.TypePersonEnum;
import com.casepix.pix.application.domain.filter.KeyFilter;
import com.casepix.pix.application.domain.model.Key;
import com.casepix.pix.application.domain.repository.KeyRepository;
import com.casepix.pix.application.mapper.KeyDomainMapper;
import com.casepix.pix.application.rest.response.CreateResponse;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PixService {
    @Autowired
    private KeyRepository keyRepository;
    @Autowired
    private KeyDomainMapper keyDomainMapper;


    /**
     * Create pix key for the informed account holder
     *
     * @param key - Information object sent by the user
     * @return - Key creation status
     */
    public CreateResponse createKey(Key key) {
        CreateResponse response = new CreateResponse();
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

        // Validate keys format
        if (key.getTypeKey() == TypeKeyEnum.CELULAR){
            Boolean valid = validatePhoneRegex(key.getValueKey());
            if (Boolean.FALSE.equals(valid)) {
                response.setMessage("Número celular inválido");
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
                return response;
            }
        } else if (key.getTypeKey() == TypeKeyEnum.EMAIL) {
            Boolean valid = validateEmailRegex(key.getValueKey());
            if (Boolean.FALSE.equals(valid)) {
                response.setMessage("E-mail inválido");
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
                return response;
            }
        } else if (key.getTypeKey() == TypeKeyEnum.CPF){
            Boolean valid = validateCpfRegex(key.getValueKey());
            if (Boolean.FALSE.equals(valid)) {
                response.setMessage("CPF inválido");
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
                return response;
            } else {
                key.setValueKey(key.getValueKey().replaceAll("[.-]", ""));
            }
        }

        UUID uniqueId = UUID.randomUUID();
        key.setId(uniqueId.toString());
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
