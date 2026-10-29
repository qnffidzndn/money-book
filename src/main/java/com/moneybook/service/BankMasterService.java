package com.moneybook.service;

import com.moneybook.domain.BankMaster;
import com.moneybook.exception.BankNotFoundException;
import com.moneybook.repository.BankMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 은행 마스터 서비스
 * 은행 코드 및 이름의 조회, 저장 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BankMasterService {

    private final BankMasterRepository bankMasterRepository;

    public List<BankMaster> findAll() {
        return bankMasterRepository.findAll();
    }

    public BankMaster findById(Long id) {
        return bankMasterRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException(id));
    }

    @Transactional
    public BankMaster save(BankMaster bankMaster) {
        return bankMasterRepository.save(bankMaster);
    }
}
