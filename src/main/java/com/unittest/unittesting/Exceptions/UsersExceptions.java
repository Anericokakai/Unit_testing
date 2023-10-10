package com.unittest.unittesting.Exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Builder
@Data

public class UsersExceptions {
    private final String  message;
    private final Throwable throwable;
}
