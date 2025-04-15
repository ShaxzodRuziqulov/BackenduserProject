/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.09.2024
 * TIME:17:42
 */
package com.example.QuasarUserApp.service.dto;

import com.example.QuasarUserApp.entity.status.Status;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Status status;
}
