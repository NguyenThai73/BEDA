package com.be.mapper;

import com.be.common_api.DeTai;
import com.be.dto.DeTaiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeTaiMapper extends EntityMapper<DeTaiDto, DeTai> {
}