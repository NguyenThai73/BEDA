package com.be.controller;

import com.be.common_api.ThanhVienHoiDong;
import com.be.dto.ThanhVienHoiDongDto;
import com.be.mapper.ThanhVienHoiDongMapper;
import com.be.service.ThanhVienHoiDongService;
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

@RequestMapping("/api/thanh-vien-hoi-dong")
@RestController
@Slf4j
@Api("thanh-vien-hoi-dong")
public class ThanhVienHoiDongController {
    private final ThanhVienHoiDongService thanhVienHoiDongService;

    public ThanhVienHoiDongController(ThanhVienHoiDongService thanhVienHoiDongService) {
        this.thanhVienHoiDongService = thanhVienHoiDongService;
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> save(@RequestBody @Validated ThanhVienHoiDongDto thanhVienHoiDongDto) {
        thanhVienHoiDongService.save(thanhVienHoiDongDto);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ThanhVienHoiDongDto> findById(@PathVariable("id") Long id) {
        ThanhVienHoiDongDto thanhVienHoiDong = thanhVienHoiDongService.findById(id);
        return ResponseEntity.ok(thanhVienHoiDong);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        thanhVienHoiDongService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<ThanhVienHoiDongDto>> pageQuery(@Filter Specification<ThanhVienHoiDong> spec, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 1000) Pageable pageable) {
        Page<ThanhVienHoiDongDto> thanhVienHoiDongPage = thanhVienHoiDongService.findByCondition(spec, pageable);
        return ResponseEntity.ok(thanhVienHoiDongPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ThanhVienHoiDongDto thanhVienHoiDongDto, @PathVariable("id") Long id) {
        thanhVienHoiDongDto.setId(id);
        thanhVienHoiDongService.update(thanhVienHoiDongDto, id);
        return ResponseEntity.ok().build();
    }
}