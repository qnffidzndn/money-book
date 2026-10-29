package com.moneybook.domain;

/**
 * 자산 유형
 * 현금(CASH), 주식(STOCK), 부동산(REAL_ESTATE), 기타(ETC)를 구분합니다.
 */
public enum AssetCategory {

    /** 현금 및 예금 */
    CASH,

    /** 주식 및 펀드 */
    STOCK,

    /** 부동산 */
    REAL_ESTATE,

    /** 기타 자산 */
    ETC
}
