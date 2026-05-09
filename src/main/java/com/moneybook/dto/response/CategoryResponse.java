package com.moneybook.dto.response;

import com.moneybook.domain.Category;
import com.moneybook.domain.CategoryType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 카테고리 응답 DTO
 * 카테고리 조회 API의 응답 데이터를 담습니다.
 */
@Getter
@Builder
public class CategoryResponse {

    /** 카테고리 고유 식별자 */
    private Long id;

    /** 카테고리 이름 */
    private String name;

    /** 카테고리 유형 (EXPENSE: 지출, SAVING: 저축) */
    private CategoryType type;

    /** 데이터 생성 일시 */
    private LocalDateTime createdAt;

    /**
     * Category 엔티티로부터 응답 DTO를 생성합니다.
     *
     * @param category 변환할 Category 엔티티
     * @return CategoryResponse DTO
     */
    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .createdAt(category.getCreatedAt())
                .build();
    }
}
