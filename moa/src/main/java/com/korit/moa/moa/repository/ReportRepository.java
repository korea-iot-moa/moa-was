package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.Report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Object[]> findReportByGroupId(Long groupId);
    void deleteByUserId(String userId);
}
