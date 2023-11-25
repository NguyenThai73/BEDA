package com.be.service;

import com.be.common_api.LopHoc;
import com.be.dto.LopHocDto;
import com.be.handler.Utils;
import com.be.mapper.LopHocMapper;
import com.be.repository.LopHocRepository;
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
public class LopHocService {
    private final LopHocRepository repository;
    private final LopHocMapper lopHocMapper;

    public LopHocService(LopHocRepository repository, LopHocMapper lopHocMapper) {
        this.repository = repository;
        this.lopHocMapper = lopHocMapper;
    }

    public LopHocDto save(LopHocDto lopHocDto) {
        LopHoc entity = lopHocMapper.toEntity(lopHocDto);
        return lopHocMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public LopHocDto findById(Long id) {
        return lopHocMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<LopHocDto> findByCondition(@Filter Specification<LopHoc> spec, Pageable pageable) {
        Page<LopHoc> entityPage = repository.findAll(spec, pageable);
        List<LopHoc> entities = entityPage.getContent();
        return new PageImpl<>(lopHocMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public LopHocDto update(LopHocDto lopHocDto, Long id) {
        LopHocDto data = findById(id);
        LopHoc entity = lopHocMapper.toEntity(lopHocDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(lopHocMapper.toDto(entity));
    }
}