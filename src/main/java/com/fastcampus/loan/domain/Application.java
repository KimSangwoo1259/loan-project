package com.fastcampus.loan.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Where(clause = "is_deleted=false")
public class Application extends BaseEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long counselId;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cellPhone;

    @Column
    private String email;

    @Column
    private BigDecimal interestRate;

    @Column
    private BigDecimal fee;

    @Column
    private LocalDateTime maturity;

    @Column
    private BigDecimal hopeAmount;

    @Column(nullable = false)
    private LocalDateTime appliedAt;
}
