package com.example.artemisPractise.user.entity;

import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface NotificationDTOMapper {

    NotificationDTOMapper INSTANCE = Mappers.getMapper(NotificationDTOMapper.class);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    NotificationDTO businessUserToNotificationDTO(BusinessUser businessUser);

}
