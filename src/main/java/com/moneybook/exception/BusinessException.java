package com.moneybook.exception;

import lombok.Getter;

/**
 * 비즈니스 예외 기본 클래스
 * 도메인 로직에서 발생하는 모든 커스텀 예외의 최상위 클래스입니다.
 */
@Getter
public class BusinessException extends RuntimeException {

    /** 에러 코드 */
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
