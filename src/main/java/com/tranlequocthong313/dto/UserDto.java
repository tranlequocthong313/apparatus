/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tranlequocthong313.models.*;
import com.tranlequocthong313.models.Thread;
import com.tranlequocthong313.models.User.UserRole;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author tranlequocthong313
 */
@Data
@Builder
public class UserDto {

    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String username;
    private boolean active;
    private UserRole userRole;
    private String avatar;
    private Date createdAt;
    private Date updatedAt;
    private String note;
    private String token;
}
