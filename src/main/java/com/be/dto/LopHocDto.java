package com.be.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@ApiModel()
@Getter
@Setter
public class LopHocDto extends BaseDto {
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String moTa;
    private Short status;

    public LopHocDto() {
    }
}