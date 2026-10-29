package com.moneybook.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 은행 마스터 엔티티
 * 은행 코드와 은행명을 관리합니다.
 */
@Entity
@Table(name = "bank_master")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class BankMaster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10, columnDefinition = "VARCHAR(10) COMMENT '은행 코드'")
    private String code;

    @Column(nullable = false, length = 50, columnDefinition = "VARCHAR(50) COMMENT '은행명'")
    private String name;
}
