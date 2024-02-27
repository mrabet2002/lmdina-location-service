package com.mdina.location.web;

import com.mdina.location.exceptions.GlobalApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Exception handler for the controller level
 * Customizing exceptions to send a clear
 * and unified response to the client
 * */
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Gère toute exception qui étend BilleterieApiException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur correspondante.
     */
    @ExceptionHandler(value = {GlobalApiException.class})
    public ResponseEntity<String> handleApiGlobalException(GlobalApiException exception){
        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }

    /**
     * Gère les exceptions de type AuthenticationException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur d'authentification.
     */
//    @ExceptionHandler(value = {AuthenticationException.class})
//    public ResponseEntity<ResponseModel> handleAuthenticationException(AuthenticationException exception){
//        // Définit le statut comme 'FORBIDDEN'.
//        HttpStatus status = HttpStatus.FORBIDDEN;
//
//        // Crée une réponse d'erreur en utilisant l'exception et son statut.
//        ResponseModel apiException = ResponseModel.error(
//                exception.getMessage(),
//                status
//        );
//
//        // Renvoie la ResponseEntity avec la réponse d'erreur.
//        return ResponseEntity.status(status).body(apiException);
//    }

    /**
     * Gère les exceptions de type AccessDeniedException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur d'accès refusé.
     */
//    @ExceptionHandler(value = {AccessDeniedException.class})
//    public ResponseEntity<ResponseModel> handleAccessDeniedException(AccessDeniedException exception){
//        // Définit le statut comme 'FORBIDDEN'.
//        HttpStatus status = HttpStatus.FORBIDDEN;
//
//        // Crée une réponse d'erreur en utilisant l'exception et son statut.
//        ResponseModel apiException = ResponseModel.error(
//                exception.getMessage(),
//                status
//        );
//
//        // Renvoie la ResponseEntity avec la réponse d'erreur.
//        return ResponseEntity.status(status).body(apiException);
//    }

    /**
     * Gère les exceptions de type DataIntegrityViolationException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur de violation de l'intégrité des données.
     */
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException exception){

        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    /**
     * Gère toute exception de type RuntimeException.
     * Cette méthode renvoie une ResponseEntity contenant une réponse d'erreur interne du serveur.
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<String> handleApiGlobalException(RuntimeException exception){

        // Renvoie la ResponseEntity avec la réponse d'erreur.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

}
