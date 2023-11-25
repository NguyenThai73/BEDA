package com.be.common_api;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "thanh_vien_hoi_dong")
@Getter
@Setter
@DynamicUpdate
@Where(clause = "deleted=false")
public class ThanhVienHoiDong extends BaseEntity{
    @Column(name = "id_nguoi_dung")
    private Long idNguoiDung;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_nguoi_dung",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private NguoiDung nguoiDung;
    @Column(name = "id_hoi_dong")
    private Long idHoiDong;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_hoi_dong",referencedColumnName="id", nullable = false, insertable = false, updatable = false)
    private HoiDong hoiDong;
}
