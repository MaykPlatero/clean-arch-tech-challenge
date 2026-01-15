package br.com.fiap.clean_arch.presentation.exception;

import java.net.URI;

public enum ProblemType {
    BUSINESS_ERROR(
            "http://fiap.com.br/problems/business-error",
            "Erro de negócio"
    ),
    RESOUCE_NOT_FOUND(
            "https://fiap.com.br/problems/resouce-not-found",
            "Recurso não encontrado"
    ),
    VALIDATION_ERROR(
            "https://fiap.com.br/problems/validation-error",
            "Erro de validação"
    ),
    INTERNAL_ERROR(
            "https://fiap.com.br/problems/internal-error",
            "Erro interno"
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
