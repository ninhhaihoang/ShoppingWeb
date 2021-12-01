package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Transient;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
