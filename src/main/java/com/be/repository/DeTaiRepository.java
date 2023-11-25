package com.be.repository;

import com.be.common_api.DeTai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeTaiRepository extends JpaRepository<DeTai, Long>, JpaSpecificationExecutor<DeTai> {
}