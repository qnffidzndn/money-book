package com.moneybook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 은행 마스터 저장 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankMasterSaveRequest {

    @NotBlank(message = "은행 코드는 필수입니다.")
    @Size(max = 10, message = "은행 코드는 10자 이하여야 합니다.")
    private String code;

    @NotBlank(message = "은행명은 필수입니다.")
    @Size(max = 50, message = "은행명은 50자 이하여야 합니다.")
    private String name;
}
