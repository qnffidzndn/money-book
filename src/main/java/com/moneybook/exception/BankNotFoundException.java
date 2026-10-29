package com.moneybook.exception;

/**
 * 은행 마스터를 찾을 수 없을 때 발생하는 예외
 */
public class BankNotFoundException extends BusinessException {

    public BankNotFoundException() {
        super(ErrorCode.BANK_NOT_FOUND);
    }

    public BankNotFoundException(Long id) {
        super(ErrorCode.BANK_NOT_FOUND, "해당 은행을 찾을 수 없습니다. id=" + id);
    }
}
