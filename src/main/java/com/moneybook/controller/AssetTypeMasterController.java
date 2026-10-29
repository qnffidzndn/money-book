package com.moneybook.controller;

import com.moneybook.domain.AssetTypeMaster;
import com.moneybook.dto.request.AssetTypeMasterSaveRequest;
import com.moneybook.dto.request.AssetTypeMasterUpdateRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.AssetTypeMasterResponse;
import com.moneybook.service.AssetTypeMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 자산 유형 마스터 컨트롤러
 * 자산 유형 목록 조회, 생성, 수정, 삭제 REST API를 제공합니다.
 */
@Tag(name = "자산 유형 마스터", description = "자산 유형 코드 관리 API")
@RestController
@RequestMapping("/api/asset-types")
@RequiredArgsConstructor
public class AssetTypeMasterController {

    private final AssetTypeMasterService assetTypeMasterService;

    @Operation(summary = "자산 유형 목록 조회", description = "전체 자산 유형 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<AssetTypeMasterResponse>> getAllAssetTypes() {
        List<AssetTypeMasterResponse> response = assetTypeMasterService.findAll().stream()
                .map(AssetTypeMasterResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "자산 유형 등록", description = "새 자산 유형을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AssetTypeMasterResponse> createAssetType(@Valid @RequestBody AssetTypeMasterSaveRequest request) {
        AssetTypeMaster assetType = AssetTypeMaster.builder()
                .name(request.getName())
                .build();
        return ApiResponse.success("자산 유형이 등록되었습니다.",
                AssetTypeMasterResponse.from(assetTypeMasterService.save(assetType)));
    }

    @Operation(summary = "자산 유형 수정", description = "자산 유형의 이름을 수정합니다.")
    @PutMapping("/{id}")
    public ApiResponse<AssetTypeMasterResponse> updateAssetType(
            @PathVariable Long id,
            @Valid @RequestBody AssetTypeMasterUpdateRequest request) {
        AssetTypeMaster updated = assetTypeMasterService.update(id, request.getName());
        return ApiResponse.success(AssetTypeMasterResponse.from(updated));
    }

    @Operation(summary = "자산 유형 삭제", description = "자산 유형을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAssetType(@PathVariable Long id) {
        assetTypeMasterService.delete(id);
        return ApiResponse.success("자산 유형이 삭제되었습니다.", null);
    }
}
