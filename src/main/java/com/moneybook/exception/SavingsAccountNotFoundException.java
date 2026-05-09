package com.moneybook.exception;

/**
 * 적금 상품을 찾을 수 없을 때 발생하는 예외
 */
public class SavingsAccountNotFoundException extends BusinessException {

    public SavingsAccountNotFoundException() {
        super(ErrorCode.SAVINGS_ACCOUNT_NOT_FOUND);
    }

    public SavingsAccountNotFoundException(Long id) {
        super(ErrorCode.SAVINGS_ACCOUNT_NOT_FOUND, "해당 적금을 찾을 수 없습니다. id=" + id);
    }
}
