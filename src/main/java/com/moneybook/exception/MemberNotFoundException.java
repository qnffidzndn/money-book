package com.moneybook.exception;

/**
 * 구성원을 찾을 수 없을 때 발생하는 예외
 */
public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }

    public MemberNotFoundException(Long id) {
        super(ErrorCode.MEMBER_NOT_FOUND, "해당 멤버를 찾을 수 없습니다. id=" + id);
    }
}
