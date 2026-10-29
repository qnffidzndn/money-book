package com.moneybook.controller;

import com.moneybook.domain.AssetAllocation;
import com.moneybook.domain.AssetTypeMaster;
import com.moneybook.domain.Member;
import com.moneybook.dto.request.AssetAllocationSaveRequest;
import com.moneybook.dto.request.AssetAllocationUpdateRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.AssetAllocationResponse;
import com.moneybook.service.AssetAllocationService;
import com.moneybook.service.AssetTypeMasterService;
import com.moneybook.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 자산 배분 컨트롤러
 * 구성원별 자산 항목 조회, 생성, 수정, 삭제 REST API를 제공합니다.
 */
@Tag(name = "자산 배분", description = "구성원별 자산 항목 관리 API")
@RestController
@RequestMapping("/api/asset-allocations")
@RequiredArgsConstructor
public class AssetAllocationController {

    private final AssetAllocationService assetAllocationService;
    private final AssetTypeMasterService assetTypeMasterService;
    private final MemberService memberService;

    @Operation(summary = "구성원별 자산 목록 조회", description = "특정 구성원의 전체 자산 항목을 조회합니다.")
    @GetMapping(params = "memberId")
    public ApiResponse<List<AssetAllocationResponse>> getAssetsByMember(@RequestParam Long memberId) {
        List<AssetAllocationResponse> response = assetAllocationService.findByMember(memberId).stream()
                .map(AssetAllocationResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "자산 항목 생성", description = "구성원의 자산 항목을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AssetAllocationResponse> createAsset(@Valid @RequestBody AssetAllocationSaveRequest request) {
        Member member = memberService.findById(request.getMemberId());
        AssetTypeMaster assetType = assetTypeMasterService.findById(request.getAssetTypeId());
        AssetAllocation asset = AssetAllocation.builder()
                .assetDate(request.getAssetDate())
                .assetType(assetType)
                .amount(request.getAmount())
                .member(member)
                .description(request.getDescription())
                .build();
        return ApiResponse.success("자산 항목이 등록되었습니다.",
                AssetAllocationResponse.from(assetAllocationService.save(asset)));
    }

    @Operation(summary = "자산 항목 수정", description = "자산 항목의 기준일자, 유형, 금액, 설명을 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<AssetAllocationResponse> updateAsset(
            @PathVariable Long id,
            @Valid @RequestBody AssetAllocationUpdateRequest request) {
        AssetTypeMaster assetType = assetTypeMasterService.findById(request.getAssetTypeId());
        AssetAllocation updated = assetAllocationService.update(
                id, request.getAssetDate(), assetType, request.getAmount(), request.getDescription());
        return ApiResponse.success(AssetAllocationResponse.from(updated));
    }

    @Operation(summary = "자산 항목 삭제", description = "자산 항목을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAsset(@PathVariable Long id) {
        assetAllocationService.delete(id);
        return ApiResponse.success("자산 항목이 삭제되었습니다.", null);
    }
}
