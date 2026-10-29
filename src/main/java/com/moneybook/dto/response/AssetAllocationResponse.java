package com.moneybook.dto.response;

import com.moneybook.domain.AssetAllocation;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 자산 배분 응답 DTO
 */
@Getter
@Builder
public class AssetAllocationResponse {

    private Long id;
    private LocalDate assetDate;
    private Long assetTypeId;
    private String assetTypeName;
    private BigDecimal amount;
    private Long memberId;
    private String memberName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AssetAllocationResponse from(AssetAllocation asset) {
        return AssetAllocationResponse.builder()
                .id(asset.getId())
                .assetDate(asset.getAssetDate())
                .assetTypeId(asset.getAssetType().getId())
                .assetTypeName(asset.getAssetType().getName())
                .amount(asset.getAmount())
                .memberId(asset.getMember().getId())
                .memberName(asset.getMember().getName())
                .description(asset.getDescription())
                .createdAt(asset.getCreatedAt())
                .updatedAt(asset.getUpdatedAt())
                .build();
    }
}
