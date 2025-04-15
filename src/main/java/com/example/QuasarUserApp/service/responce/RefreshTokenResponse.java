/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:19.10.2024
 * TIME:12:45
 */
package com.example.QuasarUserApp.service.responce;

import lombok.Data;

@Data
public class RefreshTokenResponse {

    private String token;

    private long expiresIn;


    public RefreshTokenResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public RefreshTokenResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

}

