/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.controllers;

import com.tranlequocthong313.dto.UserDto;
import com.tranlequocthong313.exceptions.Error;
import com.tranlequocthong313.exceptions.UserLoginBadRequestException;
import com.tranlequocthong313.exceptions.UserNotFoundException;
import com.tranlequocthong313.exceptions.UserUnauthorizedException;
import com.tranlequocthong313.models.User;
import com.tranlequocthong313.services.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tranlequocthong313
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class ApiUserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/register",
		consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public User register(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
		User u = new User();
		u.setFullName(params.get("fullName"));
		u.setPhoneNumber(params.get("phoneNumber"));
		u.setEmail(params.get("email"));
		u.setUsername(params.get("username"));
		u.setPassword(params.get("password"));
		u.setUserRole(User.UserRole.ROLE_USER);

		this.userService.save(u, avatar);
		return u;
	}

	@PostMapping(path = "/login", produces
		= MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.OK)
	public UserDto login(@RequestBody User user) {
		UserDto u = userService.login(user.getUsername(), user.getPassword());
		if (u == null) {
			throw new UserLoginBadRequestException();
		}
		return u;
	}

	@GetMapping(path = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
	public User currentUser(Principal user) {
		return userService.getCurrentUser();
	}

	@ExceptionHandler(UserLoginBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error loginBadRequest(UserLoginBadRequestException e) {
		return new Error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler(UserUnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Error unauthorized(UserUnauthorizedException e) {
		return new Error(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error notFound(UserNotFoundException e) {
		return new Error(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
}
