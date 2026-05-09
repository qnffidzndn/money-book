package com.moneybook.exception;

/**
 * 카테고리를 찾을 수 없을 때 발생하는 예외
 */
public class CategoryNotFoundException extends BusinessException {

    public CategoryNotFoundException() {
        super(ErrorCode.CATEGORY_NOT_FOUND);
    }

    public CategoryNotFoundException(Long id) {
        super(ErrorCode.CATEGORY_NOT_FOUND, "해당 카테고리를 찾을 수 없습니다. id=" + id);
    }
}
