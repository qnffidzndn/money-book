package com.moneybook.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 에러 코드 열거형
 * HTTP 상태 코드와 사용자 메시지를 함께 정의합니다.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(404, "해당 멤버를 찾을 수 없습니다."),
    INCOME_NOT_FOUND(404, "해당 수입 내역을 찾을 수 없습니다."),
    PLAN_NOT_FOUND(404, "해당 계획을 찾을 수 없습니다."),
    SAVINGS_ACCOUNT_NOT_FOUND(404, "해당 적금을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(404, "해당 카테고리를 찾을 수 없습니다."),
    INVALID_INPUT(400, "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다.");

    /** HTTP 상태 코드 */
    private final int status;

    /** 사용자에게 노출되는 에러 메시지 */
    private final String message;
}
