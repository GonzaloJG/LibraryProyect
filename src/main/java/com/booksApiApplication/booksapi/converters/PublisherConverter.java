package com.booksApiApplication.booksapi.converters;

import com.booksApiApplication.booksapi.dtos.PublisherDTO;
import com.booksApiApplication.booksapi.entity.Publisher;

public class PublisherConverter extends AbstractConverter<Publisher, PublisherDTO>
{
    @Override
    public PublisherDTO fromEntity(Publisher entity) {
        return PublisherDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .addressLine(entity.getAddressLine())
                .city(entity.getCity())
                .state(entity.getState())
                .zip(entity.getZip())
                .build();
    }

    @Override
    public Publisher fromDto(PublisherDTO dto) {
        return Publisher.builder()
                .id(dto.getId())
                .name(dto.getName())
                .addressLine(dto.getAddressLine())
                .city(dto.getCity())
                .state(dto.getState())
                .zip(dto.getZip())
                .build();
    }
}
