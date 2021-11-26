package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(long userId);
    UserEntity findByUsername(String username);
}
