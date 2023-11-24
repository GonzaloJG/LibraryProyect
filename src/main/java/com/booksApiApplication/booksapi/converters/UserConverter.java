package com.booksApiApplication.booksapi.converters;

import com.booksApiApplication.booksapi.dtos.SignupRequestDTO;
import com.booksApiApplication.booksapi.dtos.UserDTO;
import com.booksApiApplication.booksapi.entity.User;

public class UserConverter extends AbstractConverter<User, UserDTO> {
    @Override
    public UserDTO fromEntity(User entity) {
        if (entity == null) return null;
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }

    @Override
    public User fromDto(UserDTO dto) {
        if (dto == null) return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }

    public User signup(SignupRequestDTO dto){
        if (dto == null) return null;
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
