/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tranlequocthong313.dto.UserDto;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.repositories.UserRepository;
import com.tranlequocthong313.services.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
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

	@Override
	public List<UserDto> findAll(Map<String, String> queryParams) {
		return userRepository.findAll(queryParams).stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
	}

	private UserDto mapToUserDto(User user) {
		return UserDto.builder()
			.id(user.getId())
			.email(user.getEmail())
			.phoneNumber(user.getPhoneNumber())
			.username(user.getUsername())
			.active(user.getActive())
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
	public User login(String username, String password) {
		User user = userRepository.getUserByUsername(username);
		if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return user;
		}
		return null;
	}

}
