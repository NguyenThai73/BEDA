package com.be.service;

import com.be.common_api.HoiDong;
import com.be.dto.HoiDongDto;
import com.be.handler.Utils;
import com.be.mapper.HoiDongMapper;
import com.be.repository.HoiDongRepository;
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
public class HoiDongService {
    private final HoiDongRepository repository;
    private final HoiDongMapper hoiDongMapper;

    public HoiDongService(HoiDongRepository repository, HoiDongMapper hoiDongMapper) {
        this.repository = repository;
        this.hoiDongMapper = hoiDongMapper;
    }

    public HoiDongDto save(HoiDongDto hoiDongDto) {
        HoiDong entity = hoiDongMapper.toEntity(hoiDongDto);
        return hoiDongMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public HoiDongDto findById(Long id) {
        return hoiDongMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<HoiDongDto> findByCondition(@Filter Specification<HoiDong> spec, Pageable pageable) {
        Page<HoiDong> entityPage = repository.findAll(spec, pageable);
        List<HoiDong> entities = entityPage.getContent();
        return new PageImpl<>(hoiDongMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public HoiDongDto update(HoiDongDto hoiDongDto, Long id) {
        HoiDongDto data = findById(id);
        HoiDong entity = hoiDongMapper.toEntity(hoiDongDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(hoiDongMapper.toDto(entity));
    }
}