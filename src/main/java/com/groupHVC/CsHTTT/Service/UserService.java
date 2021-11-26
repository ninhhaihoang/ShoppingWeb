package com.groupHVC.CsHTTT.Service;

import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }
}
