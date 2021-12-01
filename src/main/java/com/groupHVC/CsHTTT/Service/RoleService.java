package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.RoleEntity;
import com.groupHVC.CsHTTT.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleService{
    @Autowired
    RoleRepository roleRepository;

    public RoleEntity getRole(Long id){
        return roleRepository.findById(id).get();
    }
}
