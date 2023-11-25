package com.be.repository;

import com.be.common_api.HoiDong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HoiDongRepository extends JpaRepository<HoiDong, Long>, JpaSpecificationExecutor<HoiDong> {
}