package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.UserRoleEntity;
import com.groupHVC.CsHTTT.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public void saveUserRole(UserRoleEntity userRole){
        userRoleRepository.save(userRole);
    }
}
