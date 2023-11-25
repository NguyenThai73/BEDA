package com.be.mapper;

import com.be.common_api.HoiDong;
import com.be.dto.HoiDongDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HoiDongMapper extends EntityMapper<HoiDongDto, HoiDong> {
}