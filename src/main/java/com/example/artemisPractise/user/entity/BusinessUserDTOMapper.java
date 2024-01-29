package com.example.artemisPractise.user.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BusinessUserDTOMapper {

    BusinessUserDTOMapper INSTANCE = Mappers.getMapper(BusinessUserDTOMapper.class);


    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    BusinessUserDTO businessUserToBusinessUserDTO(BusinessUser businessUser);

}
