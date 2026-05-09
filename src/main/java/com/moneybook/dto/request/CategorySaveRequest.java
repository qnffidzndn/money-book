package com.moneybook.dto.request;

import com.moneybook.domain.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 저장 요청 DTO
 * 카테고리 생성 API의 요청 데이터를 담습니다.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySaveRequest {

    /** 카테고리 이름 */
    @NotBlank(message = "카테고리 이름은 필수입니다.")
    @Size(max = 50, message = "카테고리 이름은 50자 이하여야 합니다.")
    private String name;

    /** 카테고리 유형 (EXPENSE: 지출, SAVING: 저축) */
    @NotNull(message = "카테고리 유형은 필수입니다.")
    private CategoryType type;
}
