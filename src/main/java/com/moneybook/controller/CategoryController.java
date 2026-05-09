package com.moneybook.controller;

import com.moneybook.domain.Category;
import com.moneybook.domain.CategoryType;
import com.moneybook.dto.request.CategorySaveRequest;
import com.moneybook.dto.response.ApiResponse;
import com.moneybook.dto.response.CategoryResponse;
import com.moneybook.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 카테고리 컨트롤러
 * 카테고리 조회 및 생성 REST API를 제공합니다.
 */
@Tag(name = "카테고리", description = "지출/저축 카테고리 관리 API")
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "전체 카테고리 조회", description = "등록된 전체 카테고리 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<CategoryResponse>> getCategories() {
        List<CategoryResponse> response = categoryService.findAll().stream()
                .map(CategoryResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "유형별 카테고리 조회", description = "EXPENSE(지출) 또는 SAVING(저축) 유형으로 카테고리를 조회합니다.")
    @GetMapping(params = "type")
    public ApiResponse<List<CategoryResponse>> getCategoriesByType(@RequestParam CategoryType type) {
        List<CategoryResponse> response = categoryService.findByType(type).stream()
                .map(CategoryResponse::from)
                .toList();
        return ApiResponse.success(response);
    }

    @Operation(summary = "카테고리 생성", description = "새 카테고리를 생성합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CategorySaveRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .type(request.getType())
                .build();
        return ApiResponse.success("카테고리가 생성되었습니다.", CategoryResponse.from(categoryService.save(category)));
    }
}
