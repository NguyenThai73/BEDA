package com.be.mapper;

import com.be.common_api.ThanhVienHoiDong;
import com.be.dto.ThanhVienHoiDongDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThanhVienHoiDongMapper extends EntityMapper<ThanhVienHoiDongDto, ThanhVienHoiDong> {
}