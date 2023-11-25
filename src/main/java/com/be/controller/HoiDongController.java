package com.be.controller;

import com.be.common_api.HoiDong;
import com.be.common_api.LopHoc;
import com.be.dto.HoiDongDto;
import com.be.mapper.HoiDongMapper;
import com.be.service.HoiDongService;
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

@RequestMapping("/api/hoi-dong")
@RestController
@Slf4j
@Api("hoi-dong")
public class HoiDongController {
    private final HoiDongService hoiDongService;

    public HoiDongController(HoiDongService hoiDongService) {
        this.hoiDongService = hoiDongService;
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody @Validated HoiDongDto hoiDongDto) {
        try {
            hoiDongService.save(hoiDongDto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HoiDongDto> findById(@PathVariable("id") Long id) {
        HoiDongDto hoiDong = hoiDongService.findById(id);
        return ResponseEntity.ok(hoiDong);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
       try {
           HoiDongDto hoiDong = hoiDongService.findById(id);
           hoiDong.setDeleted(true);
           hoiDongService.update(hoiDong, id);
           return ResponseEntity.ok(true);
       }catch (Exception e){
           return ResponseEntity.ok(false);
       }
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<HoiDongDto>> pageQuery(@Filter Specification<HoiDong> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 1000) Pageable pageable) {
        Page<HoiDongDto> hoiDongPage = hoiDongService.findByCondition(spec, pageable);
        return ResponseEntity.ok(hoiDongPage);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Boolean> update(@RequestBody @Validated HoiDongDto hoiDongDto, @PathVariable("id") Long id) {
      try {
          hoiDongDto.setId(id);
          hoiDongService.update(hoiDongDto, id);
          return ResponseEntity.ok(true);
      }catch (Exception e){
          return ResponseEntity.ok(false);
      }
    }
}