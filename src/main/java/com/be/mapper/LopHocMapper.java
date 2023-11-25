package com.be.mapper;

import com.be.common_api.LopHoc;
import com.be.dto.LopHocDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LopHocMapper extends EntityMapper<LopHocDto, LopHoc> {
}