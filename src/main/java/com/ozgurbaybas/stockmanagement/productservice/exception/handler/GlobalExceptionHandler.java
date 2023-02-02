package com.ozgurbaybas.stockmanagement.productservice.exception.handler;

import com.ozgurbaybas.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.ozgurbaybas.stockmanagement.productservice.exception.exceptions.ProductNotCreatedException;
import com.ozgurbaybas.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.ozgurbaybas.stockmanagement.productservice.response.FriendlyMessage;
import com.ozgurbaybas.stockmanagement.productservice.response.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreatedException.class)
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException exception) {
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessage(Collections.singletonList(exception.getMessage()))
                .build();
    }
}

