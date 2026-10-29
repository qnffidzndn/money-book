package com.moneybook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 구성원 저장 요청 DTO
 * 구성원 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSaveRequest {

    /** 구성원 이름 */
    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 50, message = "이름은 50자 이하여야 합니다.")
    private String name;

    /** 로그인 아이디 */
    @NotBlank(message = "아이디는 필수입니다.")
    @Size(max = 50, message = "아이디는 50자 이하여야 합니다.")
    private String username;

    /** 비밀번호 */
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
