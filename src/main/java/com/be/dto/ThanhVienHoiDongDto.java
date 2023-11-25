package com.be.dto;

import com.be.common_api.HoiDong;
import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel()
@Getter
@Setter
public class ThanhVienHoiDongDto extends BaseDto {
    private Long idNguoiDung;
    private NguoiDung nguoiDung;
    private Long idHoiDong;
    private HoiDong hoiDong;

    public ThanhVienHoiDongDto() {
    }
}