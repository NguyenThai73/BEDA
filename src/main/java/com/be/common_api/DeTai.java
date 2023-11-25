package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "de_tai")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class DeTai extends BaseEntity{
    @Column(name = "id_nguoi_dung")
    private Long idNguoiDung;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_nguoi_dung",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    @Column(name = "name")
    private String name;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "file_bao_cao")
    private String fileBaoCao;
    @Column(name = "point")
    private Short point;
    @Column(name = "status")
    private Short status;
}
