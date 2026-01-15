package br.com.fiap.clean_arch.domain.exceptions;

public class ResouceNotFoundException extends DomainException {
    public ResouceNotFoundException(String message) {
        super(message);
    }
}
