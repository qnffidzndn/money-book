package com.moneybook.exception;

/**
 * 계획을 찾을 수 없을 때 발생하는 예외
 */
public class PlanNotFoundException extends BusinessException {

    public PlanNotFoundException() {
        super(ErrorCode.PLAN_NOT_FOUND);
    }

    public PlanNotFoundException(Long id) {
        super(ErrorCode.PLAN_NOT_FOUND, "해당 계획을 찾을 수 없습니다. id=" + id);
    }
}
