package com.moneybook.exception;

/**
 * 수입 내역을 찾을 수 없을 때 발생하는 예외
 */
public class IncomeNotFoundException extends BusinessException {

    public IncomeNotFoundException() {
        super(ErrorCode.INCOME_NOT_FOUND);
    }

    public IncomeNotFoundException(Long id) {
        super(ErrorCode.INCOME_NOT_FOUND, "해당 수입 내역을 찾을 수 없습니다. id=" + id);
    }
}
