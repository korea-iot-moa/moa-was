package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.balckList.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    //블랙 리스트 조회
    List<Object[]> findBlackListByGroupId(Long groupId);

}
