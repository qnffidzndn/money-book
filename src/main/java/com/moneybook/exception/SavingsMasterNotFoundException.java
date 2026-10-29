package com.moneybook.exception;

/**
 * 적금 마스터를 찾을 수 없을 때 발생하는 예외
 */
public class SavingsMasterNotFoundException extends BusinessException {

    public SavingsMasterNotFoundException() {
        super(ErrorCode.SAVINGS_MASTER_NOT_FOUND);
    }

    public SavingsMasterNotFoundException(Long id) {
        super(ErrorCode.SAVINGS_MASTER_NOT_FOUND, "해당 적금 마스터를 찾을 수 없습니다. id=" + id);
    }
}
