package com.moneybook.controller;

import com.moneybook.domain.SavingsAccount;
import com.moneybook.domain.SavingsTransfer;
import com.moneybook.dto.request.SavingsTransferSaveRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.SavingsTransferResponse;
import com.moneybook.service.SavingsAccountService;
import com.moneybook.service.SavingsTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 적금 만기 자금 이동 컨트롤러
 * 적금 자금 이동 내역 조회 및 생성 REST API를 제공합니다.
 */
@Tag(name = "적금 자금 이동", description = "적금 만기 후 자금 이동 내역 관리 API")
@RestController
@RequestMapping("/api/savings-transfers")
@RequiredArgsConstructor
public class SavingsTransferController {

    private final SavingsTransferService savingsTransferService;
    private final SavingsAccountService savingsAccountService;

    @Operation(summary = "자금 이동 내역 조회", description = "특정 적금 상품의 만기 자금 이동 내역을 조회합니다.")
    @GetMapping
    public ApiResponse<List<SavingsTransferResponse>> getTransfers(@RequestParam Long accountId) {
        List<SavingsTransferResponse> response = savingsTransferService.findByAccount(accountId).stream()
                .map(SavingsTransferResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "자금 이동 내역 생성", description = "적금 만기 후 자금 이동 내역을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SavingsTransferResponse> createTransfer(@Valid @RequestBody SavingsTransferSaveRequest request) {
        SavingsAccount account = savingsAccountService.findById(request.getSavingsAccountId());
        SavingsTransfer transfer = SavingsTransfer.builder()
                .savingsAccount(account)
                .transferDate(request.getTransferDate())
                .amount(request.getAmount())
                .toAccount(request.getToAccount())
                .build();
        return ApiResponse.success("자금 이동 내역이 생성되었습니다.",
                SavingsTransferResponse.from(savingsTransferService.save(transfer)));
    }
}
