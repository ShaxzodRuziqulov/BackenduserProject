/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.09.2024
 * TIME:18:41
 */
package com.example.QuasarUserApp.web.rest;

import com.example.QuasarUserApp.entity.User;
import com.example.QuasarUserApp.service.UserService;
import com.example.QuasarUserApp.service.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) throws URISyntaxException {
        UserDto result = userService.create(userDto);
        return ResponseEntity
                .created(new URI("/api/user/create"))
                .body(result);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable Long id) throws URISyntaxException {
        if (userDto.getId() != 0 && !userDto.getId().equals(id)) {
            return ResponseEntity.badRequest().body("invalid id");
        }
        UserDto result = userService.update(userDto);
        return ResponseEntity
                .created(new URI("/api/user/create"))
                .body(result);
    }

    @GetMapping("/user/all")
    private ResponseEntity<?> all() {
        List<UserDto> userDto = userService.all();
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id) {
        User user = userService.byId(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
