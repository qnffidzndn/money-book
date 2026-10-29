package com.moneybook.repository;

import com.moneybook.domain.BankMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankMasterRepository extends JpaRepository<BankMaster, Long> {
}
