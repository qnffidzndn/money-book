package com.moneybook.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 공통 API 응답 래퍼
 * 모든 REST API 응답을 통일된 형식으로 반환합니다.
 *
 * @param <T> 응답 데이터 타입
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    /** 요청 성공 여부 */
    private final boolean success;

    /** 응답 메시지 */
    private final String message;

    /** 응답 데이터 */
    private final T data;

    /**
     * 데이터와 함께 성공 응답을 생성합니다.
     *
     * @param data 응답 데이터
     * @return 성공 ApiResponse
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "성공", data);
    }

    /**
     * 메시지와 데이터를 포함한 성공 응답을 생성합니다.
     *
     * @param message 성공 메시지
     * @param data    응답 데이터
     * @return 성공 ApiResponse
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    /**
     * 실패 응답을 생성합니다.
     *
     * @param message 오류 메시지
     * @return 실패 ApiResponse
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
