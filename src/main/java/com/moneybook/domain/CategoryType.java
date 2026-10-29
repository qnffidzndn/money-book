package com.moneybook.domain;

/**
 * 카테고리 유형
 * 지출(EXPENSE), 저축(SAVINGS), 예금(DEPOSIT)을 구분합니다.
 */
public enum CategoryType {

    /** 지출 항목 (식비, 교통비, 쇼핑 등) */
    EXPENSE,

    /** 저축 항목 (적금 등) */
    SAVINGS,

    /** 예금 항목 */
    DEPOSIT
}
