package com.booksApiApplication.booksapi.converters;

import com.booksApiApplication.booksapi.dtos.RegisterDTO;
import com.booksApiApplication.booksapi.entity.Register;

public class RegisterConverter extends AbstractConverter<Register, RegisterDTO> {
    @Override
    public RegisterDTO fromEntity(Register entity) {
        return RegisterDTO.builder()
                .id(entity.getId())
                .user(entity.getUser())
                .book(entity.getBook())
                .regDate(entity.getRegDate())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public Register fromDto(RegisterDTO dto) {
        return Register.builder()
                .id(dto.getId())
                .user(dto.getUser())
                .book(dto.getBook())
                .regDate(dto.getRegDate())
                .status(dto.getStatus())
                .build();
    }
}
