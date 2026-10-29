package com.moneybook.service;

import com.moneybook.domain.BankMaster;
import com.moneybook.domain.SavingsMaster;
import com.moneybook.exception.SavingsMasterNotFoundException;
import com.moneybook.repository.SavingsMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 적금 마스터 서비스
 * 적금 상품 마스터 정보의 조회, 저장, 수정, 삭제 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SavingsMasterService {

    private final SavingsMasterRepository savingsMasterRepository;

    /**
     * 전체 적금 마스터 목록을 조회합니다.
     *
     * @return 전체 적금 마스터 목록
     */
    public List<SavingsMaster> findAll() {
        return savingsMasterRepository.findAll();
    }

    /**
     * 특정 구성원의 적금 마스터 목록을 조회합니다.
     *
     * @param memberId 구성원 ID
     * @return 해당 구성원의 적금 마스터 목록
     */
    public List<SavingsMaster> findByMember(Long memberId) {
        return savingsMasterRepository.findByMemberId(memberId);
    }

    /**
     * ID로 적금 마스터를 조회합니다.
     *
     * @param id 적금 마스터 ID
     * @return 해당 적금 마스터
     * @throws SavingsMasterNotFoundException 적금 마스터가 존재하지 않을 경우
     */
    public SavingsMaster findById(Long id) {
        return savingsMasterRepository.findById(id)
                .orElseThrow(() -> new SavingsMasterNotFoundException(id));
    }

    /**
     * 적금 마스터를 저장합니다.
     *
     * @param savingsMaster 저장할 적금 마스터 엔티티
     * @return 저장된 적금 마스터
     */
    @Transactional
    public SavingsMaster save(SavingsMaster savingsMaster) {
        return savingsMasterRepository.save(savingsMaster);
    }

    /**
     * 적금 마스터 정보를 수정합니다.
     *
     * @param id            수정할 적금 마스터 ID
     * @param name          수정할 상품명
     * @param bankMaster    수정할 은행
     * @param monthlyAmount 수정할 월 납입액
     * @param startDate     수정할 시작일
     * @param maturityDate  수정할 만기일
     * @param interestRate  수정할 금리
     * @return 수정된 적금 마스터
     * @throws SavingsMasterNotFoundException 적금 마스터가 존재하지 않을 경우
     */
    @Transactional
    public SavingsMaster update(Long id, String name, BankMaster bankMaster, BigDecimal monthlyAmount, LocalDate startDate, LocalDate maturityDate, BigDecimal interestRate, BigDecimal expectedInterest, BigDecimal actualInterest) {
        SavingsMaster savingsMaster = savingsMasterRepository.findById(id)
                .orElseThrow(() -> new SavingsMasterNotFoundException(id));
        savingsMaster.update(name, bankMaster, monthlyAmount, startDate, maturityDate, interestRate, expectedInterest, actualInterest);
        return savingsMaster;
    }

    /**
     * 적금 마스터를 삭제합니다.
     *
     * @param id 삭제할 적금 마스터 ID
     * @throws SavingsMasterNotFoundException 적금 마스터가 존재하지 않을 경우
     */
    @Transactional
    public void delete(Long id) {
        if (!savingsMasterRepository.existsById(id)) {
            throw new SavingsMasterNotFoundException(id);
        }
        savingsMasterRepository.deleteById(id);
    }
}
