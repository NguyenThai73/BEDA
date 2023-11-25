package com.be.common_api;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "lop_hoc")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class LopHoc extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "status")
    private Short status;
}
