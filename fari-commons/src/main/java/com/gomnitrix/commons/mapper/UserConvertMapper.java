package com.gomnitrix.commons.mapper;

import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserConvertMapper {
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    UserDto toUserDto(User user);

    User fromUserDto(UserDto userDto);

    List<UserDto> toUserDtos(List<User> users);

    List<User> fromUserDtos(List<UserDto> userDtos);
}
