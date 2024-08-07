/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tranlequocthong313.components.JwtProvider;
import com.tranlequocthong313.dto.UserDto;
import com.tranlequocthong313.exceptions.UnauthorizedException;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.repositories.UserRepository;
import com.tranlequocthong313.services.UserService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tranlequocthong313
 */
@Service("userDetailService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public List<UserDto> findAll(Map<String, String> queryParams) {
        return userRepository.findAll(queryParams).stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .active(user.getActive())
                .avatar(user.getAvatar())
                .userRole(user.getUserRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .note(user.getNote())
                .build();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public void save(User user, MultipartFile avatar) {
        if (avatar != null && !avatar.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserDto login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            String token = this.jwtProvider.generateToken(user.getUsername());
            UserDto userDto = mapToUserDto(user);
            userDto.setToken(token);
            return userDto;
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                username = ((UserDetails) authentication.getPrincipal()).getUsername();
            } else {
                username = authentication.getName();
            }
        }

        if (username != null) {
            return userRepository.getUserByUsername(username);
        } else {
            throw new UnauthorizedException("No authenticated user found");
        }
    }

    @Override
    public Object count() {
        return userRepository.count();
    }

    @Override
    public List<UserDto> findByRole(User.UserRole userRole) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("userRole", userRole.name());
        return findAll(queryParams);
    }

}
