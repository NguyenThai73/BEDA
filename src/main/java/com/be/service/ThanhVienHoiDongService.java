package com.be.service;

import com.be.common_api.ThanhVienHoiDong;
import com.be.dto.ThanhVienHoiDongDto;
import com.be.handler.Utils;
import com.be.mapper.ThanhVienHoiDongMapper;
import com.be.repository.ThanhVienHoiDongRepository;
import com.llq.springfilter.boot.Filter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ThanhVienHoiDongService {
    private final ThanhVienHoiDongRepository repository;
    private final ThanhVienHoiDongMapper thanhVienHoiDongMapper;

    public ThanhVienHoiDongService(ThanhVienHoiDongRepository repository, ThanhVienHoiDongMapper thanhVienHoiDongMapper) {
        this.repository = repository;
        this.thanhVienHoiDongMapper = thanhVienHoiDongMapper;
    }

    public ThanhVienHoiDongDto save(ThanhVienHoiDongDto thanhVienHoiDongDto) {
        ThanhVienHoiDong entity = thanhVienHoiDongMapper.toEntity(thanhVienHoiDongDto);
        return thanhVienHoiDongMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ThanhVienHoiDongDto findById(Long id) {
        return thanhVienHoiDongMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<ThanhVienHoiDongDto> findByCondition(@Filter Specification<ThanhVienHoiDong> spec, Pageable pageable) {
        Page<ThanhVienHoiDong> entityPage = repository.findAll(spec, pageable);
        List<ThanhVienHoiDong> entities = entityPage.getContent();
        return new PageImpl<>(thanhVienHoiDongMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ThanhVienHoiDongDto update(ThanhVienHoiDongDto thanhVienHoiDongDto, Long id) {
        ThanhVienHoiDongDto data = findById(id);
        ThanhVienHoiDong entity = thanhVienHoiDongMapper.toEntity(thanhVienHoiDongDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(thanhVienHoiDongMapper.toDto(entity));
    }
}