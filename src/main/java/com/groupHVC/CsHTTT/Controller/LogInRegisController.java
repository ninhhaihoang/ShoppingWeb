package com.groupHVC.CsHTTT.Controller;

import com.groupHVC.CsHTTT.Model.RoleEntity;
import com.groupHVC.CsHTTT.Model.UserEntity;
import com.groupHVC.CsHTTT.Model.UserRoleEntity;
import com.groupHVC.CsHTTT.Repository.UserRepository;
import com.groupHVC.CsHTTT.Repository.UserRoleRepository;
import com.groupHVC.CsHTTT.Service.RoleService;
import com.groupHVC.CsHTTT.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogInRegisController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String viewLoginPage(Model model){
        model.addAttribute("user", new UserEntity());
        return "userSign-in";
    }

    @RequestMapping(value = "/register_page", method = RequestMethod.GET)
    public String viewRegisterPage(Model model){
        model.addAttribute("user", new UserEntity());
        return "userSign-up";
    }

    @PostMapping("/process_register")
    public String processRegister(UserEntity user){

        UserRoleEntity roleEntity = new UserRoleEntity();
        RoleEntity role = roleService.getRole(2L);
        roleEntity.setRoleEntity(role);
        roleEntity.setUserEntity(user);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getEncryptedPassword());
        user.setEncryptedPassword(encodedPassword);

        userRepository.save(user);
        userRoleService.saveUserRole(roleEntity);

        return "userSign-in";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage() {
        //model.addAttribute("title", "Logout");
        return "index";
    }
}
