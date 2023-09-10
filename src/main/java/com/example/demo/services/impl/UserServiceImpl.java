package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.repo.RolesRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.security.exceptions.UserExistException;
import com.example.demo.security.payload.AuthResponse;
import com.example.demo.services.IUserService;
import com.example.demo.ultility.JwtTokenUtil;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepo rolesRepo;

    @Transactional
    @Override
    public AuthResponse createUser(User user) {
        // 1. check exist user and roles
        if(user.getRoles().size() == 0) throw new ObjectNotFoundException("Roles are not found.");
        Optional<User> oUser = userRepo.findByEmail(user.getEmail());
        if (oUser.isPresent())
            throw new UserExistException("User is exist.");
        
        List<Roles> roles = new ArrayList<>();
        for (Roles r : user.getRoles()) {
            Optional<Roles> oRole = rolesRepo.findByName(r.getName());
            if(oRole.isPresent()) roles.add(oRole.get());
            else throw new ObjectNotFoundException("Roles are not found.");
        }
        
        // 2. create user and add role
        //List<Roles> roles = rolesRepo.findByNameList(lstRoleString);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setUserId(null);
        user.setPassword(encodePassword);
        user.setRoles(roles);
        User uSave = userRepo.save(user);

        // 3. generate token and send response
        String token = jwtUtil.generateAccessString(uSave);
        AuthResponse response = new AuthResponse(uSave.getEmail(), token);
        return response;
    }

}
