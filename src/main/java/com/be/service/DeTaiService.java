package com.be.service;

import com.be.common_api.DeTai;
import com.be.dto.DeTaiDto;
import com.be.handler.Utils;
import com.be.mapper.DeTaiMapper;
import com.be.repository.DeTaiRepository;
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
public class DeTaiService {
    private final DeTaiRepository repository;
    private final DeTaiMapper deTaiMapper;

    public DeTaiService(DeTaiRepository repository, DeTaiMapper deTaiMapper) {
        this.repository = repository;
        this.deTaiMapper = deTaiMapper;
    }

    public DeTaiDto save(DeTaiDto deTaiDto) {
        DeTai entity = deTaiMapper.toEntity(deTaiDto);
        return deTaiMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DeTaiDto findById(Long id) {
        return deTaiMapper.toDto(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Item Not Found! ID: " + id)
        ));
    }

    public Page<DeTaiDto> findByCondition(@Filter Specification<DeTai> spec, Pageable pageable) {
        Page<DeTai> entityPage = repository.findAll(spec, pageable);
        List<DeTai> entities = entityPage.getContent();
        return new PageImpl<>(deTaiMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DeTaiDto update(DeTaiDto deTaiDto, Long id) {
        DeTaiDto data = findById(id);
        DeTai entity = deTaiMapper.toEntity(deTaiDto);
        BeanUtils.copyProperties(entity, data, Utils.getNullPropertyNames(entity));
        return save(deTaiMapper.toDto(entity));
    }
}