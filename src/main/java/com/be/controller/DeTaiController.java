package com.be.controller;

import com.be.common_api.DeTai;
import com.be.dto.DeTaiDto;
import com.be.mapper.DeTaiMapper;
import com.be.service.DeTaiService;
import com.llq.springfilter.boot.Filter;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/de-tai")
@RestController
@Slf4j
@Api("de-tai")
public class DeTaiController {
    private final DeTaiService deTaiService;

    public DeTaiController(DeTaiService deTaiService) {
        this.deTaiService = deTaiService;
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody @Validated DeTaiDto deTaiDto) {
       try {
           deTaiService.save(deTaiDto);
           return ResponseEntity.ok(true);
       }catch (Exception e){
           return ResponseEntity.ok(false);
       }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DeTaiDto> findById(@PathVariable("id") Long id) {
        DeTaiDto deTai = deTaiService.findById(id);
        return ResponseEntity.ok(deTai);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
       try {
           deTaiService.deleteById(id);
           return ResponseEntity.ok(true);
       }catch (Exception e){
           return ResponseEntity.ok(false);
       }
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<DeTaiDto>> pageQuery(@Filter Specification<DeTai> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 1000) Pageable pageable) {
        Page<DeTaiDto> deTaiPage = deTaiService.findByCondition(spec, pageable);
        return ResponseEntity.ok(deTaiPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Boolean> update(@RequestBody @Validated DeTaiDto deTaiDto, @PathVariable("id") Long id) {
        try{
            deTaiDto.setId(id);
            deTaiService.update(deTaiDto, id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }

    }
}