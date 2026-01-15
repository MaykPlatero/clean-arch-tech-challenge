package br.com.fiap.clean_arch.domain.exceptions;

public class BusinessRuleViolationException extends DomainException {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
