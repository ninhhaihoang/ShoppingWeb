package com.groupHVC.CsHTTT.Repository;


import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Model.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDAO {

    @Autowired
    private EntityManager entityManager;

    public List<String> getRoleNames(Long userId) {
        String sql = "Select ur.roleEntity.roleName from " + UserRoleEntity.class.getName() + " ur " //
                + " where ur.userEntity.userId = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
