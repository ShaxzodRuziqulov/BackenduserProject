/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:18.10.2024
 * TIME:11:09
 */
package com.example.QuasarUserApp.web.rest;

import com.example.QuasarUserApp.entity.User;
import com.example.QuasarUserApp.entity.status.Status;
import com.example.QuasarUserApp.repository.UserRepository;
import com.example.QuasarUserApp.service.AuthenticationService;
import com.example.QuasarUserApp.service.dto.LoginUserDto;
import com.example.QuasarUserApp.service.dto.RefreshTokenDto;
import com.example.QuasarUserApp.service.dto.RegisterUserDto;
import com.example.QuasarUserApp.service.dto.UserDto;
import com.example.QuasarUserApp.service.responce.LoginResponse;
import com.example.QuasarUserApp.service.responce.RefreshTokenResponse;
import com.example.QuasarUserApp.service.security.JwtService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, UserRepository userRepository, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody RegisterUserDto registerUserDto) throws MessagingException {
        UserDto registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String code) {
        User user = userRepository.findByVerificationCode(code);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
        }
        user.setStatus(Status.ACTIVE);
        user.setVerificationCode(null);
        userRepository.save(user);

        return ResponseEntity.ok("User verified successfully.");
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {

        User refreshTokenUser = authenticationService.authenticateRefresh(refreshTokenDto);

        String rwtToken = jwtService.generateRefreshToken(refreshTokenUser);

        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse().setToken(rwtToken).setExpiresIn(jwtService.getExpirationTimeRefresh());

        return ResponseEntity.ok(refreshTokenResponse);

    }


}
