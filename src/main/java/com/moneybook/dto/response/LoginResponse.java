package com.moneybook.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 로그인 응답 DTO
 */
@Getter
@Builder
public class LoginResponse {

    private String token;
    private Long memberId;
    private String memberName;
}
