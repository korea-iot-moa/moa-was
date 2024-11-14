package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.balckList.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {
    
}
