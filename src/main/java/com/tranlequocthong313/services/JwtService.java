/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tranlequocthong313.services;

/**
 *
 * @author tranlequocthong313
 */
public interface JwtService {

	String generateTokenLogin(String username);

	String getUsernameFromToken(String token);

	Boolean validateTokenLogin(String token);

}
