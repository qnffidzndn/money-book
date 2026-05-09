package com.moneybook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 월별 총자산 스냅샷 저장 요청 DTO
 * 총자산 스냅샷 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetSnapshotSaveRequest {

    /** 연월 (형식: yyyy-MM) */
    @NotBlank(message = "연월은 필수입니다.")
    private String yearMonth;

    /** 총자산 금액 */
    @NotNull(message = "총자산 금액은 필수입니다.")
    @Positive(message = "총자산 금액은 0보다 커야 합니다.")
    private BigDecimal totalAmount;

    /** 메모 */
    @Size(max = 255, message = "메모는 255자 이하여야 합니다.")
    private String memo;
}
