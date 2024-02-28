package com.mdina.location.service.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Faire le mapping d'un objet en json (String)
 * Utiliser dans le SecurityConfig pour creer la reponse en cas d'erreur
 */
@Component
@RequiredArgsConstructor
public class JsonMapper  {
    private final ObjectMapper objectMapper;
    /**
     *  Maps an object to a json string
     *
     * @param object the object to convert
     * */
    public String objectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        return objectMapper.writeValueAsString(object);
    }
}
