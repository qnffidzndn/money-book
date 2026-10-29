package com.moneybook.dto.response;

import com.moneybook.domain.BankMaster;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 은행 마스터 응답 DTO
 */
@Getter
@Builder
public class BankMasterResponse {

    private Long id;
    private String code;
    private String name;
    private LocalDateTime createdAt;

    public static BankMasterResponse from(BankMaster bank) {
        return BankMasterResponse.builder()
                .id(bank.getId())
                .code(bank.getCode())
                .name(bank.getName())
                .createdAt(bank.getCreatedAt())
                .build();
    }
}
