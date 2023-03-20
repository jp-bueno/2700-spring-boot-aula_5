package med.voll.api.domain;

import org.springframework.http.HttpStatus;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensagem, HttpStatus status) {
        super(mensagem);
    }
}
