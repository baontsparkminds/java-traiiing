package com.example.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.enums.Capacity;
import com.example.demo.entity.enums.EmploymentMode;

import lombok.Data;

@Entity
@Table
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private EmploymentMode employmentMode;

    @Column
    @Enumerated(EnumType.STRING)
    private Capacity capacity;

    @Column
    private BigDecimal durationInMonths;

    @Column
    private Integer startYear;

    @Column
    private String role;

    @Column
    private Integer teamSize;

    @Column
    private String repositoryUrl;

    @Column
    private String liveUrl;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "fk_applicant_id", referencedColumnName = "id", insertable = true, updatable = false)
    private Applicant applicant;

}