package com.moneybook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 자산 유형 마스터 저장 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetTypeMasterSaveRequest {

    @NotBlank(message = "유형명은 필수입니다.")
    @Size(max = 100, message = "유형명은 100자 이하여야 합니다.")
    private String name;
}
