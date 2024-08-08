/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

import com.tranlequocthong313.dto.UserDto;
import com.tranlequocthong313.models.User;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tranlequocthong313
 */
public interface UserService extends UserDetailsService {

    List<UserDto> findAll(Map<String, String> queryParams);

    User findById(Integer id);

    void save(User user, MultipartFile avatar);

    default void save(User user) {
        save(user, null);
    }

    void delete(int id);

    User getUserByUsername(String username);

    UserDto login(String username, String password);

    User getCurrentUser();

    Object count();

    default List<UserDto> findAll() {
        return findAll(null);
    }

    default List<User> findByRole(User.UserRole userRole) {
        return findByRoles(new User.UserRole[]{userRole});
    }

    List<User> findByRoles(User.UserRole[] userRoles);
}
