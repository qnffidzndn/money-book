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
}
