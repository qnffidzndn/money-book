package com.moneybook.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 공통 에러 응답 클래스
 * 예외 발생 시 클라이언트에게 반환되는 표준 에러 응답 형식입니다.
 */
@Getter
@Builder
public class ErrorResponse {

    /** HTTP 상태 코드 */
    private final int status;

    /** 에러 메시지 */
    private final String message;

    /** 에러 발생 시각 */
    private final LocalDateTime timestamp;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
