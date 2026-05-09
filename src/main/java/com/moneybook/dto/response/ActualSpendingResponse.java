package com.moneybook.dto.response;

import com.moneybook.domain.ActualSpending;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 실제 사용 내역 응답 DTO
 * 실제 사용 내역 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class ActualSpendingResponse {

    /** 실제 사용 내역 고유 식별자 */
    private Long id;

    /** 계획 ID */
    private Long planId;

    /** 실제 사용 금액 */
    private BigDecimal actualAmount;

    /** 메모 */
    private String memo;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * ActualSpending 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param spending 변환할 ActualSpending 엔티티
     * @return ActualSpendingResponse DTO
     */
    public static ActualSpendingResponse from(ActualSpending spending) {
        return ActualSpendingResponse.builder()
                .id(spending.getId())
                .planId(spending.getPlan().getId())
                .actualAmount(spending.getActualAmount())
                .memo(spending.getMemo())
                .createdAt(spending.getCreatedAt())
                .build();
    }
}
