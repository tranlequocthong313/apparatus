/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.repositories;

import com.tranlequocthong313.models.User;

/**
 *
 * @author tranlequocthong313
 */
public interface UserRepository extends BaseRepository<User, Integer> {

	User getUserByUsername(String username);
}