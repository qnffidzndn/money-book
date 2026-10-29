package com.moneybook.controller;

import com.moneybook.domain.BankMaster;
import com.moneybook.domain.Member;
import com.moneybook.domain.SavingsMaster;
import com.moneybook.dto.request.SavingsMasterSaveRequest;
import com.moneybook.dto.request.SavingsMasterUpdateRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.SavingsMasterResponse;
import com.moneybook.service.BankMasterService;
import com.moneybook.service.MemberService;
import com.moneybook.service.SavingsMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 적금 마스터 컨트롤러
 * 적금 상품 마스터 정보 조회, 생성, 수정, 삭제 REST API를 제공합니다.
 */
@Tag(name = "적금 마스터", description = "구성원별 적금 상품 마스터 관리 API")
@RestController
@RequestMapping("/api/savings-master")
@RequiredArgsConstructor
public class SavingsMasterController {

    private final SavingsMasterService savingsMasterService;
    private final BankMasterService bankMasterService;
    private final MemberService memberService;

    @Operation(summary = "적금 마스터 목록 조회", description = "특정 구성원의 전체 적금 마스터 목록을 조회합니다.")
    @GetMapping(params = "memberId")
    public ApiResponse<List<SavingsMasterResponse>> getSavingsMastersByMember(@RequestParam Long memberId) {
        List<SavingsMasterResponse> response = savingsMasterService.findByMember(memberId).stream()
                .map(SavingsMasterResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "전체 적금 마스터 목록 조회", description = "전체 적금 마스터 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<SavingsMasterResponse>> getAllSavingsMasters() {
        List<SavingsMasterResponse> response = savingsMasterService.findAll().stream()
                .map(SavingsMasterResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "적금 마스터 생성", description = "새 적금 상품 마스터를 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SavingsMasterResponse> createSavingsMaster(
            @Valid @RequestBody SavingsMasterSaveRequest request) {
        Member member = memberService.findById(request.getMemberId());
        BankMaster bankMaster = bankMasterService.findById(request.getBankId());
        SavingsMaster savingsMaster = SavingsMaster.builder()
                .name(request.getName())
                .bankMaster(bankMaster)
                .monthlyAmount(request.getMonthlyAmount())
                .startDate(request.getStartDate())
                .maturityDate(request.getMaturityDate())
                .interestRate(request.getInterestRate())
                .expectedInterest(request.getExpectedInterest())
                .actualInterest(request.getActualInterest())
                .member(member)
                .build();
        return ApiResponse.success("적금 마스터가 생성되었습니다.",
                SavingsMasterResponse.from(savingsMasterService.save(savingsMaster)));
    }

    @Operation(summary = "적금 마스터 수정", description = "적금 상품의 이름, 은행, 월 납입액, 만기일, 금리를 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<SavingsMasterResponse> updateSavingsMaster(
            @PathVariable Long id,
            @Valid @RequestBody SavingsMasterUpdateRequest request) {
        BankMaster bankMaster = bankMasterService.findById(request.getBankId());
        SavingsMaster updated = savingsMasterService.update(
                id, request.getName(), bankMaster,
                request.getMonthlyAmount(), request.getStartDate(), request.getMaturityDate(), request.getInterestRate(),
                request.getExpectedInterest(), request.getActualInterest());
        return ApiResponse.success(SavingsMasterResponse.from(updated));
    }

    @Operation(summary = "적금 마스터 삭제", description = "적금 마스터를 삭제합니다. 연관된 월별 잔액 내역도 함께 삭제됩니다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSavingsMaster(@PathVariable Long id) {
        savingsMasterService.delete(id);
        return ApiResponse.success("적금 마스터가 삭제되었습니다.", null);
    }
}
