package com.moneybook.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 자산 배분 저장 요청 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetAllocationSaveRequest {

    @NotNull(message = "구성원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "자산 기준일자는 필수입니다.")
    private LocalDate assetDate;

    @NotNull(message = "자산 유형 ID는 필수입니다.")
    private Long assetTypeId;

    @NotNull(message = "금액은 필수입니다.")
    private BigDecimal amount;

    @Size(max = 255, message = "설명은 255자 이하여야 합니다.")
    private String description;
}
