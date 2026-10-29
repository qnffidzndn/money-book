package com.moneybook.controller;

import com.moneybook.domain.SavingsAccount;
import com.moneybook.domain.SavingsMaster;
import com.moneybook.dto.request.SavingsAccountSaveRequest;
import com.moneybook.dto.request.SavingsAccountUpdateRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.SavingsAccountResponse;
import com.moneybook.service.SavingsAccountService;
import com.moneybook.service.SavingsMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 적금 월별 잔액 컨트롤러
 * 적금 마스터별 월별 잔액 조회, 생성, 수정 REST API를 제공합니다.
 */
@Tag(name = "적금 잔액", description = "적금 마스터별 월별 잔액 관리 API")
@RestController
@RequestMapping("/api/savings-accounts")
@RequiredArgsConstructor
public class SavingsAccountController {

    private final SavingsAccountService savingsAccountService;
    private final SavingsMasterService savingsMasterService;

    @Operation(summary = "전체 적금 잔액 목록 조회", description = "전체 적금 월별 잔액 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<SavingsAccountResponse>> getAllSavingsAccounts() {
        List<SavingsAccountResponse> response = savingsAccountService.findAll().stream()
                .map(SavingsAccountResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "적금 마스터별 잔액 목록 조회", description = "특정 적금 마스터의 월별 잔액 목록을 조회합니다.")
    @GetMapping(params = "savingsMasterId")
    public ApiResponse<List<SavingsAccountResponse>> getSavingsAccountsByMaster(@RequestParam Long savingsMasterId) {
        List<SavingsAccountResponse> response = savingsAccountService.findBySavingsMaster(savingsMasterId).stream()
                .map(SavingsAccountResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "적금 잔액 등록", description = "특정 적금 마스터의 월별 잔액을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SavingsAccountResponse> createSavingsAccount(
            @Valid @RequestBody SavingsAccountSaveRequest request) {
        SavingsMaster savingsMaster = savingsMasterService.findById(request.getSavingsMasterId());
        SavingsAccount account = SavingsAccount.builder()
                .savingsMaster(savingsMaster)
                .yearMonth(request.getYm())
                .paymentAmount(request.getPaymentAmount())
                .build();
        return ApiResponse.success("적금 잔액이 등록되었습니다.",
                SavingsAccountResponse.from(savingsAccountService.save(account)));
    }

    @Operation(summary = "적금 잔액 수정", description = "적금 월별 잔액의 연월과 금액을 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<SavingsAccountResponse> updateSavingsAccount(
            @PathVariable Long id,
            @Valid @RequestBody SavingsAccountUpdateRequest request) {
        SavingsAccount updated = savingsAccountService.update(id, request.getYm(), request.getPaymentAmount());
        return ApiResponse.success(SavingsAccountResponse.from(updated));
    }

    @Operation(summary = "적금 잔액 삭제", description = "적금 월별 잔액을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSavingsAccount(@PathVariable Long id) {
        savingsAccountService.delete(id);
        return ApiResponse.success("적금 잔액이 삭제되었습니다.", null);
    }
}
