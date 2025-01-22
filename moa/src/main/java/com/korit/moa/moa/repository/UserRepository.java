package com.korit.moa.moa.repository;

import com.korit.moa.moa.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserId(String userId);

    boolean existsByNickName(String nickName);

    boolean existsByUserId(String userId);

    @Query(
            "SELECT u FROM User u" +
                    " WHERE u.userName = :userName AND u.phoneNumber = :phoneNumber "
    )
    Optional<User> findByUserNameAndUserPhoneNumber(@Param("userName") String userName, @Param("phoneNumber") String phoneNumber);

    Optional<User>  findByUserIdAndUserName(String userId, String userName);

    @Query("""
     select u.nickName
     from User u
     where u.userId = :userId
""")
    String findByUserNickName(@Param("userId") String userId);

    User findBySnsIdAndJoinPath(String snsId, String registration);
    
    @Modifying
    @Query("DELETE FROM User u WHERE u.userId = :userId")
    void deleteUser(@Param("userId") String userId);

}