package com.moneybook.dto.response;

import com.moneybook.domain.AssetTypeMaster;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 자산 유형 마스터 응답 DTO
 */
@Getter
@Builder
public class AssetTypeMasterResponse {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

    public static AssetTypeMasterResponse from(AssetTypeMaster assetType) {
        return AssetTypeMasterResponse.builder()
                .id(assetType.getId())
                .name(assetType.getName())
                .createdAt(assetType.getCreatedAt())
                .build();
    }
}
