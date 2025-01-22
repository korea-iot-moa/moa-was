package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.user.UserHobbies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHobbiesRepository extends JpaRepository<UserHobbies, Long> {
}
