package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
