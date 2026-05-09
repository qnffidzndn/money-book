package com.moneybook.dto.response;

import com.moneybook.domain.AssetSnapshot;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 월별 총자산 스냅샷 응답 DTO
 * 총자산 스냅샷 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class AssetSnapshotResponse {

    /** 총자산 스냅샷 고유 식별자 */
    private Long id;

    /** 연월 (형식: yyyy-MM) */
    private String yearMonth;

    /** 총자산 금액 */
    private BigDecimal totalAmount;

    /** 메모 */
    private String memo;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * AssetSnapshot 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param snapshot 변환할 AssetSnapshot 엔티티
     * @return AssetSnapshotResponse DTO
     */
    public static AssetSnapshotResponse from(AssetSnapshot snapshot) {
        return AssetSnapshotResponse.builder()
                .id(snapshot.getId())
                .yearMonth(snapshot.getYearMonth())
                .totalAmount(snapshot.getTotalAmount())
                .memo(snapshot.getMemo())
                .createdAt(snapshot.getCreatedAt())
                .build();
    }
}
