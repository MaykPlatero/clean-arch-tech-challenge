package br.com.fiap.clean_arch.presentation.exception;

import java.net.URI;

public enum ProblemType {
    BUSINESS_ERROR(
            "http://fiap.com.br/problems/business-error",
            "Business error"
    ),
    RESOUCE_NOT_FOUND(
            "https://fiap.com.br/problems/resouce-not-found",
            "Resource not found"
    ),
    VALIDATION_ERROR(
            "https://fiap.com.br/problems/validation-error",
            "Validation error"
    ),
    INTERNAL_ERROR(
            "https://fiap.com.br/problems/internal-error",
            "Internal error"
    );
    private final String uri;
    private final String title;

    ProblemType(String uri, String title){
        this.uri=uri;
        this.title=title;
    }
    public URI getUri(){
        return URI.create(uri);
    }
    public String getTitle(){
        return title;
    }

}
