package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.Report.Report;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Object[]> findReportByGroupId(Long groupId);
    void deleteByUserId(String userId);

    @Modifying
    @Query("""
        delete from Report r where r.groupId = :groupId and r.reportUser = :reportUser
    """)
    void deleteReport(@Param("groupId") Long groupId, @Param("reportUser") @NotBlank String reportUser);
}
