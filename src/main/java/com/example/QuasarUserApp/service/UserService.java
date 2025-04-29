/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.09.2024
 * TIME:17:40
 */
package com.example.QuasarUserApp.service;

import com.example.QuasarUserApp.entity.User;
import com.example.QuasarUserApp.entity.status.Status;
import com.example.QuasarUserApp.repository.UserRepository;
import com.example.QuasarUserApp.service.dto.UserDto;
import com.example.QuasarUserApp.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto update(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> all() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public User byId(Long id) {
        return userRepository
                .findById(id)
                .orElseGet(User::new);
    }

    public void delete(Long id) {
        userRepository.updateByStatus(id, Status.DELETED);
    }

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }
}
