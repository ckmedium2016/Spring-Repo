package com.audition.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class RestTemplateErrorHandler {

    public boolean hasError(HttpClientErrorException e) {

        return e.getStatusCode().is4xxClientError() || e.getStatusCode().is5xxServerError();

    }

    public void handleError(HttpClientErrorException e) {
        if (e.getStatusCode().is5xxServerError()) {

            if (e.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {

                throw new ServiceUnAvailableException("Service is currently unavailable");

            } else if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {

                throw new ServiceUnAvailableException("Service is currently unavailable");

            }

            // handle more server errors

        } else if (e.getStatusCode().is4xxClientError()) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {

                throw new UnAuthorizedException("Unauthorized access");

            } else if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {

                throw new UnAuthorizedException("Unauthorized access");

            } else {

                throw new SystemException("Unknown Error message");
            }
            // handle more client errors

        }

    }

}
