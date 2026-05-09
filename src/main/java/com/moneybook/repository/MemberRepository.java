package com.moneybook.repository;

import com.moneybook.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 구성원 레포지토리
 * 구성원 데이터에 대한 조회 및 저장 기능을 제공합니다.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 이름으로 구성원을 조회합니다.
     *
     * @param name 조회할 구성원 이름
     * @return 해당 이름의 구성원 (없으면 empty)
     */
    Optional<Member> findByName(String name);
}
