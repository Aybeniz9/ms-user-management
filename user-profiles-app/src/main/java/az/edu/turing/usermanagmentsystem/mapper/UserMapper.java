package az.edu.turing.usermanagmentsystem.mapper;

import az.edu.turing.usermanagmentsystem.model.dto.UserDto;
import az.edu.turing.usermanagmentsystem.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(UserEntity userEntity);

    List<UserDto> entityListToDtoList(List<UserEntity> userEntity);

    UserEntity dtoToEntity(UserDto userDto);

}
