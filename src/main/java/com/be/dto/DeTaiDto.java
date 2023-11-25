package com.be.dto;

import com.be.common_api.NguoiDung;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class DeTaiDto extends BaseDto {
    private Long idNguoiDung;
    private NguoiDung nguoiDung;
    @Size(max = 255)
    private String name;
    private String moTa;
    @Size(max = 255)
    private String fileBaoCao;
    private Short point;
    private Short status;

    public DeTaiDto() {
    }
}