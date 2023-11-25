package com.be.controller;

import com.be.common_api.LopHoc;
import com.be.dto.LopHocDto;
import com.be.mapper.LopHocMapper;
import com.be.service.LopHocService;
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

@RequestMapping("/api/lop-hoc")
@RestController
@Slf4j
@Api("lop-hoc")
public class LopHocController {
    private final LopHocService lopHocService;

    public LopHocController(LopHocService lopHocService) {
        this.lopHocService = lopHocService;
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody @Validated LopHocDto lopHocDto) {
      try {
          lopHocService.save(lopHocDto);
          return ResponseEntity.ok(true);
      }catch (Exception e){
          return ResponseEntity.ok(false);
      }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LopHocDto> findById(@PathVariable("id") Long id) {
        LopHocDto lopHoc = lopHocService.findById(id);
        return ResponseEntity.ok(lopHoc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        try{
            LopHocDto lopHoc = lopHocService.findById(id);
            lopHoc.setDeleted(true);
            lopHocService.update(lopHoc, id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<LopHocDto>> pageQuery(@Filter Specification<LopHoc> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 1000) Pageable pageable) {
        Page<LopHocDto> lopHocPage = lopHocService.findByCondition(spec, pageable);
        return ResponseEntity.ok(lopHocPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Boolean> update(@RequestBody @Validated LopHocDto lopHocDto, @PathVariable("id") Long id) {
        try{
            lopHocDto.setId(id);
            lopHocService.update(lopHocDto, id);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }
}