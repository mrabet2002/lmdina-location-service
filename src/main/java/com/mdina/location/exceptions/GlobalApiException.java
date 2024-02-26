package com.mdina.location.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * BilleterieApiException est l'exception générale de notre API.
 * Toutes les autres exceptions de notre API doivent hériter de cette classe.
 * Lorsqu'une exception fille de cette classe est déclenchée, l'exception handler la capturera
 * et utilisera son message et son code de statut pour une gestion appropriée des erreurs.
 **/
@Data
public class GlobalApiException extends RuntimeException {
    private HttpStatus status;
    public GlobalApiException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

}
