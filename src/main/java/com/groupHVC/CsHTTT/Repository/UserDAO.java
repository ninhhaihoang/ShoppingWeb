package com.groupHVC.CsHTTT.Repository;

import com.groupHVC.CsHTTT.Model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDAO {

    @Autowired
    private EntityManager entityManager;

    public UserEntity findUserAccount(String username) {
        try {
            String sql = "Select e from " + UserEntity.class.getName() + " e " //
                    + " Where e.username = :username ";

            Query query = entityManager.createQuery(sql, UserEntity.class);
            query.setParameter("username", username);

            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
